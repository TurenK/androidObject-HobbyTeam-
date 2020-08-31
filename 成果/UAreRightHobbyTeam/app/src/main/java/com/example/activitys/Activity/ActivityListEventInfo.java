package com.example.activitys.Activity;

import java.io.Serializable;

/**
 * Created by TurenK on 2017/7/9.
 */

public class ActivityListEventInfo implements Serializable {
    private long id;
    private String activityName;
    private String ownerObjectId;
    private String activityTime;
    private String activityImgURL;
    private String maxPeople;
    private String currentPeople;
    private String activityObjectId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getOwnerObjectId() {
        return ownerObjectId;
    }

    public void setOwnerObjectId(String ownerObjectId) {
        this.ownerObjectId = ownerObjectId;
    }

    public String getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(String activityTime) {
        this.activityTime = activityTime;
    }

    public String getActivityImgURL() {
        return activityImgURL;
    }

    public void setActivityImgURL(String activityImgURL) {
        this.activityImgURL = activityImgURL;
    }

    public String getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(String maxPeople) {
        this.maxPeople = maxPeople;
    }

    public String getCurrentPeople() {
        return currentPeople;
    }

    public void setCurrentPeople(String currentPeople) {
        this.currentPeople = currentPeople;
    }

    public String getActivityObjectId() {
        return activityObjectId;
    }

    public void setActivityObjectId(String activityObjectId) {
        this.activityObjectId = activityObjectId;
    }
}
