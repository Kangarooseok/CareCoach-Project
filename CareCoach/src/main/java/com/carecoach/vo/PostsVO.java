package com.carecoach.vo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PostsVO {

	private int id;
	private String user_id;
	private int category_id;
	private String title;
	private String content;
	private String url;
	private String video_id;
	private String created_dt;
	private String updated_dt;
	private int view_cnt;
	private int is_deleted;
	
	@Override
    public String toString() {
        return "PostsVO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createdDate=" + created_dt +
                ", url=" + url +
                ", video_id=" + video_id +
                '}';
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
