package com.example.activitys.Exercise;

import java.io.Serializable;
import java.util.List;

/**
 * Created by TurenK on 2017/7/9.
 */

public class ExerciseItemListEventInfo implements Serializable {
    private long activitiesId;//
    private String imgURL;//
    private List<String> tag;//
    private String commentnum;//
    private String likenum;//
    private String activityName;//
    private String objectId;//

    public long getActivitiesId() {
        return activitiesId;
    }

    public void setActivitiesId(long activitiesId) {
        this.activitiesId = activitiesId;
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

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }
}
