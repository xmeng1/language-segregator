package science.mengxin.java.language_segregator.api;

import ch.qos.logback.core.util.ContentTypeUtil;
import com.sun.xml.internal.ws.api.pipe.ContentType;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import science.mengxin.java.language_segregator.model.SegItem;
import science.mengxin.java.language_segregator.model.SegRequest;
import science.mengxin.java.language_segregator.model.basic.Result;
import science.mengxin.java.language_segregator.model.VersionInfo;
import science.mengxin.java.language_segregator.model.basic.ResultList;
import science.mengxin.java.language_segregator.service.SegregatorService;

@RestController
@V1APIController
public class SegregatorController {

  private static final String template = "Hello, %s!";
  private final AtomicLong counter = new AtomicLong();

  private final SegregatorService segregatorService;

  public SegregatorController(SegregatorService segregatorService) {
    this.segregatorService = segregatorService;
  }

  @PostMapping(value = "/split", produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseBody
  public ResultList<SegItem> splitString(@RequestBody SegRequest segRequest) {
//    return new VersionInfo("0.0.1");
    return segregatorService.split(segRequest);
  }

}
