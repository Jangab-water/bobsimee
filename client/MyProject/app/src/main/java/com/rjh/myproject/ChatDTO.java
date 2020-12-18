package com.rjh.myproject;

public class ChatDTO {
    private int imgId;
    private String name;
    private String content;

    public ChatDTO(int imgId, String name, String content) {
        this.imgId = imgId;
        this.name = name;
        this.content = content;
    }

    public ChatDTO() {
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ChatDTO{" +
                "imgId=" + imgId +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
