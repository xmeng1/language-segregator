package science.mengxin.java.language_segregator;


import com.neovisionaries.i18n.LanguageAlpha3Code;
import com.neovisionaries.i18n.LanguageCode;
import com.neovisionaries.i18n.LocaleCode;

import java.util.EnumSet;
import java.util.Set;

public class SystemConfig {

  public static final Set<LanguageAlpha3Code> supportLang6392 = EnumSet.of(
      LanguageAlpha3Code.ast // ast Asturian
  );
  public static final Set<LanguageCode> supportLang6391 = EnumSet.of(
      LanguageCode.af,// Afrikaans
      LanguageCode.an,// Aragonese
      LanguageCode.ar,// Arabic
      LanguageCode.be,// Belarusian
      LanguageCode.br,// Breton
      LanguageCode.ca,// Catalan
      LanguageCode.bg,// Bulgarian
      LanguageCode.bn,// Bengali
      LanguageCode.cs,// Czech
      LanguageCode.cy,// Welsh
      LanguageCode.da,// Danish
      LanguageCode.de,// German
      LanguageCode.el,// Greek
      LanguageCode.en,// English
      LanguageCode.es,// Spanish
      LanguageCode.et,// Estonian
      LanguageCode.eu,// Basque
      LanguageCode.fa,// Persian
      LanguageCode.fi,// Finnish
      LanguageCode.fr,// French
      LanguageCode.ga,// Irish
      LanguageCode.gl,// Galician
      LanguageCode.gu,// Gujarati
      LanguageCode.he,// Hebrew
      LanguageCode.hi,// Hindi
      LanguageCode.hr,// Croatian
      LanguageCode.ht,// Haitian
      LanguageCode.hu,// Hungarian
      LanguageCode.id,// Indonesian
      LanguageCode.is,// Icelandic
      LanguageCode.it,// Italian
      LanguageCode.ja,// Japanese
      LanguageCode.km,// Khmer
      LanguageCode.kn,// Kannada
      LanguageCode.ko,// Korean
      LanguageCode.lt,// Lithuanian
      LanguageCode.lv,// Latvian
      LanguageCode.mk,// Macedonian
      LanguageCode.ml,// Malayalam
      LanguageCode.mr,// Marathi
      LanguageCode.ms,// Malay
      LanguageCode.mt,// Maltese
      LanguageCode.ne,// Nepali
      LanguageCode.nl,// Dutch
      LanguageCode.no,// Norwegian
      LanguageCode.oc,// Occitan
      LanguageCode.pa,// Punjabi
      LanguageCode.pl,// Polish
      LanguageCode.pt,// Portuguese
      LanguageCode.ro,// Romanian
      LanguageCode.ru,// Russian
      LanguageCode.sk,// Slovak
      LanguageCode.sl,// Slovene
      LanguageCode.so,// Somali
      LanguageCode.sq,// Albanian
      LanguageCode.sr,// Serbian
      LanguageCode.sv,// Swedish
      LanguageCode.sw,// Swahili
      LanguageCode.ta,// Tamil
      LanguageCode.te,// Telugu
      LanguageCode.th,// Thai
      LanguageCode.tl,// Tagalog
      LanguageCode.tr,// Turkish
      LanguageCode.uk,// Ukrainian
      LanguageCode.ur,// Urdu
      LanguageCode.vi,// Vietnamese
      LanguageCode.wa,// Walloon
      LanguageCode.yi// Yiddish
//            LanguageCode.zh,//-cn Simplified Chinese
//            LanguageCode.zh//-tw Traditional Chinese
  );

  public static final Set<LocaleCode> supportLocales = EnumSet.of(
      LocaleCode.zh_CN,
      LocaleCode.zh_TW
  );
}
