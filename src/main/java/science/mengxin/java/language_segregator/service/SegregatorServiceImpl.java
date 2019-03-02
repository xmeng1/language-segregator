package science.mengxin.java.language_segregator.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import science.mengxin.java.language_segregator.model.SegItem;
import science.mengxin.java.language_segregator.model.SegRequest;
import science.mengxin.java.language_segregator.model.basic.ResultList;
import science.mengxin.java.language_segregator.model.options.DocOptions;

@Service
public class SegregatorServiceImpl implements SegregatorService {

  private static final Logger logger = LoggerFactory.getLogger(SegregatorServiceImpl.class);


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
  public ResultList<SegItem> split(SegRequest segRequest) {
    logger.debug("start SegregatorService split {}", segRequest.toString());

    List<SegItem> segItemList = new ArrayList<>();
    List<String> titles = new ArrayList<>();
    int seqNo = 1;

    List<String> fragments = generateFragments(segRequest.getSource(), segRequest.getDocOptions());

    // detect every fragment
    for (String fragment : fragments) {

    }
    // build result

    ResultList<SegItem> resultList = new ResultList<>();

    segItemList.add(new SegItem());
    segItemList.add(new SegItem());
    resultList.setList(segItemList);
    return resultList;
  }
}
