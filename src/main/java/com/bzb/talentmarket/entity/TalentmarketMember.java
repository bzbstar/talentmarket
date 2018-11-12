package com.bzb.talentmarket.entity;

import java.util.Date;

public class TalentmarketMember {
    private Integer id;

    private String uid;

    private String openid;

    private String nickname;

    private String headimg;

    private String realname;

    private Byte sex;

    private Integer age;

    private String idcardno;

    private String phone;

    private String qq;

    private String email;

    private String country;

    private String province;

    private String city;

    private String counties;

    private String address;

    private Byte level;

    private String brokeruid;

    private String fopenid;

    private Byte source;

    private Integer fansNum;

    private Long totalredmoney;

    private Byte isbroker;

    private Byte subscribeStatus;

    private Byte redStatus;

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

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg == null ? null : headimg.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getIdcardno() {
        return idcardno;
    }

    public void setIdcardno(String idcardno) {
        this.idcardno = idcardno == null ? null : idcardno.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getCounties() {
        return counties;
    }

    public void setCounties(String counties) {
        this.counties = counties == null ? null : counties.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Byte getLevel() {
        return level;
    }

    public void setLevel(Byte level) {
        this.level = level;
    }

    public String getBrokeruid() {
        return brokeruid;
    }

    public void setBrokeruid(String brokeruid) {
        this.brokeruid = brokeruid == null ? null : brokeruid.trim();
    }

    public String getFopenid() {
        return fopenid;
    }

    public void setFopenid(String fopenid) {
        this.fopenid = fopenid == null ? null : fopenid.trim();
    }

    public Byte getSource() {
        return source;
    }

    public void setSource(Byte source) {
        this.source = source;
    }

    public Integer getFansNum() {
        return fansNum;
    }

    public void setFansNum(Integer fansNum) {
        this.fansNum = fansNum;
    }

    public Long getTotalredmoney() {
        return totalredmoney;
    }

    public void setTotalredmoney(Long totalredmoney) {
        this.totalredmoney = totalredmoney;
    }

    public Byte getIsbroker() {
        return isbroker;
    }

    public void setIsbroker(Byte isbroker) {
        this.isbroker = isbroker;
    }

    public Byte getSubscribeStatus() {
        return subscribeStatus;
    }

    public void setSubscribeStatus(Byte subscribeStatus) {
        this.subscribeStatus = subscribeStatus;
    }

    public Byte getRedStatus() {
        return redStatus;
    }

    public void setRedStatus(Byte redStatus) {
        this.redStatus = redStatus;
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