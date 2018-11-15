package com.bzb.talentmarket.entity;

import java.util.Date;

public class RedGrandrecords {
    private Integer id;

    private String uid;

    private String draweruid;

    private String name;

    private String openid;

    private Long money;

    private String sourceOpenid;

    private Byte source;

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

    public String getDraweruid() {
        return draweruid;
    }

    public void setDraweruid(String draweruid) {
        this.draweruid = draweruid == null ? null : draweruid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public String getSourceOpenid() {
        return sourceOpenid;
    }

    public void setSourceOpenid(String sourceOpenid) {
        this.sourceOpenid = sourceOpenid == null ? null : sourceOpenid.trim();
    }

    public Byte getSource() {
        return source;
    }

    public void setSource(Byte source) {
        this.source = source;
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