package com.javaapi.test.testUtil.fileUtil.fenpi;

public class AcContentVideo {
    private String content_id;
    private String video_id;
    private String priority;
    private String id;

    public AcContentVideo(String content_id, String video_id, String priority) {
        this.content_id = content_id;
        this.video_id = video_id;
        this.priority = priority;
    }

    public String getContent_id() {
        return content_id;
    }

    public void setContent_id(String content_id) {
        this.content_id = content_id;
    }

    public String getVideo_id() {
        return video_id;
    }

    public void setVideo_id(String video_id) {
        this.video_id = video_id;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AcContentVideo{");
        sb.append("content_id='").append(content_id).append('\'');
        sb.append(", video_id='").append(video_id).append('\'');
        sb.append(", priority='").append(priority).append('\'');
        sb.append(", id='").append(id).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
