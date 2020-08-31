package com.example.activitys.Exercise;

import java.io.Serializable;

/**
 * Created by TurenK on 2017/7/18.
 */

public class CommentListInfo implements Serializable {
    private String ObjectId;
    private String UserObjectId;
    private String content;
    private String userHeadImg;
    private String nicknameAndUsername;
    private String sendingTime;
    private String likenum;

    public String getUserObjectId() {
        return UserObjectId;
    }

    public void setUserObjectId(String userObjectId) {
        UserObjectId = userObjectId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserHeadImg() {
        return userHeadImg;
    }

    public void setUserHeadImg(String userHeadImg) {
        this.userHeadImg = userHeadImg;
    }

    public String getNicknameAndUsername() {
        return nicknameAndUsername;
    }

    public void setNicknameAndUsername(String nicknameAndUsername) {
        this.nicknameAndUsername = nicknameAndUsername;
    }

    public String getSendingTime() {
        return sendingTime;
    }

    public void setSendingTime(String sendingTime) {
        this.sendingTime = sendingTime;
    }

    public String getLikenum() {
        return likenum;
    }

    public void setLikenum(String likenum) {
        this.likenum = likenum;
    }

    public String getObjectId() {
        return ObjectId;
    }

    public void setObjectId(String objectId) {
        ObjectId = objectId;
    }
}
