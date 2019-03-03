package science.mengxin.java.language_segregator.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import science.mengxin.java.language_segregator.model.SegItem;
import science.mengxin.java.language_segregator.model.SegRequest;
import science.mengxin.java.language_segregator.model.basic.ResultList;
import science.mengxin.java.language_segregator.model.options.DocOptions;
import science.mengxin.java.language_segregator.model.options.SupportLang;
import science.mengxin.java.language_segregator.model.options.TitlePatternOptions;
import science.mengxin.java.language_segregator.utilities.TimeLimitedMatcherFactory;

@Service
public class SegregatorServiceImpl implements SegregatorService {

  private static final Logger logger = LoggerFactory.getLogger(SegregatorServiceImpl.class);

  private LangDetectService langDetectService;

  public SegregatorServiceImpl(LangDetectService langDetectService) {
    this.langDetectService = langDetectService;
  }

  @Override
  public List<String> generateFragments(String source, DocOptions docOptions) {
    List<String> result;
    switch (docOptions.getBlockSeparator()) {
      case LINE_BREAK:
        result = Arrays.asList(source.split("\\n"));
        break;
      case BLANK_LINE:
        result = Arrays.asList(source.split("\\n\\n"));
        break;
      case REGEX_EXPRESS:
        result = Arrays.asList(source.split(docOptions.getBlockSeparatorRex()));
        break;
      default:
        result = new ArrayList<>();
        break;
    }
    return result.stream().filter(x -> !x.isEmpty()).collect(Collectors.toList());
  }

  private static Pattern latinPattern = Pattern.compile("(\\p{IsLatin}*\\s*\\d*\\pP*)*");

  @Override
  public Boolean checkTitle(String fragment, TitlePatternOptions titlePatternOptions) {

    // check Uppercase if enable
    if (titlePatternOptions.getFilterByCase() && titlePatternOptions.getAllUpperCase()) {
      // if the fragment is Latin String and all upper case, the toUpperCase result should be
      //  same with original string
      boolean isLatin;
      try {
        isLatin = TimeLimitedMatcherFactory.matcher(latinPattern, fragment, 2000).matches();
      } catch (TimeLimitedMatcherFactory.RegExpTimeoutException e) {
        isLatin = false;
        logger.debug("check latin timeout, regarded it as non-latin {}", e.getMessage());
      }
      if (isLatin && fragment.toUpperCase().equals(fragment)) {
        return true;
      }
    }
    if (titlePatternOptions.getFilterByLength()) {
      if (fragment.length() < titlePatternOptions.getLengthThreshold()) {
        return true;
      }
    }

    // NOTE: this should be final check
    if (titlePatternOptions.getFilterByRegex()) {
      Matcher matcher = Pattern.compile(titlePatternOptions.getRegexPattern()).matcher(fragment);
      return matcher.matches();
    }

    return false;
  }

  /**
   * In order to mapping between language, we need seq number to track the language fragment.
   * There are several things we need to think about
   *
   * 1. title should be share between language
   * 2. title should be with unique seq number
   * 3. every pair of fragment (diff language) should have same seq number
   * 4. the title + fragments seq number should in order
   *
   *
   * @param segRequest SegRequest
   * @return ResultList<SegItem>
   */
  @Override
  public ResultList<SegItem> split(SegRequest segRequest) {
    logger.debug("start SegregatorService split {}", segRequest.toString());

    List<SegItem> segItemList = new ArrayList<>();
    Map<Integer, String> titles = new HashMap<>();
    // the overall seq number
    int seqNo = 1;

    // the previous fragment language
    SupportLang preLang = null;
    SupportLang seqInitialLang = null;
    List<String> fragments = generateFragments(segRequest.getSource(), segRequest.getDocOptions());
    boolean previousNotTitle = false;
    boolean checkTitle = segRequest.getDocOptions().getSupportTitle();

    // detect every fragment
    for (String fragment : fragments) {
      logger.debug("process fragment {} and seqNo {} ", fragment, seqNo);
      if (checkTitle && checkTitle(fragment, segRequest.getTitlePatternOptions())) {
        // If previous fragment is not the title, we need increase the seq no for title.
        if (previousNotTitle) {
          seqNo++;
        }
        titles.put(seqNo, fragment);
        previousNotTitle = false;
        // every title should have unique seq no, so increase it.
        seqNo++;
      } else {
        previousNotTitle = true;
        Optional<SupportLang> fragLang = langDetectService.detect(fragment);
        // if the lang is empty or not in the predefine language list, use 3rd party api
        if (!fragLang.isPresent() || !segRequest.getDocOptions().getLangList()
            .contains(fragLang.get())) {
          fragLang = langDetectService.remoteDetect(fragment);
        }

        if (fragLang.isPresent() && preLang == null && seqInitialLang == null) {
          seqInitialLang = fragLang.get();
        }

        if (!fragLang.isPresent() && preLang == null) {
          fragLang = Optional.of(SupportLang.UNKNOWN);
        } else if (!fragLang.isPresent()) {
          fragLang = Optional.of(preLang);
        }

        SegItem segItem;
        Optional<SupportLang> finalFragLang = fragLang;

        Optional<SegItem> findSeg = segItemList.stream()
            .filter(x -> x.getLanguage().equals(finalFragLang.get()))
            .findFirst();

        if (!findSeg.isPresent()) {
          segItem = new SegItem();
          segItem.setLanguage(finalFragLang.get());
          Map<Integer, String> contents = new HashMap<>();
          contents.put(seqNo, fragment);
          segItem.setContents(contents);
          segItemList.add(segItem);
          preLang = finalFragLang.get();
          // when we add the first fragment, we don't know whether next fragment is same language
          // seqNo++;
        } else {
          segItem = findSeg.get();
          if (finalFragLang.get() == preLang) {
            String existing = segItem.getContents().get(seqNo);
            segItem.getContents().put(seqNo, existing == null ? fragment : existing + fragment);
          } else {
            // only when the mapping language handle finish, process second seq.
            if (finalFragLang.get().equals(seqInitialLang)) {
              seqNo++;
            }
            segItem.getContents().put(seqNo, fragment);
            preLang = finalFragLang.get();
          }
        }

      }
    }
    logger.debug("finish fragment process {}", segItemList.size());
    // build result
    segItemList.forEach(x -> x.setTitles(titles));
    ResultList<SegItem> resultList = new ResultList<>();
    resultList.setList(segItemList);
    resultList.setTotalSize(segItemList.size());
    resultList.setResultSize(segItemList.size());
    resultList.setNextPage(false);
    return resultList;
  }
}
