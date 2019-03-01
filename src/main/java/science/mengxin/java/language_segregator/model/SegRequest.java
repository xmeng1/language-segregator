package science.mengxin.java.language_segregator.model;

import science.mengxin.java.language_segregator.model.options.DocOptions;
import science.mengxin.java.language_segregator.model.options.TitlePatternOptions;

public class SegRequest {
  private String source;
  private DocOptions docOptions;
  private TitlePatternOptions titlePatternOptions;

  public SegRequest(String source,
      DocOptions docOptions,
      TitlePatternOptions titlePatternOptions) {
    this.source = source;
    this.docOptions = docOptions;
    this.titlePatternOptions = titlePatternOptions;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public DocOptions getDocOptions() {
    return docOptions;
  }

  public void setDocOptions(DocOptions docOptions) {
    this.docOptions = docOptions;
  }

  public TitlePatternOptions getTitlePatternOptions() {
    return titlePatternOptions;
  }

  public void setTitlePatternOptions(
      TitlePatternOptions titlePatternOptions) {
    this.titlePatternOptions = titlePatternOptions;
  }

  @Override
  public String toString() {
    return "SegRequest{" +
        "source='" + source + '\'' +
        ", docOptions=" + docOptions +
        ", titlePatternOptions=" + titlePatternOptions +
        '}';
  }
}
