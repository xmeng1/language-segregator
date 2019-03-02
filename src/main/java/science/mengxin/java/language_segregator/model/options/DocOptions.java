package science.mengxin.java.language_segregator.model.options;

public class DocOptions {

  private Boolean supportTitle = true;
  private Boolean supportMapping = true;
  private BlockSeparator blockSeparator = BlockSeparator.LINE_BREAK;
  private String blockSeparatorRex; // if BlockSeparator.REGEX_EXPRESS

  public DocOptions() {
  }

  public DocOptions(Boolean supportTitle, Boolean supportMapping,
      BlockSeparator blockSeparator, String blockSeparatorRex) {
    this.supportTitle = supportTitle;
    this.supportMapping = supportMapping;
    this.blockSeparator = blockSeparator;
    this.blockSeparatorRex = blockSeparatorRex;
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

  @Override
  public String toString() {
    return "DocOptions{" +
        "supportTitle=" + supportTitle +
        ", supportMapping=" + supportMapping +
        ", blockSeparator=" + blockSeparator +
        ", blockSeparatorRex='" + blockSeparatorRex + '\'' +
        '}';
  }
}
