package science.mengxin.java.language_segregator.model.options;

import com.neovisionaries.i18n.LanguageAlpha3Code;
import com.neovisionaries.i18n.LanguageCode;
import com.neovisionaries.i18n.LocaleCode;

public enum SupportLang {
  AF(0, null, LanguageCode.af, null, null),
  AN(0, null, LanguageCode.an, null, null),
  AR(0, null, LanguageCode.ar, null, null),
  BE(0, null, LanguageCode.be, null, null),
  BR(0, null, LanguageCode.br, null, null),
  CA(0, null, LanguageCode.ca, null, null),
  BG(0, null, LanguageCode.bg, null, null),
  BN(0, null, LanguageCode.bn, null, null),
  CS(0, null, LanguageCode.cs, null, null),
  CY(0, null, LanguageCode.cy, null, null),
  DA(0, null, LanguageCode.da, null, null),
  DE(0, null, LanguageCode.de, null, null),
  EL(0, null, LanguageCode.el, null, null),
  EN(0, null, LanguageCode.en, null, null),
  ES(0, null, LanguageCode.es, null, null),
  ET(0, null, LanguageCode.et, null, null),
  EU(0, null, LanguageCode.eu, null, null),
  FA(0, null, LanguageCode.fa, null, null),
  FI(0, null, LanguageCode.fi, null, null),
  FR(0, null, LanguageCode.fr, null, null),
  GA(0, null, LanguageCode.ga, null, null),
  GL(0, null, LanguageCode.gl, null, null),
  GU(0, null, LanguageCode.gu, null, null),
  HE(0, null, LanguageCode.he, null, null),
  HI(0, null, LanguageCode.hi, null, null),
  HR(0, null, LanguageCode.hr, null, null),
  HT(0, null, LanguageCode.ht, null, null),
  HU(0, null, LanguageCode.hu, null, null),
  ID(0, null, LanguageCode.id, null, null),
  IS(0, null, LanguageCode.is, null, null),
  IT(0, null, LanguageCode.it, null, null),
  JA(0, null, LanguageCode.ja, null, null),
  KM(0, null, LanguageCode.km, null, null),
  KN(0, null, LanguageCode.kn, null, null),
  KO(0, null, LanguageCode.ko, null, null),
  LT(0, null, LanguageCode.lt, null, null),
  LV(0, null, LanguageCode.lv, null, null),
  MK(0, null, LanguageCode.mk, null, null),
  ML(0, null, LanguageCode.ml, null, null),
  MR(0, null, LanguageCode.mr, null, null),
  MS(0, null, LanguageCode.ms, null, null),
  MT(0, null, LanguageCode.mt, null, null),
  NE(0, null, LanguageCode.ne, null, null),
  NL(0, null, LanguageCode.nl, null, null),
  NO(0, null, LanguageCode.no, null, null),
  OC(0, null, LanguageCode.oc, null, null),
  PA(0, null, LanguageCode.pa, null, null),
  PL(0, null, LanguageCode.pl, null, null),
  PT(0, null, LanguageCode.pt, null, null),
  RO(0, null, LanguageCode.ro, null, null),
  RU(0, null, LanguageCode.ru, null, null),
  SK(0, null, LanguageCode.sk, null, null),
  SL(0, null, LanguageCode.sl, null, null),
  SO(0, null, LanguageCode.so, null, null),
  SQ(0, null, LanguageCode.sq, null, null),
  SR(0, null, LanguageCode.sr, null, null),
  SV(0, null, LanguageCode.sv, null, null),
  SW(0, null, LanguageCode.sw, null, null),
  TA(0, null, LanguageCode.ta, null, null),
  TE(0, null, LanguageCode.te, null, null),
  TH(0, null, LanguageCode.th, null, null),
  TL(0, null, LanguageCode.tl, null, null),
  TR(0, null, LanguageCode.tr, null, null),
  UK(0, null, LanguageCode.uk, null, null),
  UR(0, null, LanguageCode.ur, null, null),
  VI(0, null, LanguageCode.vi, null, null),
  WA(0, null, LanguageCode.wa, null, null),
  YI(0, null, LanguageCode.yi, null, null),
  AST(1, null, null, LanguageAlpha3Code.ast, null),
  ZH_CN(2, null, null, null, LocaleCode.zh_CN),
  ZH_TW(2, null, null, null, LocaleCode.zh_TW);

  private final String code;          // Short code
  private final String description;   // English Description
  private final Integer type;         // 0:639-1, 1:639-3, 2:language with Locale
  // https://en.wikipedia.org/wiki/List_of_ISO_639-2_codes
  private final LanguageCode languageCode;
  private final LanguageAlpha3Code languageAlpha3Code;
  private final LocaleCode localeCode;

  SupportLang(Integer type, String description, LanguageCode languageCode,
      LanguageAlpha3Code languageAlpha3Code, LocaleCode localeCode) {

    this.type = type;
    this.languageCode = languageCode;
    this.languageAlpha3Code = languageAlpha3Code;
    this.localeCode = localeCode;
    if (type == 0) {
      this.description = this.languageCode.getName();
      this.code = this.languageCode.name();
    } else if (type == 1) {
      this.description = this.languageAlpha3Code.getName();
      this.code = this.languageAlpha3Code.name();
    } else {
      this.description = description;
      this.code = this.localeCode.name().toLowerCase();
    }
  }

  public String getCode() {
    return code;
  }

  public String getDescription() {
    return description;
  }

  public Integer getType() {
    return type;
  }

  public LanguageCode getLanguageCode() {
    return languageCode;
  }

  public LanguageAlpha3Code getLanguageAlpha3Code() {
    return languageAlpha3Code;
  }

  public LocaleCode getLocaleCode() {
    return localeCode;
  }
}
