package science.mengxin.java.language_segregator;

import com.cybozu.labs.langdetect.Detector;
import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.LangDetectException;
import com.detectlanguage.DetectLanguage;
import com.detectlanguage.Result;
import com.detectlanguage.errors.APIError;
import com.google.common.base.Optional;
import com.optimaize.langdetect.LanguageDetector;
import com.optimaize.langdetect.LanguageDetectorBuilder;
import com.optimaize.langdetect.i18n.LdLocale;
import com.optimaize.langdetect.ngram.NgramExtractors;
import com.optimaize.langdetect.profiles.LanguageProfile;
import com.optimaize.langdetect.profiles.LanguageProfileReader;
import com.optimaize.langdetect.text.CommonTextObjectFactories;
import com.optimaize.langdetect.text.TextObject;
import com.optimaize.langdetect.text.TextObjectFactory;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest
public class LanguageDetectionQuickTests {

    String persianStr = "طراحی ساده اولد طوری کار می کند که شما می\u200Cتونید این را که محرک نامیده میشه پشتش صفحه نمایش نصب کنید. این ها چیزای بسیار بسیار نازکیه که کل صفحه نمایش را به یک بلندگو تبدیل می کنه. پشت سر این صفحه نمایش به نظرم پنج محرک نصب شده و طوری کار می کنه که به نظر میاد صدا واقعا از جای مشخص صفحه بیرون میآد و به این دلیله که در واقع همینطور هم است.";


    /**
     * https://github.com/optimaize/language-detector
     *
     * @throws IOException  IOException
     */
    @Test
    public void languageDetectTest() throws IOException {
        //load all languages:
        List<LanguageProfile> languageProfiles = new LanguageProfileReader().readAllBuiltIn();

        //build language detector:
        LanguageDetector languageDetector = LanguageDetectorBuilder.create(NgramExtractors.standard())
                .withProfiles(languageProfiles)
                .minimalConfidence(0.99d)
                .build();

//create a text object factory
        TextObjectFactory textObjectFactory = CommonTextObjectFactories.forDetectingOnLargeText();
//        TextObjectFactory textObjectFactory = CommonTextObjectFactories.forDetectingShortCleanText();

//query:
//        TextObject textObject = textObjectFactory.forText("this is my bag");
        TextObject textObject = textObjectFactory.forText(persianStr);
        // NOTE: if you want to select this RTL string, need only there RTL string not include others.

        Optional<LdLocale> lang = languageDetector.detect(textObject);
        if (lang.isPresent()) {
            System.out.println(lang.get().toString());
        }else {
            System.out.println("cannot get the result");
        }
    }

    /**
     * https://detectlanguage.com/
     * https://github.com/detectlanguage/detectlanguage-java
     * @throws IOException
     */
    @Test
    public void languageDetectApiTest() throws IOException, APIError {
        DetectLanguage.apiKey = "91ff5462776798ad617da86fdc6131aa";

        // Enable secure mode (SSL) if passing sensitive information
        // DetectLanguage.ssl = true;
        List<Result> results = DetectLanguage.detect(persianStr);

        Result result = results.get(0);

        System.out.println("Language: " + result.language);
        System.out.println("Is reliable: " + result.isReliable);
        System.out.println("Confidence: " + result.confidence);
    }


    @Test
    public void languageDetectShuyoTest() throws LangDetectException {
        DetectorFactory.loadProfile(LanguageDetectionQuickTests.class.getResource("/profiles").getPath());

        Detector detector = DetectorFactory.create();
        detector.append(persianStr);

        String lang = detector.detect();

        System.out.println(lang);
    }

}
