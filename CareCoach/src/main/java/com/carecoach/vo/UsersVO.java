package com.carecoach.vo;


public class UsersVO {
    private String user_id;
    private String name;
    private String password;
    private String email;
    private int is_deleted;
    private String indate;
    private String profile_img;
    private String bio;
    private String roles;
    private int user_no;

    public String getProfile_img() {
        return profile_img;
    }

    public void setProfile_img(String profile_img) {
        this.profile_img = profile_img;
    }

    public int getUser_no() {
        return user_no;
    }

    public void setUser_no(int user_no) {
        this.user_no = user_no;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(int is_deleted) {
        this.is_deleted = is_deleted;
    }

    public String getIndate() {
        return indate;
    }

    public void setIndate(String indate) {
        this.indate = indate;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UsersVO [user_id=" + user_id + ", name=" + name + ", password=" + password + ", email=" + email
                + ", is_deleted=" + is_deleted + ", indate=" + indate + ", profile_image=" + profile_img + ", bio="
                + bio + ", roles=" + roles + ", user_no=" + user_no + "]";
    }


}
