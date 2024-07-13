package com.carecoach.vo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PostsVO {

    //게시글 넘버
    private int id;

    //작성자 아이디
    private String user_id;

    //카테고리 아이디
    private int category_id;

    //제목
    private String title;

    //내용
    private String content;

    //영상 url
    private String url;

    private String video_id;

    //작성일
    private String created_dt;

    //수정일
    private String updated_dt;

    //조회수
    private int view_cnt;

    //좋아요 수
    private int like_cnt;

    private int is_deleted;

    private int CntPerPage;

    private int StartIndex;


    public int getLike_cnt() {
        return like_cnt;
    }

    public void setLike_cnt(int like_cnt) {
        this.like_cnt = like_cnt;
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


    // Getter와 Setter 메서드들

    public String getVideo_id() {
        return video_id;
    }

    public void setVideo_id(String video_id) {
        this.video_id = video_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
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

    public void setUrl(String url) {
        this.url = url;
        this.video_id = extractVideoIdFromUrl(url); // URL이 설정될 때 비디오 ID 추출 및 설정
    }

    public String getCreated_dt() {
        return created_dt;
    }

    public void setCreated_dt(String created_dt) {
        this.created_dt = created_dt;
    }

    public String getUpdated_dt() {
        return updated_dt;
    }

    public void setUpdated_dt(String updated_dt) {
        this.updated_dt = updated_dt;
    }

    public int getView_cnt() {
        return view_cnt;
    }

    public void setView_cnt(int view_cnt) {
        this.view_cnt = view_cnt;
    }

    public int getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(int is_deleted) {
        this.is_deleted = is_deleted;
    }

    public String toString() {
        String result = "id=" + id + "user_id=" + user_id + "category_id=" + category_id + "title=" + title +
                "\ncontent=" + content + "url=" + url + "created_dt=" + created_dt + "updated_dt=" + updated_dt +
                "\nview_cnt=" + view_cnt + "is_deleted=" + is_deleted + "like_cnt : " + like_cnt;

        return result;
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
