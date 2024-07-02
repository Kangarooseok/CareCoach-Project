package com.carecoach.vo;

public class CommentsVO {

	private int id;
	private int post_id;
	private String user_id;
	private int parent_id;
	private String content;
	private String created_dt;
	private String updated_dt;
	private int is_deleted;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPost_id() {
		return post_id;
	}
	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public int getIs_deleted() {
		return is_deleted;
	}
	public void setIs_deleted(int is_deleted) {
		this.is_deleted = is_deleted;
	}
	
}
