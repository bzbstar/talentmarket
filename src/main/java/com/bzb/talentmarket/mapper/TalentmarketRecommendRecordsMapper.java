package com.bzb.talentmarket.mapper;

import com.bzb.talentmarket.entity.TalentmarketRecommendRecords;

public interface TalentmarketRecommendRecordsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TalentmarketRecommendRecords record);

    int insertSelective(TalentmarketRecommendRecords record);

    TalentmarketRecommendRecords selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TalentmarketRecommendRecords record);

    int updateByPrimaryKey(TalentmarketRecommendRecords record);
}