package com.bzb.talentmarket.mapper;

import com.bzb.talentmarket.entity.TalentmarketGamerules;
import org.springframework.stereotype.Repository;

@Repository
public interface TalentmarketGamerulesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TalentmarketGamerules record);

    int insertSelective(TalentmarketGamerules record);

    TalentmarketGamerules selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TalentmarketGamerules record);

    int updateByPrimaryKey(TalentmarketGamerules record);

    /**
     * 获取华山路的半径映射范围
     * @return
     */
    TalentmarketGamerules getDistinceAndRed();
    
    /**
     * @Description:获取总部经纪人密码
     * @return
     * @exception:
     * @author: bzb
     * @time:2018年11月19日 下午10:26:19
     */
	String getHeaderPassword();
}