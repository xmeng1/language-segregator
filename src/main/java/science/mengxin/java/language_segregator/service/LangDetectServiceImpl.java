package science.mengxin.java.language_segregator.service;

import com.detectlanguage.DetectLanguage;
import com.detectlanguage.Result;
import com.detectlanguage.errors.APIError;
import com.optimaize.langdetect.LanguageDetector;
import com.optimaize.langdetect.LanguageDetectorBuilder;
import com.optimaize.langdetect.i18n.LdLocale;
import com.optimaize.langdetect.ngram.NgramExtractors;
import com.optimaize.langdetect.profiles.LanguageProfile;
import com.optimaize.langdetect.profiles.LanguageProfileReader;
import com.optimaize.langdetect.text.CommonTextObjectFactories;
import com.optimaize.langdetect.text.TextObject;
import com.optimaize.langdetect.text.TextObjectFactory;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import science.mengxin.java.language_segregator.model.options.SupportLang;

@Service
public class LangDetectServiceImpl implements LangDetectService {

  private static final Logger logger = LoggerFactory.getLogger(LangDetectServiceImpl.class);

  private Boolean status;
  private List<LanguageProfile> languageProfiles;
  private List<LanguageProfile> languageSmProfiles;
  private TextObjectFactory textLargeObjectFactory;
  private TextObjectFactory textCleanObjectFactory;

  public LangDetectServiceImpl() {
    try {
      languageProfiles = new LanguageProfileReader().readAllBuiltIn();
      // languageSmProfiles = new LanguageProfileReader().readAll(
      //     new File(LangDetectServiceImpl.class.getResource("/profiles.sm").getPath())
      // );
      languageSmProfiles = new LanguageProfileReader().readAllBuiltIn();
      textLargeObjectFactory = CommonTextObjectFactories.forDetectingOnLargeText();
      textCleanObjectFactory = CommonTextObjectFactories.forDetectingShortCleanText();
    } catch (IOException e) {
      logger.error("Initial the Language Profile failed! {}", e.getMessage());
      status = false;
    }
    status = true;
  }

  private Double determineConfidence(String source) {
    if (source.length() < 100) {
      return 0.7d;
    } else if (source.length() < 200) {
      return 0.8d;
    } else {
      return 0.9d;
    }
  }

  @Override
  public Optional<SupportLang> detect(String source) {
    return detect(source, determineConfidence(source));
  }

  @SuppressWarnings({"UnstableApiUsage", "Guava"})
  @Override
  public Optional<SupportLang> detect(String source, Double minimalConfidence) {
    if (!status) {
      try {
        languageProfiles = new LanguageProfileReader().readAllBuiltIn();
      } catch (IOException e) {
        logger.error("Initial the Language Profile failed! {}", e.getMessage());
        return Optional.empty();
      }
      status = true;
    }
    LanguageDetector languageDetector;
    TextObject textObject;
    logger.debug("the length of source {} and bytes {}", source.length(), source.getBytes().length);
    String sourceCompress = source.replaceAll("\\s", "").replaceAll("\\pP", "");
    logger.debug("remove whitespace and p {}", sourceCompress.getBytes().length);
    if (source.getBytes().length < 20 || sourceCompress.getBytes().length < 20) {
      languageDetector = LanguageDetectorBuilder.create(NgramExtractors.standard())
          .withProfiles(languageSmProfiles)
          .minimalConfidence(minimalConfidence)
          .build();
      textObject = textCleanObjectFactory.forText(source);
    } else {
      languageDetector = LanguageDetectorBuilder.create(NgramExtractors.standard())
          .withProfiles(languageProfiles)
          .minimalConfidence(minimalConfidence)
          .build();
      textObject = textLargeObjectFactory.forText(source);
    }
    // NOTE: if you want to select this RTL string, need only there RTL string not include others.
    com.google.common.base.Optional<LdLocale> lang = languageDetector.detect(textObject);

    if (lang.isPresent()) {
      logger.debug("get the lang: {} (languageCode {} & local {})", lang.get().toString(),
          lang.get().getLanguage(), lang.get().getRegion());
      return adapterLang(lang.get());
    } else {
      return Optional.empty();
    }
  }

  @Override
  public Optional<SupportLang> remoteDetect(String source) {
    DetectLanguage.apiKey = "91ff5462776798ad617da86fdc6131aa";
    logger.info("###### NOTICE: calling the remote detection API which use monthly usage.");
    // Enable secure mode (SSL) if passing sensitive information
    // DetectLanguage.ssl = true;
    List<Result> results;
    try {
      results = DetectLanguage.detect(source);
    } catch (APIError apiError) {
      logger.error("remote detection get error: {}", apiError);
      return Optional.empty();
    }
    Result result = results.get(0);
    String lang = result.language;
    if (lang.equals("zh")) {
      lang = "zh_CN";
    } else if (lang.equals("zh-Hant")) {
      lang = "zh_TW";
    }
    SupportLang supportLang;
    try {
      supportLang = SupportLang.valueOf(lang.toUpperCase());
    } catch (Exception e) {
      logger.error("the remote api return language code not compatible with local. {}", lang);
      return Optional.empty();
    }
    return Optional.of(supportLang);
  }

  @Override
  public Optional<SupportLang> adapterLang(LdLocale ldLocale) {
    SupportLang supportLang;
    String ldLocalLang;
    if (ldLocale.getRegion().isPresent()) {
      ldLocalLang = (ldLocale.getLanguage() + "_" + ldLocale.getRegion().get()).toUpperCase();
    } else {
      ldLocalLang = ldLocale.getLanguage().toUpperCase();
    }
    try {
      supportLang = SupportLang.valueOf(ldLocalLang);
    } catch (Exception e) {
      logger.error("Exchange the language code [{}] from the detector get error: {}",
          ldLocale.getLanguage(), e.getMessage());
      return Optional.empty();
    }
    return Optional.of(supportLang);
  }
}
