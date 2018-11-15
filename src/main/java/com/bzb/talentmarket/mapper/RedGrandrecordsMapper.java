package com.bzb.talentmarket.mapper;

import com.bzb.talentmarket.entity.RedGrandrecords;

import java.util.List;

public interface RedGrandrecordsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RedGrandrecords record);

    int insertSelective(RedGrandrecords record);

    RedGrandrecords selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RedGrandrecords record);

    int updateByPrimaryKey(RedGrandrecords record);

    /**
     * 根据openid获取红包收入明细
     * @param openid
     * @return
     */
    List<RedGrandrecords> getByOpenid(String openid);
}