package com.bzb.talentmarket.entity;

import java.util.Date;

public class MessageReply {
    private Integer id;

    private String uid;

    private String sendOpenid;

    private String toOpenid;

    private String content;

    private Byte status;

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

    public String getSendOpenid() {
        return sendOpenid;
    }

    public void setSendOpenid(String sendOpenid) {
        this.sendOpenid = sendOpenid == null ? null : sendOpenid.trim();
    }

    public String getToOpenid() {
        return toOpenid;
    }

    public void setToOpenid(String toOpenid) {
        this.toOpenid = toOpenid == null ? null : toOpenid.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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