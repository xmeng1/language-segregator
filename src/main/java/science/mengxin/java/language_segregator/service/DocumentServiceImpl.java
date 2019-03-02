package science.mengxin.java.language_segregator.service;

import java.io.IOException;
import java.io.InputStream;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DocumentServiceImpl implements DocumentService {

  @Override
  public String parseDoc(MultipartFile multipartFile){
    InputStream stream = null;
    try {
      stream = multipartFile.getInputStream();
    } catch (IOException e) {
      e.printStackTrace();
    }

    // Parsing using the Tika Facade
    Tika tika = new Tika();

    String result1 = null;
    try {
      result1 = tika.parseToString(stream);
    } catch (TikaException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return result1;
  }
}
