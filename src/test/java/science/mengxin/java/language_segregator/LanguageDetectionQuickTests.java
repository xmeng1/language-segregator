package science.mengxin.java.language_segregator;

import com.cybozu.labs.langdetect.Detector;
import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.LangDetectException;
import com.detectlanguage.DetectLanguage;
import com.detectlanguage.Result;
import com.detectlanguage.errors.APIError;
import com.google.common.base.Optional;
import com.neovisionaries.i18n.LanguageCode;
import com.neovisionaries.i18n.LocaleCode;
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
  String mixStr = "This is a bag 两国领导人在国际媒体前握手并拍照，有说有笑，随后接受记者访问。金正恩表示，确信\"金特会\"会取得所有人都满意的结果，他说：“我们克服了所有阻碍，今日来到了这里。”美国总统特朗普则表示，他期待这次峰会是一次“非常成功”的峰会。他说：“能和金委员长在一起是一种荣誉。”";
  String mixStr5 = "两国领导人在国际媒体前握手并拍照，有说有笑，随后接受记者访问。金正恩表示，确信\"金特会\"会取得所有人都满意的结果，他说：“我们克服了所有阻碍，今日来到了这里。”美国总统特朗普则表示，他期待这次峰会是一次“非常成功”的峰会。他说：“能和金委员长在一起是一种荣誉。”";
  String mixStr3 = "但是我们不能使用它，This is a bag,  why?";
  String mixStr2 = "This is a bag, 但是我们不能使用它，why?";


  String longZhTwSource = "OLED的簡單結構也意味著您可以將這種稱為“激勵器”的東西附加到顯示器的背面。 現在，那些非常非常薄的東西將整個顯示器轉變為揚聲器。 我認為，在這個屏幕的背面有大約5個激勵器，我不得不說，它確實聽起來像音頻來自圖片的正確部分 - 這基本上是因為它是。";

  /**
   * https://github.com/optimaize/language-detector
   *
   * @throws IOException IOException
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
    } else {
      System.out.println("cannot get the result");
    }
  }

  @Test
  public void languageDetectZhTwTest() throws IOException {
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
    TextObject textObject = textObjectFactory.forText(longZhTwSource);
    // NOTE: if you want to select this RTL string, need only there RTL string not include others.

    Optional<LdLocale> lang = languageDetector.detect(textObject);
    if (lang.isPresent()) {
      System.out.println(lang.get().toString());
    } else {
      System.out.println("cannot get the result");
    }
  }

  @Test
  public void languageDetectMixTest() throws IOException {
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
    TextObject textObject = textObjectFactory.forText(mixStr);
    // NOTE: if you want to select this RTL string, need only there RTL string not include others.

    Optional<LdLocale> lang = languageDetector.detect(textObject);
    if (lang.isPresent()) {
      System.out.println(lang.get().toString());
    } else {
      System.out.println("cannot get the result");
    }
  }

  /**
   * https://detectlanguage.com/ https://github.com/detectlanguage/detectlanguage-java
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
  public void languageDetectApiZhTwTest() throws IOException, APIError {
    DetectLanguage.apiKey = "91ff5462776798ad617da86fdc6131aa";

    // Enable secure mode (SSL) if passing sensitive information
    // DetectLanguage.ssl = true;
    List<Result> results = DetectLanguage.detect(longZhTwSource);

    Result result = results.get(0);

    System.out.println("Language: " + result.language);
    System.out.println("Is reliable: " + result.isReliable);
    System.out.println("Confidence: " + result.confidence);
  }

  @Test
  public void languageDetectApiMixTest() throws IOException, APIError {
    DetectLanguage.apiKey = "91ff5462776798ad617da86fdc6131aa";

    // Enable secure mode (SSL) if passing sensitive information
    // DetectLanguage.ssl = true;
    List<Result> results = DetectLanguage.detect(mixStr);

    Result result = results.get(0);

    System.out.println("Language: " + result.language);
    System.out.println("Is reliable: " + result.isReliable);
    System.out.println("Confidence: " + result.confidence);
  }

  @Test
  public void languageDetectShuyoTest() throws LangDetectException {
    DetectorFactory
        .loadProfile(LanguageDetectionQuickTests.class.getResource("/profiles").getPath());

    Detector detector = DetectorFactory.create();
    detector.append(persianStr);

    String lang = detector.detect();

    System.out.println(lang);
  }

  @Test
  public void languageDetectMixShuyoTest() throws LangDetectException {
    DetectorFactory
        .loadProfile(LanguageDetectionQuickTests.class.getResource("/profiles").getPath());

    Detector detector = DetectorFactory.create();
    detector.append(mixStr);

    String lang = detector.detect();

    System.out.println(lang);
  }

  @Test
  public void languageDetectZhTwShuyoTest() throws LangDetectException {
    DetectorFactory
        .loadProfile(LanguageDetectionQuickTests.class.getResource("/profiles").getPath());

    Detector detector = DetectorFactory.create();
    detector.append(longZhTwSource);

    String lang = detector.detect();

    System.out.println(lang);
  }

  @Test
  public void listLanguageTest() {
    for (LanguageCode code : LanguageCode.values()) {
      // For example, "[ar] Arabic" is printed.</span>
      System.out.format("[%s] %s\n", code, code.getName());
    }

    for (LocaleCode localeCode : LocaleCode.values()) {
      System.out
          .format("[%s] %s %s\n", localeCode, localeCode.getCountry(), localeCode.getLanguage());
    }
  }

}
