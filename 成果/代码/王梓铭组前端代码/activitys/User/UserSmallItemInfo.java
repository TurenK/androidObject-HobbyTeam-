package com.example.activitys.User;

import java.io.Serializable;
import java.util.List;

/**
 * Created by TurenK on 2017/7/16.
 */

public class UserSmallItemInfo implements Serializable {
    private String username;
    private String userheadimgUrl;
    private List<String> usertags;
    private long UseritemsmallId;
    private String nickname;

    public List<String> getUsertags() {
        return usertags;
    }

    public void setUsertags(List<String> usertags) {
        this.usertags = usertags;
    }

    public String getUserheadimgUrl() {
        return userheadimgUrl;
    }

    public void setUserheadimgUrl(String userheadimgUrl) {
        this.userheadimgUrl = userheadimgUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getUseritemsmallId() {
        return UseritemsmallId;
    }

    public void setUseritemsmallId(long useritemsmallId) {
        UseritemsmallId = useritemsmallId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
