package com.bzb.talentmarket.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bzb.talentmarket.entity.MessageReply;

public interface MessageReplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MessageReply record);

    int insertSelective(MessageReply record);

    MessageReply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MessageReply record);

    int updateByPrimaryKey(MessageReply record);
    
    /**
     * 
     * @Description:获取回复的2条消息
     * @param openid
     * @param string
     * @return
     * @exception:
     * @author: bzb
     * @time:2018年11月24日 下午10:59:59
     */
	List<MessageReply> getNewTwoMessages(@Param("openid") String openid, @Param("tableName") String tableName);
}