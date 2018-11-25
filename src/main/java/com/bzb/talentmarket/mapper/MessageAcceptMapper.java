package com.bzb.talentmarket.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bzb.talentmarket.entity.MessageAccept;

public interface MessageAcceptMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MessageAccept record);

    int insertSelective(MessageAccept record);

    MessageAccept selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MessageAccept record);

    int updateByPrimaryKey(MessageAccept record);
    
    /**
     * 
     * @Description:创建经纪人消息表
     * @param openid
     * @exception:
     * @author: bzb
     * @time:2018年11月23日 下午8:39:26
     */
	boolean createTable(@Param("openid") String openid);
	
	/**
	 * 
	 * @Description:获取最新的5条消息
	 * @param openid 粉丝的openid
	 * @param agentOpenid 经纪人openid
	 * @return
	 * @exception:
	 * @author: bzb
	 * @time:2018年11月23日 下午8:52:56
	 */
	List<MessageAccept> getNewFiveMessages(@Param("openid") String openid, @Param("tableName") String tableName);
}