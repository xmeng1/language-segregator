package science.mengxin.java.language_segregator.model;

import java.util.List;
import science.mengxin.java.language_segregator.model.options.SupportLang;

public class SegItem {
  private SupportLang supportLang;
  private List<String> contents;

  public SegItem() {
  }

  public SegItem(SupportLang supportLang, List<String> contents) {
    this.supportLang = supportLang;
    this.contents = contents;
  }

  public SupportLang getSupportLang() {
    return supportLang;
  }

  public void setSupportLang(
      SupportLang supportLang) {
    this.supportLang = supportLang;
  }

  public List<String> getContents() {
    return contents;
  }

  public void setContents(List<String> contents) {
    this.contents = contents;
  }

  @Override
  public String toString() {
    return "SegItem{" +
        "supportLang=" + supportLang +
        ", contents=" + contents +
        '}';
  }
}
