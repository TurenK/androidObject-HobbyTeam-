package com.example.model;

import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * Created by TurenK on 2017/7/18.
 */

public class Friends extends BmobObject {
    private String username;
    private String friendname;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFriendname() {
        return friendname;
    }

    public void setFriendname(String friendname) {
        this.friendname = friendname;
    }
}
