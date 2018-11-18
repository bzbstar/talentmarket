package com.bzb.talentmarket.bean.wx;

/**
 * 
 * @Description:客服信息
 * @author:bzb
 * @time:2018年11月16日 下午10:45:29
 */
public class TransInfo {
	private String KfAccount;

	public TransInfo(String kfAccount) {
		KfAccount = kfAccount;
	}

	public String getKfAccount() {
		return KfAccount;
	}

	public void setKfAccount(String kfAccount) {
		KfAccount = kfAccount;
	}

	@Override
	public String toString() {
		return "TransInfo [KfAccount=" + KfAccount + "]";
	}
	
}
