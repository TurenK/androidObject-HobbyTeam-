package com.example.model;

import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobGeoPoint;

/**
 * Created by TurenK on 2017/7/11.
 */

public class Activitys extends BmobObject {
    private Integer activitiesId;//
    private String owenerId;//
    private String sendingTime;//
    private List<String> tag;//
    private String content;//
    private String imgURL;//
    private String commentnum;//
    private String likenum;//
    private String address;//
    private String actiivtiesTime;//
    private String activityName;//
    private BmobGeoPoint addrGeo;//
    private List<String> memberIds;//
    private String maxMember;//


    public Integer getActivitiesId() {
        return activitiesId;
    }

    public void setActivitiesId(Integer activitiesId) {
        this.activitiesId = activitiesId;
    }

    public String getSendingTime() {
        return sendingTime;
    }

    public void setSendingTime(String sendingTime) {
        this.sendingTime = sendingTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getCommentnum() {
        return commentnum;
    }

    public void setCommentnum(String commentnum) {
        this.commentnum = commentnum;
    }

    public String getLikenum() {
        return likenum;
    }

    public void setLikenum(String likenum) {
        this.likenum = likenum;
    }

    public String getActiivtiesTime() {
        return actiivtiesTime;
    }

    public void setActiivtiesTime(String actiivtiesTime) {
        this.actiivtiesTime = actiivtiesTime;
    }

    public String getOwenerId() {
        return owenerId;
    }

    public void setOwenerId(String owenerId) {
        this.owenerId = owenerId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }

    public BmobGeoPoint getAddrGeo() {
        return addrGeo;
    }

    public void setAddrGeo(BmobGeoPoint addrGeo) {
        this.addrGeo = addrGeo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getMemberIds() {
        return memberIds;
    }

    public void setMemberIds(List<String> memberIds) {
        this.memberIds = memberIds;
    }

    public String getMaxMember() {
        return maxMember;
    }

    public void setMaxMember(String maxMember) {
        this.maxMember = maxMember;
    }

}
