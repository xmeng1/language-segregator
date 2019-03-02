package science.mengxin.java.language_segregator.utilities;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

/**
 * { "git.branch" : "develop", "git.build.host" : "Xin-Mac-mini", "git.build.time" :
 * "2019-03-02T11:04:32+0000", "git.build.user.email" : "x.meng@outlook.com", "git.build.user.name"
 * : "Xin Meng", "git.build.version" : "0.0.1", "git.closest.tag.commit.count" : "",
 * "git.closest.tag.name" : "", "git.commit.id" : "a1b729dd3bdea7568fa4206fdbafcc8ef66810d1",
 * "git.commit.id.abbrev" : "a1b729d", "git.commit.id.describe" : "a1b729d-dirty",
 * "git.commit.id.describe-short" : "a1b729d-dirty", "git.commit.message.full" : "finish the
 * skeleton rest api for split document", "git.commit.message.short" : "finish the skeleton rest api
 * for split document", "git.commit.time" : "2019-03-02T10:27:05+0000", "git.commit.user.email" :
 * "x.meng@outlook.com", "git.commit.user.name" : "Xin Meng", "git.dirty" : "true",
 * "git.remote.origin.url" : "git@gitlab.com:mengxin/language-segregator.git", "git.tags" : "",
 * "git.total.commit.count" : "11" }
 */
public class GitVersion implements Serializable {

  private static final long serialVersionUID = 8479252120843254659L;
  private String branch;
  private String buildHost;
  private String buildTime;
  private String buildUserEmail;
  private String buildUserName;
  private String buildVersion;
  private String closestTagCommitCount;
  private String closestTagName;
  private String commitId;
  private String commitIdAbbrev;
  private String commitIdDescribe;
  private String commitIdDescribeShort;
  private String commitMessageFull;
  private String commitMessageShort;
  private String commitTime;
  private String commitUserEmail;
  private String commitUserName;
  private String dirty;
  private String remoteOriginUrl;
  private String tags;
  private String totalCommitCount;

  @JsonProperty("branch")
  public String getBranch() {
    return branch;
  }

  @JsonProperty("git.branch")
  public void setBranch(String branch) {
    this.branch = branch;
  }

  @JsonProperty("buildHost")
  public String getBuildHost() {
    return buildHost;
  }

  @JsonProperty("git.build.host")
  public void setBuildHost(String buildHost) {
    this.buildHost = buildHost;
  }

  @JsonProperty("buildTime")
  public String getBuildTime() {
    return buildTime;
  }

  @JsonProperty("git.build.time")
  public void setBuildTime(String buildTime) {
    this.buildTime = buildTime;
  }

  @JsonProperty("buildUserEmail")
  public String getBuildUserEmail() {
    return buildUserEmail;
  }

  @JsonProperty("git.build.user.email")
  public void setBuildUserEmail(String buildUserEmail) {
    this.buildUserEmail = buildUserEmail;
  }

  @JsonProperty("buildUserName")
  public String getBuildUserName() {
    return buildUserName;
  }

  @JsonProperty("git.build.user.name")
  public void setBuildUserName(String buildUserName) {
    this.buildUserName = buildUserName;
  }

  @JsonProperty("buildVersion")
  public String getBuildVersion() {
    return buildVersion;
  }

  @JsonProperty("git.build.version")
  public void setBuildVersion(String buildVersion) {
    this.buildVersion = buildVersion;
  }

  @JsonProperty("closestTagCommitCount")
  public String getClosestTagCommitCount() {
    return closestTagCommitCount;
  }

  @JsonProperty("git.closest.tag.commit.count")
  public void setClosestTagCommitCount(String closestTagCommitCount) {
    this.closestTagCommitCount = closestTagCommitCount;
  }

  @JsonProperty("closestTagName")
  public String getClosestTagName() {
    return closestTagName;
  }

  @JsonProperty("git.closest.tag.name")
  public void setClosestTagName(String closestTagName) {
    this.closestTagName = closestTagName;
  }

  @JsonProperty("commitId")
  public String getCommitId() {
    return commitId;
  }

  @JsonProperty("git.commit.id")
  public void setCommitId(String commitId) {
    this.commitId = commitId;
  }

  @JsonProperty("commitIdAbbrev")
  public String getCommitIdAbbrev() {
    return commitIdAbbrev;
  }

  @JsonProperty("git.commit.id.abbrev")
  public void setCommitIdAbbrev(String commitIdAbbrev) {
    this.commitIdAbbrev = commitIdAbbrev;
  }

