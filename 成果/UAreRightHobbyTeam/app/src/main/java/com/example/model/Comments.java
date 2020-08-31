package com.example.model;

import cn.bmob.v3.BmobObject;

/**
 * Created by TurenK on 2017/7/18.
 */

public class Comments extends BmobObject {
    private String UserObjectId;
    private String ActivityObjectId;
    private String content;
    private String likenum;
    private String sendingTime;
    private String nicknameAndUsername;
    private String userHeadImg;

    public String getUserObjectId() {
        return UserObjectId;
    }

    public void setUserObjectId(String userObjectId) {
        UserObjectId = userObjectId;
    }

    public String getActivityObjectId() {
        return ActivityObjectId;
    }

    public void setActivityObjectId(String activityObjectId) {
        ActivityObjectId = activityObjectId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLikenum() {
        return likenum;
    }

    public void setLikenum(String likenum) {
        this.likenum = likenum;
    }

    public String getSendingTime() {
        return sendingTime;
    }

    public void setSendingTime(String sendingTime) {
        this.sendingTime = sendingTime;
    }

    public String getNicknameAndUsername() {
        return nicknameAndUsername;
    }

    public void setNicknameAndUsername(String nicknameAndUsername) {
        this.nicknameAndUsername = nicknameAndUsername;
    }

    public String getUserHeadImg() {
        return userHeadImg;
    }

    public void setUserHeadImg(String userHeadImg) {
        this.userHeadImg = userHeadImg;
    }
}
