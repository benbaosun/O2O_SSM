package com.lin.entity;

import java.util.Date;

/**
 * @author lkmc2
 * @date 2018/4/23.
 * 用户信息实体类
 */

public class PersonInfo {
    /*** 用户id ***/
    private Long userId;

    /*** 用户名 ***/
    private String name;

    /*** 用户头像 ***/
    private String profileImg;

    /*** 邮箱 ***/
    private String email;

    /*** 性别 ***/
    private Integer gender;

    /*** 创建时间 ***/
    private Date createTime;

    /*** 更新时间 ***/
    private Date lastEditTime;

    /*** 用户类型：1.顾客 2.店家 3.管理员 ***/
    private Integer userType;

    /*** 可用状态 ***/
    private Integer enableStatus;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(Integer enableStatus) {
        this.enableStatus = enableStatus;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "PersonInfo{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", profileImg='" + profileImg + '\'' +
                ", email='" + email + '\'' +
                ", createTime=" + createTime +
                ", lastEditTime=" + lastEditTime +
                ", userType=" + userType +
                ", enableStatus=" + enableStatus +
                ", gender=" + gender +
                '}';
    }
}
