package com.example.model;

import java.util.List;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobGeoPoint;

/**
 * Created by TurenK on 2017/7/7.
 */

public class UserInfo extends BmobUser {
    private String sex;//
    private List<String> tag;//
    private String headimg;//
    private String address;//
    private String nickname;//
    private String intro;//
    private List<String> reportTeamIds;//
    private List<String> collectTeamIds;//
    private Integer userId;//
    private List<String> createdTeamIds;//
    private BmobGeoPoint addrGeo;//
    private List<String> joinedTeamIds;//

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

    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }

    public List<String> getReportTeamIds() {
        return reportTeamIds;
    }

    public void setReportTeamIds(List<String> reportTeamIds) {
        this.reportTeamIds = reportTeamIds;
    }

    public List<String> getCollectTeamIds() {
        return collectTeamIds;
    }

    public void setCollectTeamIds(List<String> collectTeamIds) {
        this.collectTeamIds = collectTeamIds;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<String> getCreatedTeamIds() {
        return createdTeamIds;
    }

    public void setCreatedTeamIds(List<String> createdTeamIds) {
        this.createdTeamIds = createdTeamIds;
    }

    public BmobGeoPoint getAddrGeo() {
        return addrGeo;
    }

    public void setAddrGeo(BmobGeoPoint addrGeo) {
        this.addrGeo = addrGeo;
    }

    public List<String> getJoinedTeamIds() {
        return joinedTeamIds;
    }

    public void setJoinedTeamIds(List<String> joinedTeamIds) {
        this.joinedTeamIds = joinedTeamIds;
    }
}
