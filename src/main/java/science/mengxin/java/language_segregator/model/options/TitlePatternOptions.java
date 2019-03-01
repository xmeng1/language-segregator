package science.mengxin.java.language_segregator.model.options;

public class TitlePatternOptions {

  private Boolean filterByLang = true;
  private SupportLang lang = SupportLang.EN;
  private Boolean filterByCase = true;
  private Boolean allUpperCase = true;
  private Boolean filterByLength = false;
  private Integer lengthThreshold;
  private Boolean filterBySubString = false;
  private String specialSubString;

  public TitlePatternOptions() {

  }

  public TitlePatternOptions(Boolean filterByLang, SupportLang lang, Boolean filterByCase,
      Boolean allUpperCase,
      Boolean filterByLength, Integer lengthThreshold, Boolean filterBySubString,
      String specialSubString) {
    this.filterByLang = filterByLang;
    this.lang = lang;
    this.filterByCase = filterByCase;
    this.allUpperCase = allUpperCase;
    this.filterByLength = filterByLength;
    this.lengthThreshold = lengthThreshold;
    this.filterBySubString = filterBySubString;
    this.specialSubString = specialSubString;
  }

  public Boolean getFilterByLang() {
    return filterByLang;
  }

  public SupportLang getLang() {
    return lang;
  }

  public Boolean getFilterByCase() {
    return filterByCase;
  }

  public Boolean getAllUpperCase() {
    return allUpperCase;
  }

  public Boolean getFilterByLength() {
    return filterByLength;
  }

  public Integer getLengthThreshold() {
    return lengthThreshold;
  }

  public Boolean getFilterBySubString() {
    return filterBySubString;
  }

  public String getSpecialSubString() {
    return specialSubString;
  }

  @Override
  public String toString() {
    return "TitlePatternOptions{" +
        "filterByLang=" + filterByLang +
        ", lang=" + lang +
        ", filterByCase=" + filterByCase +
        ", allUpperCase=" + allUpperCase +
        ", filterByLength=" + filterByLength +
        ", lengthThreshold=" + lengthThreshold +
        ", filterBySubString=" + filterBySubString +
        ", specialSubString='" + specialSubString + '\'' +
        '}';
  }
}
