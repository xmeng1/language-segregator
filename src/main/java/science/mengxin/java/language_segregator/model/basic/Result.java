package science.mengxin.java.language_segregator.model.basic;

public class Result<T> implements IResult<T> {

  private static final long serialVersionUID = -5199960083536283425L;
  //Store Object of Return
  private transient T resultObj;

  //Error Code
  private int error;

  private String message;

  @Override
  public T getResult() {
    return resultObj;
  }

  @Override
  public void setResult(T result) {
    this.resultObj = result;
  }

  @Override
  public String getMessage() {
    return message;
  }

  @Override
  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public int getError() {
    return error;
  }

  @Override
  public void setError(int error) {
    this.error = error;
  }

  @Override
  public String toString() {
    return "Result{" +
        "resultObj=" + resultObj +
        ", error=" + error +
        ", message='" + message + '\'' +
        '}';
  }

  public static Result ok() {
    final Result r = new Result();
    r.setError(Status.OK.getCode());
    r.setMessage(Status.OK.getReason());
    return r;
  }

  public static <T> Result<T> ok(T t) {
    final Result<T> r = new Result<>();
    r.setError(Status.OK.getCode());
    r.setMessage(Status.OK.getReason());
    r.setResult(t);
    return r;
  }

  public static <T> Result<T> build(int error, String message, T result) {
    final Result<T> r = new Result<>();
    r.setError(error);
    r.setMessage(message);
    r.setResult(result);
    return r;
  }

}
