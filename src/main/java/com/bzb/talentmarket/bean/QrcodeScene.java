package com.bzb.talentmarket.bean;

/**
 * 
 * @Description:关注二维码场景值Bean
 * @author:bzb
 * @time:2018年11月21日 下午9:17:39
 */
public class QrcodeScene {
	private String refereeOpenid; // 推荐人的openid
	private String agentOpenid; // 经纪人的openid
	private Integer isAgent; // 是否经纪人
	public String getRefereeOpenid() {
		return refereeOpenid;
	}
	public void setRefereeOpenid(String refereeOpenid) {
		this.refereeOpenid = refereeOpenid;
	}
	public String getAgentOpenid() {
		return agentOpenid;
	}
	public void setAgentOpenid(String agentOpenid) {
		this.agentOpenid = agentOpenid;
	}
	public Integer getIsAgent() {
		return isAgent;
	}
	public void setIsAgent(Integer isAgent) {
		this.isAgent = isAgent;
	}
	@Override
	public String toString() {
		return "QrcodeScene [refereeOpenid=" + refereeOpenid + ", agentOpenid=" + agentOpenid + ", isAgent=" + isAgent
				+ "]";
	}
}
