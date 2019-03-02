package science.mengxin.java.language_segregator.api;

import ch.qos.logback.core.util.ContentTypeUtil;
import com.sun.xml.internal.ws.api.pipe.ContentType;
import java.util.concurrent.atomic.AtomicLong;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import science.mengxin.java.language_segregator.model.SegItem;
import science.mengxin.java.language_segregator.model.SegRequest;
import science.mengxin.java.language_segregator.model.basic.Result;
import science.mengxin.java.language_segregator.model.VersionInfo;
import science.mengxin.java.language_segregator.model.basic.ResultList;
import science.mengxin.java.language_segregator.service.DocumentService;
import science.mengxin.java.language_segregator.service.SegregatorService;

@RestController
@V1APIController
public class SegregatorController {

  private static final String template = "Hello, %s!";
  private final AtomicLong counter = new AtomicLong();

  private final SegregatorService segregatorService;
  private final DocumentService documentService;

  private static final Logger logger = LoggerFactory.getLogger(SegregatorController.class);

  public SegregatorController(SegregatorService segregatorService,
      DocumentService documentService) {
    this.segregatorService = segregatorService;
    this.documentService = documentService;
  }

  @PostMapping(value = "/split", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseBody
  public ResultList<SegItem> splitString(@RequestBody SegRequest segRequest) {
//    return new VersionInfo("0.0.1");
    return segregatorService.split(segRequest);
  }

  @PostMapping(value = "/splitDoc", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseBody
  public ResultList<SegItem> splitDoc(
//      @RequestPart(name = "config", required = false) SegRequest segRequest,
      @Valid SegRequest segRequest,
      @RequestParam(value = "doc") MultipartFile doc) {
    String content = documentService.parseDoc(doc);
    logger.debug("api get doc \n {}", content);
    return segregatorService.split(segRequest);
  }
}
