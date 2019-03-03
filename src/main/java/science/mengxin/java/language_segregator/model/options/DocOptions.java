package science.mengxin.java.language_segregator.model.options;

import java.util.List;
import java.util.Set;

public class DocOptions {

  private Boolean supportTitle = true;
  private Boolean supportMapping = true;
  private BlockSeparator blockSeparator = BlockSeparator.LINE_BREAK;
  private String blockSeparatorRex; // if BlockSeparator.REGEX_EXPRESS
  private Set<SupportLang> langList;

  public DocOptions() {
  }

  public DocOptions(
      Set<SupportLang> langList) {
    this.langList = langList;
  }

  public DocOptions(Boolean supportTitle, Boolean supportMapping,
      BlockSeparator blockSeparator, String blockSeparatorRex,
      Set<SupportLang> langList) {
    this.supportTitle = supportTitle;
    this.supportMapping = supportMapping;
    this.blockSeparator = blockSeparator;
    this.blockSeparatorRex = blockSeparatorRex;
    this.langList = langList;
  }

  public Boolean getSupportTitle() {
    return supportTitle;
  }

  public Boolean getSupportMapping() {
    return supportMapping;
  }

  public BlockSeparator getBlockSeparator() {
    return blockSeparator;
  }

  public String getBlockSeparatorRex() {
    return blockSeparatorRex;
  }


  public Set<SupportLang> getLangList() {
    return langList;
  }

  public void setLangList(
      Set<SupportLang> langList) {
    this.langList = langList;
  }

  @Override
  public String toString() {
    return "DocOptions{" +
        "supportTitle=" + supportTitle +
        ", supportMapping=" + supportMapping +
        ", blockSeparator=" + blockSeparator +
        ", blockSeparatorRex='" + blockSeparatorRex + '\'' +
        ", langList=" + langList +
        '}';
  }
}
