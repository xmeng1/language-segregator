package science.mengxin.java.language_segregator.model.basic;

public enum Status {
  INIT(-1, "Initial"),
  OK(0, "OK"),
  ERROR(400, "ERROR");

  private final int code;
  private final String reason;

  Status(final int statusCode, final String reason) {
    this.code = statusCode;
    this.reason = reason;
  }

  public int getCode() {
    return code;
  }

  public String getReason() {
    return reason;
  }
}
