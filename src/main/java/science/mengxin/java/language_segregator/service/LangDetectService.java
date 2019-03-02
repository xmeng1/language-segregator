package science.mengxin.java.language_segregator.service;

import com.optimaize.langdetect.i18n.LdLocale;
import java.util.Optional;
import science.mengxin.java.language_segregator.model.options.SupportLang;

public interface LangDetectService {

  public Optional<SupportLang> detect(String source);
  public Optional<SupportLang> detect(String source, Double minimalConfidence);

  Optional<SupportLang> adapterLang(LdLocale ldLocale);
}
