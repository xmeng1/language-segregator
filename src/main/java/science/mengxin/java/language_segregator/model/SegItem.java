package science.mengxin.java.language_segregator.model;

import java.util.List;
import java.util.Map;
import science.mengxin.java.language_segregator.model.options.SupportLang;

public class SegItem {

  private SupportLang language;

  private Map<Integer, String> contents;
  private Map<Integer, String> titles;

  public SegItem() {
  }


  public SegItem(SupportLang language, Map<Integer, String> contents,
      Map<Integer, String> titles) {
    this.language = language;
    this.contents = contents;
    this.titles = titles;
  }

  public SupportLang getLanguage() {
    return language;
  }

  public void setLanguage(
      SupportLang language) {
    this.language = language;
  }

  public Map<Integer, String> getContents() {
    return contents;
  }

  public void setContents(Map<Integer, String> contents) {
    this.contents = contents;
  }

  public Map<Integer, String> getTitles() {
    return titles;
  }

  public void setTitles(Map<Integer, String> titles) {
    this.titles = titles;
  }

  @Override
  public String toString() {
    return "SegItem{" +
        "language=" + language +
        ", contents=" + contents +
        ", titles=" + titles +
        '}';
  }
}
