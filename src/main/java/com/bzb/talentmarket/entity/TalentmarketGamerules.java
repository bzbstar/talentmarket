package com.bzb.talentmarket.entity;

import java.util.Date;

public class TalentmarketGamerules {
    private Integer id;

    private String uid;

    private Integer maxred;

    private Integer distance;

    private String randredRule;

    private Date credate;

    private Date upddate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public Integer getMaxred() {
        return maxred;
    }

    public void setMaxred(Integer maxred) {
        this.maxred = maxred;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public String getRandredRule() {
        return randredRule;
    }

    public void setRandredRule(String randredRule) {
        this.randredRule = randredRule == null ? null : randredRule.trim();
    }

    public Date getCredate() {
        return credate;
    }

    public void setCredate(Date credate) {
        this.credate = credate;
    }

    public Date getUpddate() {
        return upddate;
    }

    public void setUpddate(Date upddate) {
        this.upddate = upddate;
    }
}