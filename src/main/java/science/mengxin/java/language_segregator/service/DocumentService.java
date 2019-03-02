package science.mengxin.java.language_segregator.service;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public interface DocumentService {


  public String parseDoc(MultipartFile multipartFile);
}
