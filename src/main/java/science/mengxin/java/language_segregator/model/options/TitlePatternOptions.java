package science.mengxin.java.language_segregator.model.options;

public class TitlePatternOptions {

  // filter, if match continue, or return false
  private Boolean filterByLang = false;
  private SupportLang lang = SupportLang.EN;

  // First Priority
  private Boolean filterByCase = true;
  private Boolean allUpperCase = true;

  // Second Priority
  private Boolean filterByLength = false;
  private Integer lengthThreshold = 50;

  // Third Priority
  private Boolean filterByRegex = false;
  private String regexPattern = "";

  public TitlePatternOptions() {

  }

  public TitlePatternOptions(Boolean filterByLang, SupportLang lang, Boolean filterByCase,
      Boolean allUpperCase,
      Boolean filterByLength, Integer lengthThreshold, Boolean filterByRegex,
      String regexPattern) {
    this.filterByLang = filterByLang;
    this.lang = lang;
    this.filterByCase = filterByCase;
    this.allUpperCase = allUpperCase;
    this.filterByLength = filterByLength;
    this.lengthThreshold = lengthThreshold;
    this.filterByRegex = filterByRegex;
    this.regexPattern = regexPattern;
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

  public Boolean getFilterByRegex() {
    return filterByRegex;
  }

  public String getRegexPattern() {
    return regexPattern;
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
        ", filterByRegex=" + filterByRegex +
        ", regexPattern='" + regexPattern + '\'' +
        '}';
  }
}
