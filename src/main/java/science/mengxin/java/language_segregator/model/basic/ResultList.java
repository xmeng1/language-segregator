package science.mengxin.java.language_segregator.model.basic;

import java.io.Serializable;
import java.util.List;

public class ResultList<T> implements Serializable {

  private static final long serialVersionUID = -8653186193690777716L;

  private Integer totalSize;
  private Integer resultSize;
  private Boolean nextPage;
  private transient List<T> list;

  public ResultList(Integer totalSize, Integer resultSize, Boolean nextPage, List<T> list) {
    this.totalSize = totalSize;
    this.resultSize = resultSize;
    this.nextPage = nextPage;
    this.list = list;
  }

  public ResultList() {
  }

  public Integer getTotalSize() {
    return totalSize;
  }

  public void setTotalSize(Integer totalSize) {
    this.totalSize = totalSize;
  }

  public Integer getResultSize() {
    return resultSize;
  }

  public void setResultSize(Integer resultSize) {
    this.resultSize = resultSize;
  }

  public Boolean getNextPage() {
    return nextPage;
  }

  public void setNextPage(Boolean nextPage) {
    this.nextPage = nextPage;
  }

  public List<T> getList() {
    return list;
  }

  public void setList(List<T> list) {
    this.list = list;
  }
}
