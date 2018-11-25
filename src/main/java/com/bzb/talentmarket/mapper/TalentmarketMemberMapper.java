package com.bzb.talentmarket.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bzb.talentmarket.entity.TalentmarketMember;

public interface TalentmarketMemberMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TalentmarketMember record);

    int insertSelective(TalentmarketMember record);

    TalentmarketMember selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TalentmarketMember record);

    int updateByPrimaryKey(TalentmarketMember record);

    /**
     * 根据openid获取会员信息
     * @param openid
     * @return
     */
    TalentmarketMember getByOpenid(@Param("openid") String openid);

    /**
     * 根据openid更新会员信息
     * @param member
     * @return
     */
    int updateByOpenid(TalentmarketMember member);

    /**
     * 更新推荐人的相关粉丝信息
     * @param presenterOpenid
     * @param randMoney, 单位分
     * @return
     */
    int updateFans(@Param("openid") String presenterOpenid, @Param("randMoney") long randMoney);

    /**
     * 获取推荐者的粉丝列表
     * @param openid
     * @return
     */
    List<TalentmarketMember> getRecommendMembersByOpenid(String openid);

    /**
     * 
     * @Description:获取总部经纪人
     * @return
     * @exception:
     * @author: bzb
     * @time:2018年11月21日 下午9:33:07
     */
	TalentmarketMember getHeaderAgent();
}