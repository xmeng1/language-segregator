package science.mengxin.java.language_segregator.utilities;


import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * {
 *   "git.branch" : "develop",
 *   "git.build.host" : "Xin-Mac-mini",
 *   "git.build.time" : "2019-03-02T11:04:32+0000",
 *   "git.build.user.email" : "x.meng@outlook.com",
 *   "git.build.user.name" : "Xin Meng",
 *   "git.build.version" : "0.0.1",
 *   "git.closest.tag.commit.count" : "",
 *   "git.closest.tag.name" : "",
 *   "git.commit.id" : "a1b729dd3bdea7568fa4206fdbafcc8ef66810d1",
 *   "git.commit.id.abbrev" : "a1b729d",
 *   "git.commit.id.describe" : "a1b729d-dirty",
 *   "git.commit.id.describe-short" : "a1b729d-dirty",
 *   "git.commit.message.full" : "finish the skeleton rest api for split document",
 *   "git.commit.message.short" : "finish the skeleton rest api for split document",
 *   "git.commit.time" : "2019-03-02T10:27:05+0000",
 *   "git.commit.user.email" : "x.meng@outlook.com",
 *   "git.commit.user.name" : "Xin Meng",
 *   "git.dirty" : "true",
 *   "git.remote.origin.url" : "git@gitlab.com:mengxin/language-segregator.git",
 *   "git.tags" : "",
 *   "git.total.commit.count" : "11"
 * }
 *
 *
 */
public class GitVersion {
  @JsonProperty("git.branch")
  public String branch;
  @JsonProperty("git.build.host")
  public String buildHost;
  @JsonProperty("git.build.time")
  public String buildTime;
  @JsonProperty("git.build.user.email")
  public String buildUserEmail;
  @JsonProperty("git.build.user.name")
  public String buildUserName;
  @JsonProperty("git.build.version")
  public String buildVersion;
  @JsonProperty("git.closest.tag.commit.count")
  public String closestTagCommitCount;
  @JsonProperty("git.closest.tag.name")
  public String closestTagName;
  @JsonProperty("git.commit.id")
  public String commitId;
  @JsonProperty("git.commit.id.abbrev")
  public String commitIdAbbrev;
  @JsonProperty("git.commit.id.describe")
  public String commitIdDescribe;
  @JsonProperty("git.commit.id.describe-short")
  public String commitIdDescribeShort;
  @JsonProperty("git.commit.message.full")
  public String commitMessageFull;
  @JsonProperty("git.commit.message.short")
  public String commitMessageShort;
  @JsonProperty("git.commit.time")
  public String commitTime;
  @JsonProperty("git.commit.user.email")
  public String commitUserEmail;
  @JsonProperty("git.commit.user.name")
  public String commitUserName;
  @JsonProperty("git.dirty")
  public String dirty;
  @JsonProperty("git.remote.origin.url")
  public String remoteOriginUrl;
  @JsonProperty("git.tags")
  public String tags;
  @JsonProperty("git.total.commit.count")
  public String totalCommitCount;
}
