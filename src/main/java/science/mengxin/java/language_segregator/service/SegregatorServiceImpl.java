package science.mengxin.java.language_segregator.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import science.mengxin.java.language_segregator.model.SegItem;
import science.mengxin.java.language_segregator.model.SegRequest;
import science.mengxin.java.language_segregator.model.basic.ResultList;

@Service
public class SegregatorServiceImpl implements SegregatorService {

  private static final Logger logger = LoggerFactory.getLogger(SegregatorServiceImpl.class);

  @Override
  public ResultList<SegItem> split(SegRequest segRequest) {
    logger.debug("start SegregatorService split {}", segRequest.toString());
    return null;
  }
}
