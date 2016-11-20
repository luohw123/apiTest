package com.javaapi.test.testUtil.fileUtil2.msg;

import java.util.Objects;

/**
 * Created by user on 16/11/11.
 */
public class ContentVideo {
    private Integer contentId;
    private String contentTitle;
    private Integer vid;
    private String sid;

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public String getContentTitle() {
        return contentTitle;
    }

    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle;
    }

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContentVideo that = (ContentVideo) o;
        return Objects.equals(contentId, that.contentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contentId);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ContentVideo{");
        sb.append("contentId=").append(contentId);
        sb.append(", contentTitle='").append(contentTitle).append('\'');
        sb.append(", vid=").append(vid);
        sb.append(", sid='").append(sid).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
