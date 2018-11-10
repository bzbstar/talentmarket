package com.bzb.talentmarket.entity;

import java.util.Date;

public class TalentmarketChoreboy {
    private Integer id;

    private String uid;

    private String cbuid;

    private String cbname;

    private String factoryuid;

    private String factoryname;

    private Long money;

    private Byte moneyClass;

    private Byte status;

    private Date date;

    private Date credate;

    private Date upddate;

    private String userid;

    private String username;

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

    public String getCbuid() {
        return cbuid;
    }

    public void setCbuid(String cbuid) {
        this.cbuid = cbuid == null ? null : cbuid.trim();
    }

    public String getCbname() {
        return cbname;
    }

    public void setCbname(String cbname) {
        this.cbname = cbname == null ? null : cbname.trim();
    }

    public String getFactoryuid() {
        return factoryuid;
    }

    public void setFactoryuid(String factoryuid) {
        this.factoryuid = factoryuid == null ? null : factoryuid.trim();
    }

    public String getFactoryname() {
        return factoryname;
    }

    public void setFactoryname(String factoryname) {
        this.factoryname = factoryname == null ? null : factoryname.trim();
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public Byte getMoneyClass() {
        return moneyClass;
    }

    public void setMoneyClass(Byte moneyClass) {
        this.moneyClass = moneyClass;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }
}