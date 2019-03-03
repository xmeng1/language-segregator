package science.mengxin.java.language_segregator.utilities

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import spock.lang.Specification

import java.util.regex.Pattern

class TimeLimitedMatcherFactoryTest extends Specification {

    private static final Logger logger = LoggerFactory.getLogger(TimeLimitedMatcherFactoryTest.class);
    def "Matcher"() {
        given:
        Pattern pattern = Pattern.compile("[\\s\\w]+");
        String str = "long wordy string";
        boolean matched;

        try {
            matched = TimeLimitedMatcherFactory.matcher(pattern, str).matches();
        } catch (TimeLimitedMatcherFactory.RegExpTimeoutException e) {
            // Take appropriate action here.
            logger.debug("e {}", e)
        }
    }

    def "Matcher1"() {
    }
}
