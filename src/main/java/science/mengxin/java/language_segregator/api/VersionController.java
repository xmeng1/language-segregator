package science.mengxin.java.language_segregator.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import science.mengxin.java.language_segregator.model.VersionInfo;

import java.util.concurrent.atomic.AtomicLong;

@Controller
@V1APIController
public class VersionController {

  private static final String template = "Hello, %s!";
  private final AtomicLong counter = new AtomicLong();

  @GetMapping("/version")
  @ResponseBody
  public VersionInfo getVersion(
      @RequestParam(name = "name", required = false, defaultValue = "Stranger") String name) {
    return new VersionInfo("0.0.1");
  }
}
