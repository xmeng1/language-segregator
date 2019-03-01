package science.mengxin.java.language_segregator.api;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import science.mengxin.java.language_segregator.model.SegItem;
import science.mengxin.java.language_segregator.model.SegRequest;
import science.mengxin.java.language_segregator.model.basic.Result;
import science.mengxin.java.language_segregator.model.VersionInfo;
import science.mengxin.java.language_segregator.model.basic.ResultList;
import science.mengxin.java.language_segregator.service.SegregatorService;

@Controller
@V1APIController
public class SegregatorController {

  private static final String template = "Hello, %s!";
  private final AtomicLong counter = new AtomicLong();

  private final SegregatorService segregatorService;

  public SegregatorController(SegregatorService segregatorService) {
    this.segregatorService = segregatorService;
  }

  @PostMapping("/split")
  @ResponseBody
  public ResultList<SegItem> getVersion(@RequestBody SegRequest segRequest) {
    return segregatorService.split(segRequest);
  }
}
