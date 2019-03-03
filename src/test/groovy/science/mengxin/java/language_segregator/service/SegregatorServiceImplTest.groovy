package science.mengxin.java.language_segregator.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import science.mengxin.java.language_segregator.model.SegItem
import science.mengxin.java.language_segregator.model.SegRequest
import science.mengxin.java.language_segregator.model.basic.ResultList
import science.mengxin.java.language_segregator.model.options.DocOptions
import science.mengxin.java.language_segregator.model.options.SupportLang
import science.mengxin.java.language_segregator.model.options.TitlePatternOptions
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

@SpringBootTest
class SegregatorServiceImplTest extends Specification {
    void setup() {
    }

    void cleanup() {
    }

    @Autowired
    SegregatorService segregatorService;

    @Shared
    List<String> fragments = [
            "FARSI 975 CES 2019",
            "SPEN / CES SETUP",
            "OOV:",
            "2019 starts with the Consumer Electronics Show in Las Vegas",
            "This is where you come to see all the big new tech - the crazy new ideas, and the occasional polar bear. (PIX- polar bear) Anything to get attention, basically - which is Vegas all over.",
            "خرس‌های قطبی رو ببینند." +
                    "و هر چه که توجه‌ها رو به خودش جلب کنه... چیزی که اصلا ماهیت وگاسه.",
            "PTC changed to OOV:"
    ]

    @Shared
    String source1 = """
FARSI 975 CES 2019 


SPEN / CES SETUP


PIX - CES

OOV:
2019 starts with the Consumer Electronics Show in Las Vegas
This is where you come to see all the big new tech - the crazy new ideas, and the occasional polar bear. (PIX- polar bear) Anything to get attention, basically - which is Vegas all over.

سال ۲۰۱۹ با نمایشگاه بین‌المللی سی ای اس در لاس‌وگاس آغاز شد
جایی که همه میان تا بزرگ‌ترین فناوری‌های تازه، ایده‌های جدید دیوانه‌وار و حتی خرس‌های قطبی رو ببینند. 
و هر چه که توجه‌ها رو به خودش جلب کنه... چیزی که اصلا ماهیت وگاسه.
OOV:
But what does it all mean? What will the coming year look like? What is the important tech that you need to look out for?

ولی همه اینها به چه معناست؟ سال  میلادی پیش رو چه طور خواهد بود؟ و مهم‌ترین فناوری‌هایی که قراره دنبالش بریم چه چیزهاییه؟


PTC:
We’re going and try to tell you the story of CES 2019 by bringing you... 

ما تلاش می کنیم چند و چون نمایشگاه سی ای اس 2019 را با شما در میان بذاریم با... 
GFX - COUNTERS APPEAR FOR 20 GADGETS AND 19 - LET’S MAKE THEM SAY 20 AND 19 AT FIRST

PTC:
...20 bits of tech in the next 19 minutes - mind you, that is a lot of stuff in not a lot of time, we better crack on. Quick tip, CES is always a mix of big trends and stuff and nonsense - I will leave you to workout which is which...

بیست تکه فناوری در 19 دقیقه. دقت کنید که این چیزای خیلی زیادیه در وقت خیلی کم.
بهتره شروع کنیم. یک نکته این که سی ای اس همیشه تلفیقی از فناوری های گنده و چیزهای بی معنیه، قضاوتش با شما.

GFX - REWIND THE COUNTERS FROM 20 TO 0 AND FROM 19 TO 0

PTC changed to OOV:
So, reset the counter, reset the clock - and we’ll start with something that’s always big here - TVs…

خب، از محصولات بزرگ این نمایشگاه شروع می‌کنیم، یعنی تلویزیون‌ها



SPEN / GADGET 1 / OLED LG TV


PIX ROSE DISPLAY 4 CURVED PRETTY DISPLAYS 

OOV:
The big screen technology here is OLED, and LG Display is the only company making large OLED screens. The tech has several advantages. It can be curved. It can do really black blacks... 

فناوری بزرگ نمایشگر در اینجا او لده، و ال‌جی دیسپلی تنها شرکتیه که نمایشگرهای بزرگ او-لد می‌سازه. فناوری‌ای که چندین قابلیت داره. می‌تونه قوس برداره، می‌تونه سیاهش واقعا سیاه سیاه باشه...

PIX TRANSPARENT DISPLAY
"""

