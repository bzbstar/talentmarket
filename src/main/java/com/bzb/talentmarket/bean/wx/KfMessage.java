package com.bzb.talentmarket.bean.wx;

/**
 * 
 * @Description:转发给指定客服的消息类
 * @author:bzb
 * @time:2018年11月16日 下午10:43:45
 */
public class KfMessage extends BaseMessage {
	private TransInfo transInfo;

	public TransInfo getTransInfo() {
		return transInfo;
	}

	public void setTransInfo(TransInfo transInfo) {
		this.transInfo = transInfo;
	}

	@Override
	public String toString() {
		return "KfMessage [transInfo=" + transInfo + ", getToUserName()=" + getToUserName() + ", getFromUserName()="
				+ getFromUserName() + ", getCreateTime()=" + getCreateTime() + ", getMsgType()=" + getMsgType()
				+ ", toString()=" + super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ "]";
	}
	
}
