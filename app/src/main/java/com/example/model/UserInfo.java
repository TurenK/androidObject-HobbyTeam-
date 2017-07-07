package com.example.model;

import cn.bmob.v3.BmobUser;

/**
 * Created by TurenK on 2017/7/7.
 */

public class UserInfo extends BmobUser {
    private String sex;
    private String headimg;
    private String address;
    private String nickname;
    private String intro;
    private String intimeaddr;

    public String getSex(){
        return sex;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getIntimeaddr() {
        return intimeaddr;
    }

    public void setIntimeaddr(String intimeaddr) {
        this.intimeaddr = intimeaddr;
    }
}
