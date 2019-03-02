package science.mengxin.java.language_segregator;

public final class Version {

  private static final String VERSION = "${project.version}";
  private static final String GROUPID = "${project.groupId}";
  private static final String ARTIFACTID = "${project.artifactId}";
  private static final String REVISION = "${git.commit.id.abbrev}";

  public static String getVersion() {
    return VERSION;
  }

  public static String getArtifactId() {
    return ARTIFACTID;
  }
  public static String getGroupId() {
    return GROUPID;
  }


  public static String getRevision() {
    return REVISION;
  }

}
