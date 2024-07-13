package com.carecoach.vo;


public class BannersVO {

    private String name;
    private int sortSep;
    private int useAt;
    private String banner;
    private String url;
    private String createdDt;
    private String updateDt;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUpdateDt() {
        return updateDt;
    }

    public void setUpdateDt(String updateDt) {
        this.updateDt = updateDt;
    }

    public String getCreatedDt() {
        return createdDt;
    }

    public void setCreatedDt(String createdDt) {
        this.createdDt = createdDt;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public int getUseAt() {
        return useAt;
    }

    public void setUseAt(int useAt) {
        this.useAt = useAt;
    }

    public int getSortSep() {
        return sortSep;
    }

    public void setSortSep(int sortSep) {
        this.sortSep = sortSep;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BannersVO{" +
                "name='" + name + '\'' +
                ", sortSep=" + sortSep +
                ", useAt=" + useAt +
                ", banner='" + banner + '\'' +
                ", url='" + url + '\'' +
                ", createdDt='" + createdDt + '\'' +
                ", updateDt='" + updateDt + '\'' +
                ", id=" + id +
                '}';
    }
}
