package com.carecoach.vo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PostsVO {

    //게시글 넘버
    private int id;

    //작성자 아이디
    private String userId;

    //카테고리 아이디
    private int categoryId;

    //제목
    private String title;

    //내용
    private String content;

    //영상 url
    private String url;

    private String videoId;

    //작성일
    private String createdDt;

    //수정일
    private String updatedDt;

    //조회수
    private int viewCnt;

    //좋아요 수
    private int likeCnt;

    //좋아요 여부
    private int isLiked;

    private int isDeleted;

    private int CntPerPage;

    private int StartIndex;

    // Getter와 Setter 메서드들


    public int getIsLiked() {
        return isLiked;
    }

    public void setIsLiked(int isLiked) {
        this.isLiked = isLiked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getCreatedDt() {
        return createdDt;
    }

    public void setCreatedDt(String createdDt) {
        this.createdDt = createdDt;
    }

    public String getUpdatedDt() {
        return updatedDt;
    }

    public void setUpdatedDt(String updatedDt) {
        this.updatedDt = updatedDt;
    }

    public int getViewCnt() {
        return viewCnt;
    }

    public void setViewCnt(int viewCnt) {
        this.viewCnt = viewCnt;
    }

    public int getLikeCnt() {
        return likeCnt;
    }

    public void setLikeCnt(int likeCnt) {
        this.likeCnt = likeCnt;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public int getCntPerPage() {
        return CntPerPage;
    }

    public void setCntPerPage(int cntPerPage) {
        CntPerPage = cntPerPage;
    }

    public int getStartIndex() {
        return StartIndex;
    }

    public void setStartIndex(int startIndex) {
        StartIndex = startIndex;
    }

    public void setUrl(String url) {
        this.url = url;
        this.videoId = extractVideoIdFromUrl(url); // URL이 설정될 때 비디오 ID 추출 및 설정
    }

    @Override
    public String toString() {
        return "PostsVO{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", categoryId=" + categoryId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", url='" + url + '\'' +
                ", videoId='" + videoId + '\'' +
                ", createdDt='" + createdDt + '\'' +
                ", updatedDt='" + updatedDt + '\'' +
                ", viewCnt=" + viewCnt +
                ", likeCnt=" + likeCnt +
                ", isDeleted=" + isDeleted +
                ", CntPerPage=" + CntPerPage +
                ", StartIndex=" + StartIndex +
                '}';
    }

    /**
     * URL에서 동영상 ID를 추출하는 메서드
     *
     * @param url 유튜브 동영상 URL
     * @return 추출된 동영상 ID 또는 null
     */
    private String extractVideoIdFromUrl(String url) {
        String pattern = "((?<=(v=|\\/))(\\w+)(?=&|\\?|$))";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(url);
        if (matcher.find()) {
            return matcher.group(3); // 매칭된 그룹 중 세 번째 그룹이 동영상 ID
        } else {
            return null; // 추출 실패 시 null 반환
        }
    }
}
