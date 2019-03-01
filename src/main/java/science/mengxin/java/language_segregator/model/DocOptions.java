package science.mengxin.java.language_segregator.model;

public class DocOptions {

  private Boolean supportTitle = true;
  private Boolean supportMapping = true;
  private BlockSeparator blockSeparator = BlockSeparator.BLANK_LINE;
  private String blockSeparatorStr; // if BlockSeparator.SPECIAL_STRING

  public DocOptions() {
  }

  public DocOptions(Boolean supportTitle, Boolean supportMapping,
      BlockSeparator blockSeparator, String blockSeparatorStr) {
    this.supportTitle = supportTitle;
    this.supportMapping = supportMapping;
    this.blockSeparator = blockSeparator;
    this.blockSeparatorStr = blockSeparatorStr;
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

  public String getBlockSeparatorStr() {
    return blockSeparatorStr;
  }
}
