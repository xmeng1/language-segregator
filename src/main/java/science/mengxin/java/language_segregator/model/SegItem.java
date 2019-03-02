package science.mengxin.java.language_segregator.model;

import java.util.List;
import java.util.Map;
import science.mengxin.java.language_segregator.model.options.SupportLang;

public class SegItem {

  private SupportLang language;
  private List<Integer> titleSeqNums;
  private Map<Integer, String> contents;

  public SegItem() {
  }

  public SegItem(SupportLang language, List<Integer> titleSeqNums,
      Map<Integer, String> contents) {
    this.language = language;
    this.titleSeqNums = titleSeqNums;
    this.contents = contents;
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

  public List<Integer> getTitleSeqNums() {
    return titleSeqNums;
  }

  public void setTitleSeqNums(List<Integer> titleSeqNums) {
    this.titleSeqNums = titleSeqNums;
  }

  @Override
  public String toString() {
    return "SegItem{" +
        "language=" + language +
        ", contents=" + contents +
        '}';
  }
}
