package science.mengxin.java.language_segregator.service;

import science.mengxin.java.language_segregator.model.SegItem;
import science.mengxin.java.language_segregator.model.SegRequest;
import science.mengxin.java.language_segregator.model.basic.ResultList;

public interface SegregatorService {

  public ResultList<SegItem> split(SegRequest segRequest);
}
