package science.mengxin.java.language_segregator.model.basic;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;

public interface IResult<T> extends Serializable {

  T getResult();

  void setResult(T result);

  String getMessage();

  void setMessage(String message);

  int getError();

  void setError(int error);

  String toString();
}
