package science.mengxin.java.language_segregator.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GitVersionUtils {

  private static final Logger logger = LoggerFactory.getLogger(GitVersionUtils.class);

  public static Optional<GitVersion> getGitVersion(){
    String gitVersionStr = versionInformation();
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      GitVersion gitVersion = objectMapper.readValue(gitVersionStr, GitVersion.class);
      return Optional.of(gitVersion);
    } catch (IOException e) {
      logger.warn("Get the git version information exception: {}", e.getMessage());
      return Optional.empty();
    }
  }

  public static String versionInformation() {
    return readGitProperties();
  }

  private static String readFromInputStream(InputStream inputStream)
      throws IOException {
    StringBuilder resultStringBuilder = new StringBuilder();
    try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
      String line;
      while ((line = br.readLine()) != null) {
        resultStringBuilder.append(line).append("\n");
      }
    }
    return resultStringBuilder.toString();
  }

  private static String readGitProperties() {
    ClassLoader classLoader = GitVersionUtils.class.getClassLoader();
    InputStream inputStream = classLoader.getResourceAsStream("git.properties");
    try {
      return readFromInputStream(inputStream);
    } catch (IOException e) {
      e.printStackTrace();
      return "Version information could not be retrieved";
    }
  }

}
