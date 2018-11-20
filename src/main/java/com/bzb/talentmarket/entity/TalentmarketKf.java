package com.bzb.talentmarket.entity;

import java.util.Date;

public class TalentmarketKf {
    private Integer id;

    private String uid;

    private String kfAccount;

    private String phone;

    private String kfNick;

    private String kfId;

    private String kfHeadimgurl;

    private String kfWx;

    private String inviteWx;

    private Long inviteExpireTime;

    private String inviteStatus;

    private String empuid;

    private Byte isheaderkf;

    private Byte kfStatus;

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

    public String getKfAccount() {
        return kfAccount;
    }

    public void setKfAccount(String kfAccount) {
        this.kfAccount = kfAccount == null ? null : kfAccount.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getKfNick() {
        return kfNick;
    }

    public void setKfNick(String kfNick) {
        this.kfNick = kfNick == null ? null : kfNick.trim();
    }

    public String getKfId() {
        return kfId;
    }

    public void setKfId(String kfId) {
        this.kfId = kfId == null ? null : kfId.trim();
    }

    public String getKfHeadimgurl() {
        return kfHeadimgurl;
    }

    public void setKfHeadimgurl(String kfHeadimgurl) {
        this.kfHeadimgurl = kfHeadimgurl == null ? null : kfHeadimgurl.trim();
    }

    public String getKfWx() {
        return kfWx;
    }

    public void setKfWx(String kfWx) {
        this.kfWx = kfWx == null ? null : kfWx.trim();
    }

    public String getInviteWx() {
        return inviteWx;
    }

    public void setInviteWx(String inviteWx) {
        this.inviteWx = inviteWx == null ? null : inviteWx.trim();
    }

    public Long getInviteExpireTime() {
        return inviteExpireTime;
    }

    public void setInviteExpireTime(Long inviteExpireTime) {
        this.inviteExpireTime = inviteExpireTime;
    }

    public String getInviteStatus() {
        return inviteStatus;
    }

    public void setInviteStatus(String inviteStatus) {
        this.inviteStatus = inviteStatus == null ? null : inviteStatus.trim();
    }

    public String getEmpuid() {
        return empuid;
    }

    public void setEmpuid(String empuid) {
        this.empuid = empuid == null ? null : empuid.trim();
    }

    public Byte getIsheaderkf() {
        return isheaderkf;
    }

    public void setIsheaderkf(Byte isheaderkf) {
        this.isheaderkf = isheaderkf;
    }

    public Byte getKfStatus() {
        return kfStatus;
    }

    public void setKfStatus(Byte kfStatus) {
        this.kfStatus = kfStatus;
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