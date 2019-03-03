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

  @Override
  public Boolean checkTitle(String fragment, TitlePatternOptions titlePatternOptions) {

    // check Uppercase if enable
    if (titlePatternOptions.getFilterByCase() && titlePatternOptions.getAllUpperCase()) {
      // if the fragment is Latin String and all upper case, the toUpperCase result should be
      //  same with original string
      if (fragment.matches("(\\p{IsLatin}*\\s*\\d*\\pP*)*") && fragment.toUpperCase()
          .equals(fragment)) {
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

  @Override
  public ResultList<SegItem> split(SegRequest segRequest) {
    logger.debug("start SegregatorService split {}", segRequest.toString());

    List<SegItem> segItemList = new ArrayList<>();
    List<String> titles = new ArrayList<>();

    // the overall seq number
    int seqNo = 1;

    // the previous fragment language
    SupportLang preLang = null;
    SupportLang seqInitialLang = null;
    List<String> fragments = generateFragments(segRequest.getSource(), segRequest.getDocOptions());

    boolean checkTitle = segRequest.getDocOptions().getSupportTitle();

    // detect every fragment
    for (String fragment : fragments) {
      if (checkTitle && checkTitle(fragment, segRequest.getTitlePatternOptions())) {
        titles.add(fragment);
        seqNo++;
      } else {
        Optional<SupportLang> fragLang = langDetectService.detect(fragment);

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
    // build result

    ResultList<SegItem> resultList = new ResultList<>();
    resultList.setList(segItemList);
    return resultList;
  }
}
