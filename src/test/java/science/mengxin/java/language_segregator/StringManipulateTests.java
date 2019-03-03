package science.mengxin.java.language_segregator;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import spock.lang.Shared;

@SpringBootTest
public class StringManipulateTests {

  private static final Logger logger = LoggerFactory.getLogger(StringManipulateTests.class);

  @Shared
  String shortENSource = "Or it can be transparent ";

  String shortENSourceUpper = "OR IT CAN BE TRANSPARENT ";
  @Shared
  String shortFASource = "یا حتی شفاف بشه؟";
  @Shared
  String shortZhCnSource = "或者它可以是透明的";
  @Shared
  String shortZhTwSource = "有人曾致中華人民共和國政府的相關部門，希望能恢復部分";
  @Shared
  String shortFRSource = "Ou cela peut être transparent";
  @Shared
  String shortAFSource = "Of dit kan deursigtig wees";

  @Test
  public void uppercaseTest() {
    Boolean upper1 = shortENSource.toUpperCase().equals(shortENSource);
    Boolean upper2 = shortENSourceUpper.toUpperCase().equals(shortENSourceUpper);
    logger.debug("upper1 should be false: {}", upper1);
    logger.debug("upper2 should be true: {}", upper2);
    // other language

    logger.debug("check: {}", shortFASource.toUpperCase().equals(shortFASource));
    logger.debug("check: {}", shortZhCnSource.toUpperCase().equals(shortZhCnSource));
    logger.debug("check: {}", shortZhTwSource.toUpperCase().equals(shortZhTwSource));

    logger.debug("check: {}", shortFRSource.toUpperCase().equals(shortFRSource));
    logger.debug("check: {}", shortFRSource.toUpperCase());

    logger.debug("check: {}", shortAFSource.toUpperCase().equals(shortAFSource));
    logger.debug("check: {}", shortAFSource.toUpperCase());
  }
}