  @JsonProperty("commitIdDescribe")
  public String getCommitIdDescribe() {
    return commitIdDescribe;
  }

  @JsonProperty("git.commit.id.describe")
  public void setCommitIdDescribe(String commitIdDescribe) {
    this.commitIdDescribe = commitIdDescribe;
  }

  @JsonProperty("commitIdDescribeShort")
  public String getCommitIdDescribeShort() {
    return commitIdDescribeShort;
  }

  @JsonProperty("git.commit.id.describe-short")
  public void setCommitIdDescribeShort(String commitIdDescribeShort) {
    this.commitIdDescribeShort = commitIdDescribeShort;
  }

  @JsonProperty("commitMessageFull")
  public String getCommitMessageFull() {
    return commitMessageFull;
  }

  @JsonProperty("git.commit.message.full")
  public void setCommitMessageFull(String commitMessageFull) {
    this.commitMessageFull = commitMessageFull;
  }

  @JsonProperty("commitMessageShort")
  public String getCommitMessageShort() {
    return commitMessageShort;
  }

  @JsonProperty("git.commit.message.short")
  public void setCommitMessageShort(String commitMessageShort) {
    this.commitMessageShort = commitMessageShort;
  }

  @JsonProperty("commitTime")
  public String getCommitTime() {
    return commitTime;
  }

  @JsonProperty("git.commit.time")
  public void setCommitTime(String commitTime) {
    this.commitTime = commitTime;
  }

  @JsonProperty("commitUserEmail")
  public String getCommitUserEmail() {
    return commitUserEmail;
  }

  @JsonProperty("git.commit.user.email")
  public void setCommitUserEmail(String commitUserEmail) {
    this.commitUserEmail = commitUserEmail;
  }

  @JsonProperty("commitUserName")
  public String getCommitUserName() {
    return commitUserName;
  }

  @JsonProperty("git.commit.user.name")
  public void setCommitUserName(String commitUserName) {
    this.commitUserName = commitUserName;
  }

  @JsonProperty("dirty")
  public String getDirty() {
    return dirty;
  }

  @JsonProperty("git.dirty")
  public void setDirty(String dirty) {
    this.dirty = dirty;
  }

  @JsonProperty("remoteOriginUrl")
  public String getRemoteOriginUrl() {
    return remoteOriginUrl;
  }

  @JsonProperty("git.remote.origin.url")
  public void setRemoteOriginUrl(String remoteOriginUrl) {
    this.remoteOriginUrl = remoteOriginUrl;
  }

  @JsonProperty("tags")
  public String getTags() {
    return tags;
  }

  @JsonProperty("git.tags")
  public void setTags(String tags) {
    this.tags = tags;
  }

  @JsonProperty("totalCommitCount")
  public String getTotalCommitCount() {
    return totalCommitCount;
  }

  @JsonProperty("git.total.commit.count")
  public void setTotalCommitCount(String totalCommitCount) {
    this.totalCommitCount = totalCommitCount;
  }

  @Override
  public String toString() {
    return "GitVersion{" +
        "branch='" + branch + '\'' +
        ", buildHost='" + buildHost + '\'' +
        ", buildTime='" + buildTime + '\'' +
        ", buildUserEmail='" + buildUserEmail + '\'' +
        ", buildUserName='" + buildUserName + '\'' +
        ", buildVersion='" + buildVersion + '\'' +
        ", closestTagCommitCount='" + closestTagCommitCount + '\'' +
        ", closestTagName='" + closestTagName + '\'' +
        ", commitId='" + commitId + '\'' +
        ", commitIdAbbrev='" + commitIdAbbrev + '\'' +
        ", commitIdDescribe='" + commitIdDescribe + '\'' +
        ", commitIdDescribeShort='" + commitIdDescribeShort + '\'' +
        ", commitMessageFull='" + commitMessageFull + '\'' +
        ", commitMessageShort='" + commitMessageShort + '\'' +
        ", commitTime='" + commitTime + '\'' +
        ", commitUserEmail='" + commitUserEmail + '\'' +
        ", commitUserName='" + commitUserName + '\'' +
        ", dirty='" + dirty + '\'' +
        ", remoteOriginUrl='" + remoteOriginUrl + '\'' +
        ", tags='" + tags + '\'' +
        ", totalCommitCount='" + totalCommitCount + '\'' +
        '}';
  }
}
