package com.bzb.talentmarket.bean.wx;

public class BaseMessage {
	// 消息接受方，开发者的微信号
	private String toUserName; 
	// 发送方账号，粉丝openid
	private String fromUserName;
	// 消息创建时间（整型）
	private long createTime;
	// 消息类型,event
	private String msgType;
	// Event, 事件类型，subscribe
	private String event;
	public String getToUserName() {
		return toUserName;
	}
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	public String getFromUserName() {
		return fromUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	@Override
	public String toString() {
		return "WxMessage [toUserName=" + toUserName + ", fromUserName=" + fromUserName + ", createTime=" + createTime
				+ ", msgType=" + msgType + ", event=" + event + "]";
	}

}
