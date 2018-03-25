package com.example.admin.model.bean;

import com.google.gson.annotations.SerializedName;

/**
 * User: hqs
 * Date: 2016/10/12
 * Time: 17:03
 */

public class AdInfo {

    private String title;

    @SerializedName("linkurl")
    private String link;

    @SerializedName("picurl")
    private String pic;

    @SerializedName("sortid")
    private String sortId;

    @SerializedName("headpicurl")
    private String headUrl;

    @SerializedName("shareDesc")
    private String shareDesc;

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getShareDesc() {
        return shareDesc;
    }

    public void setShareDesc(String shareDesc) {
        this.shareDesc = shareDesc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getSortId() {
        return sortId;
    }

    public void setSortId(String sortId) {
        this.sortId = sortId;
    }
}
