package com.lin.entity;

import java.util.Date;

/**
 * @author lkmc2
 * @date 2018/4/23.
 * 区域实体类
 * （以下属性使用Integer类型，是为了防止int型会默认赋值为0）
 */

public class Area {
    /*** 区域id ***/
    private Integer areaId;

    /*** 区域名称 ***/
    private String areaName;

    /*** 权重 ***/
    private Integer priority;

    /*** 更新时间 ***/
    private Date lastEditTime;

    /*** 创建时间 ***/
    private Date createTime;

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    @Override
    public String toString() {
        return "Area{" +
                "areaId=" + areaId +
                ", priority=" + priority +
                ", lastEditTime=" + lastEditTime +
                ", createTime=" + createTime +
                ", areaName='" + areaName + '\'' +
                '}';
    }
}
