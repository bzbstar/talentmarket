package com.bzb.talentmarket.entity;

import java.util.Date;

public class TalentmarketRecommendRecords {
    private Integer id;

    private String uid;

    private String referrerOpenid;

    private String fansOpenid;

    private Long redmoney;

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

    public String getReferrerOpenid() {
        return referrerOpenid;
    }

    public void setReferrerOpenid(String referrerOpenid) {
        this.referrerOpenid = referrerOpenid == null ? null : referrerOpenid.trim();
    }

    public String getFansOpenid() {
        return fansOpenid;
    }

    public void setFansOpenid(String fansOpenid) {
        this.fansOpenid = fansOpenid == null ? null : fansOpenid.trim();
    }

    public Long getRedmoney() {
        return redmoney;
    }

    public void setRedmoney(Long redmoney) {
        this.redmoney = redmoney;
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