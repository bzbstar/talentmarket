package com.bzb.talentmarket.entity;

import java.util.Date;

public class TalentmarketMember {
    private Integer id;

    private String uid;

    private String openid;

    private String nickname;

    private String realname;

    private Byte sex;

    private Integer age;

    private String idcardno;

    private String phone;

    private String qq;

    private String email;

    private String headimgurl;

    private String yuyan;

    private String country;

    private String province;

    private String city;

    private String counties;

    private String address;

    private Byte memberlevel;

    private String brokeruid;

    private String fopenid;

    private String unionid;

    private String remark;

    private Integer groupid;

    private String tagidList;

    private String subscribeScene;

    private Integer qrScene;

    private String qrSceneStr;

    private String qrcode;

    private Integer fansNum;

    private Long totalredmoney;

    private Byte isbroker;

    private Byte subscribeStatus;

    private Byte redStatus;

    private Long subscribedate;

    // 粉丝地理位置
    private Double geoglatitude;
    private Double geoglongitude;
    private Double geogprecision;

    // 微信号
    private String wxid;

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

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl == null ? null : headimgurl.trim();
    }

    public String getYuyan() {
        return yuyan;
    }

    public void setYuyan(String yuyan) {
        this.yuyan = yuyan;
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

    public Byte getMemberlevel() {
        return memberlevel;
    }

    public void setMemberlevel(Byte memberlevel) {
        this.memberlevel = memberlevel;
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

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid == null ? null : unionid.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    public String getTagidList() {
        return tagidList;
    }

    public void setTagidList(String tagidList) {
        this.tagidList = tagidList == null ? null : tagidList.trim();
    }

    public String getSubscribeScene() {
        return subscribeScene;
    }

    public void setSubscribeScene(String subscribeScene) {
        this.subscribeScene = subscribeScene == null ? null : subscribeScene.trim();
    }

    public Integer getQrScene() {
        return qrScene;
    }

    public void setQrScene(Integer qrScene) {
        this.qrScene = qrScene;
    }

    public String getQrSceneStr() {
        return qrSceneStr;
    }

    public void setQrSceneStr(String qrSceneStr) {
        this.qrSceneStr = qrSceneStr == null ? null : qrSceneStr.trim();
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode == null ? null : qrcode.trim();
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

    public Long getSubscribedate() {
        return subscribedate;
    }

    public void setSubscribedate(Long subscribedate) {
        this.subscribedate = subscribedate;
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

    public Double getGeoglatitude() {
        return geoglatitude;
    }

    public void setGeoglatitude(Double geoglatitude) {
        this.geoglatitude = geoglatitude;
    }

    public Double getGeoglongitude() {
        return geoglongitude;
    }

    public void setGeoglongitude(Double geoglongitude) {
        this.geoglongitude = geoglongitude;
    }

    public Double getGeogprecision() {
        return geogprecision;
    }

    public void setGeogprecision(Double geogprecision) {
        this.geogprecision = geogprecision;
    }

    public String getWxid() {
        return wxid;
    }

    public void setWxid(String wxid) {
        this.wxid = wxid;
    }

    @Override
    public String toString() {
        return "TalentmarketMember{" +
                "id=" + id +
                ", uid='" + uid + '\'' +
                ", openid='" + openid + '\'' +
                ", nickname='" + nickname + '\'' +
                ", realname='" + realname + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", idcardno='" + idcardno + '\'' +
                ", phone='" + phone + '\'' +
                ", qq='" + qq + '\'' +
                ", email='" + email + '\'' +
                ", headimgurl='" + headimgurl + '\'' +
                ", yuyan='" + yuyan + '\'' +
                ", country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", counties='" + counties + '\'' +
                ", address='" + address + '\'' +
                ", memberlevel=" + memberlevel +
                ", brokeruid='" + brokeruid + '\'' +
                ", fopenid='" + fopenid + '\'' +
                ", unionid='" + unionid + '\'' +
                ", remark='" + remark + '\'' +
                ", groupid=" + groupid +
                ", tagidList='" + tagidList + '\'' +
                ", subscribeScene='" + subscribeScene + '\'' +
                ", qrScene=" + qrScene +
                ", qrSceneStr='" + qrSceneStr + '\'' +
                ", qrcode='" + qrcode + '\'' +
                ", fansNum=" + fansNum +
                ", totalredmoney=" + totalredmoney +
                ", isbroker=" + isbroker +
                ", subscribeStatus=" + subscribeStatus +
                ", redStatus=" + redStatus +
                ", subscribedate=" + subscribedate +
                ", geoglatitude=" + geoglatitude +
                ", geoglongitude=" + geoglongitude +
                ", geogprecision=" + geogprecision +
                ", wxid='" + wxid + '\'' +
                ", credate=" + credate +
                ", upddate=" + upddate +
                '}';
    }
}