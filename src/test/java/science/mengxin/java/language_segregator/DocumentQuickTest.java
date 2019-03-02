package science.mengxin.java.language_segregator;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.BodyContentHandler;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.xml.sax.SAXException;

@SpringBootTest
public class DocumentQuickTest {

  static final String docRootPath = "/documents/";
  static final String doc1Name = "multi_lang_doc";
  static final String pdfExt = ".pdf";
  static final String rtfExt = ".rtf";
  static final String docxExt = ".docx";
  static final String odtExt = ".odt";
  static final String markdownExt = ".md";
  static final String txtExt = ".txt";

  static final String doc1FullPath = docRootPath + doc1Name;

  @Test
  public void rtfDocTest() throws IOException, TikaException, SAXException {
    InputStream stream = DocumentQuickTest.class
        .getResourceAsStream(doc1FullPath + rtfExt);

    // Parsing using the Tika Facade
    Tika tika = new Tika();

    String result1 = tika.parseToString(stream);
    System.out.println(result1);
  }

  @Test
  public void rtfDocAndSplitTest() throws IOException, TikaException {
    InputStream stream = DocumentQuickTest.class
        .getResourceAsStream(doc1FullPath + rtfExt);

    // Parsing using the Tika Facade
    Tika tika = new Tika();

    String result1 = tika.parseToString(stream);

    String[] splitResult = result1.split("\\n\\n");

    System.out.println(splitResult.length);
    System.out.println(Arrays.toString(splitResult));
  }

  @Test
  public void rtf2DocTest() throws IOException, TikaException, SAXException {
    InputStream stream2 = DocumentQuickTest.class
        .getResourceAsStream(doc1FullPath + rtfExt);
    AutoDetectParser parser = new AutoDetectParser();
    BodyContentHandler handler = new BodyContentHandler();
    Metadata metadata = new Metadata();
    parser.parse(stream2, handler, metadata);
    System.out.println(handler.toString());
  }


  @Test
  public void pdfDocTest() throws IOException, TikaException {
    InputStream stream = DocumentQuickTest.class
        .getResourceAsStream(doc1FullPath + pdfExt);

    // Parsing using the Tika Facade
    Tika tika = new Tika();

    String result1 = tika.parseToString(stream);
    System.out.println(result1);
  }

  @Test
  public void wordDocTest() throws IOException, TikaException {
    InputStream stream = DocumentQuickTest.class
        .getResourceAsStream(doc1FullPath + docxExt);

    // Parsing using the Tika Facade
    Tika tika = new Tika();

    String result1 = tika.parseToString(stream);
    System.out.println(result1);
  }

  @Test
  public void odtDocTest() throws IOException, TikaException {
    InputStream stream = DocumentQuickTest.class
        .getResourceAsStream(doc1FullPath + odtExt);

    // Parsing using the Tika Facade
    Tika tika = new Tika();

    String result1 = tika.parseToString(stream);
    System.out.println(result1);
  }

  @Test
  public void txtDocTest() throws IOException, TikaException {
    InputStream stream = DocumentQuickTest.class
        .getResourceAsStream(doc1FullPath + txtExt);

    // Parsing using the Tika Facade
    Tika tika = new Tika();

    String result1 = tika.parseToString(stream);
    System.out.println(result1);
  }

  @Test
  public void mdDocTest() throws IOException, TikaException {
    InputStream stream = DocumentQuickTest.class
        .getResourceAsStream(doc1FullPath + markdownExt);

    // Parsing using the Tika Facade
    Tika tika = new Tika();

    String result1 = tika.parseToString(stream);
    System.out.println(result1);
  }
}
