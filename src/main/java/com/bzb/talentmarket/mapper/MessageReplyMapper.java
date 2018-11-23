package com.bzb.talentmarket.mapper;

import com.bzb.talentmarket.entity.MessageReply;

public interface MessageReplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MessageReply record);

    int insertSelective(MessageReply record);

    MessageReply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MessageReply record);

    int updateByPrimaryKey(MessageReply record);
}