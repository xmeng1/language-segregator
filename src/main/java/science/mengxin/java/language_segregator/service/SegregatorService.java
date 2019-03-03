package science.mengxin.java.language_segregator.service;

import java.util.List;
import science.mengxin.java.language_segregator.model.SegItem;
import science.mengxin.java.language_segregator.model.SegRequest;
import science.mengxin.java.language_segregator.model.basic.ResultList;
import science.mengxin.java.language_segregator.model.options.DocOptions;
import science.mengxin.java.language_segregator.model.options.TitlePatternOptions;

public interface SegregatorService {

  List<String> generateFragments(String source, DocOptions docOptions);

  Boolean checkTitle(String fragment, TitlePatternOptions titlePatternOptions);

  public ResultList<SegItem> split(SegRequest segRequest);
}
