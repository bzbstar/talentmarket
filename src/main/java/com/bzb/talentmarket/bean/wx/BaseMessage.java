package com.bzb.talentmarket.bean.wx;

public class BaseMessage {
	// 消息接受方，开发者的微信号
	private String ToUserName; 
	// 发送方账号，粉丝openid
	private String FromUserName;
	// 消息创建时间（整型）
	private long CreateTime;
	private String MsgType; // 消息类型
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public long getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	@Override
	public String toString() {
		return "BaseMessage [ToUserName=" + ToUserName + ", FromUserName=" + FromUserName + ", CreateTime=" + CreateTime
				+ ", MsgType=" + MsgType + "]";
	}

}