    @Shared
    TitlePatternOptions defaultOption = new TitlePatternOptions()

    def "GenerateFragments"() {
    }

    @SuppressWarnings(["GroovyPointlessBoolean", "GroovyAssignabilityCheck"])
    @Unroll("""
#no:  check whether #fragment is title, result: #predict the scenario: #scenario
""")
    def "CheckTitle"() {
        given: ""

        when: "check whether fragment is title based on given option"
        def result = segregatorService.checkTitle(fragment, option)
        then:
        assert result == predict
        where: "the scenarios list"
        no | fragment     | option        || predict | scenario
        1  | fragments[0] | defaultOption || true    | "default"
        2  | fragments[1] | defaultOption || true    | "default"
        3  | fragments[2] | defaultOption || true    | "default"
        4  | fragments[3] | defaultOption || false   | "default"
        5  | fragments[4] | defaultOption || false   | "default"
        6  | fragments[5] | defaultOption || false   | "default"
        7  | fragments[6] | defaultOption || false   | "default"
    }


    @Shared
    String special = " SOT NILS " +
            "WOLLNY, He" +
            "ad of Digi" +
            "tal Busine" +
            "ss, Audi: “" +
            "What we ha" +
            "ve created" +
            ", basicall" +
            "y, is a co" +
            "mpletely n" +
            "ew categor" +
            "y of conte" +
            "nt, becaus" +
            "e it is th" +
            "e first ti" +
            "me that it" +
            " is someth" +
            "ing that w" +
            "orks best " +
            "in the car.”"
    private static final Logger logger = LoggerFactory.getLogger(SegregatorServiceImplTest.class);
    @Shared
    String special2 = "ad of Digi" +
            "tal Busine" +
            "ss, Audi: ";

    @Unroll
    @SuppressWarnings(["GroovyPointlessBoolean", "GroovyAssignabilityCheck"])
    def "Latin Match"() {
        given: ""
        when: "check whether fragment is title based on given option"
//        int max = fragment.length() / 10
//        logger.debug("40 to 91, string: {}", fragment.substring(40, 91))
//        def isLatin1 = fragment.substring(40, 91).matches("(\\p{IsLatin}*\\s*\\d*\\pP*)*");
//        for (int j = 0; j < max; j++) {
//            logger.debug("0 to {}, string: {}", (j + 1) * 10, fragment.substring(0, (j + 1) * 10))
//            def isLatin = fragment.substring(0, (j + 1) * 10).matches("(\\p{IsLatin}*\\s*\\d*\\pP*)*");
//            logger.debug("result {}", isLatin)
//        }
        def isLatin = fragment.matches("(\\p{IsLatin}*\\s*\\d*\\pP*)*");
        then:
        assert isLatin == predict
//        max
        where: "the scenarios list"
        no | fragment                                                  || predict | scenario
//      1  | special                                                   || true    | "english"
//        1  | special2                                                  || true    | "english"
        1  | "this is good: 1 2 3"                                     || true    | "english"
        1  | "PTC:"                                                    || true    | "english"
        1  | "OOV:"                                                    || true    | "english"
        1  | "SPEN / GADGET 1 / OLED LG TV"                            || true    | "english"
        1  | "GFX - REWIND THE COUNTERS FROM 20 TO 0 AND FROM 19 TO 0" || true    | "english"
        1  | "FARSI 975 CES 2019"                                      || true    | "english"
        1  | "see all the big new tech - the crazy new ideas, and " +
                "the occasional polar bear. (PIX- polar bear) Anythi"  || true    | "english"
        1  | " ای اس در لاس‌وگاس آغاز شد"                              || false   | "english"
    }

    @SuppressWarnings(["GroovyPointlessBoolean", "GroovyAssignabilityCheck"])
    @Unroll("""
#no:  split #source   result size: #predict the scenario: #scenario
""")
    def "Split"() {
        given: ""
        when: "check split"
        Set<SupportLang> supportLangSet = new HashSet<>()
        supportLangSet.add(SupportLang.EN)
        supportLangSet.add(SupportLang.FA)

        SegRequest segRequest = new SegRequest(source, new DocOptions(supportLangSet), new TitlePatternOptions())
        ResultList<SegItem> result = segregatorService.split(segRequest)
        then:
        assert result.list.size() == predict
        where: "the scenarios list"
        no | source  || predict | scenario
        1  | source1 || 2       | "official doc segment"
    }
}
