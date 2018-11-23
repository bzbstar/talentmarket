package com.bzb.talentmarket.mapper;

import com.bzb.talentmarket.entity.MessageAccept;

public interface MessageAcceptMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MessageAccept record);

    int insertSelective(MessageAccept record);

    MessageAccept selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MessageAccept record);

    int updateByPrimaryKey(MessageAccept record);
}