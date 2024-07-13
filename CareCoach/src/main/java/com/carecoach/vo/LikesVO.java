package com.carecoach.vo;

public class LikesVO {

    private int id;
    private int postId;
    private String userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "LikesVO{" +
                "id=" + id +
                ", postId=" + postId +
                ", userId='" + userId + '\'' +
                '}';
    }
}
