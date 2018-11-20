package com.bzb.talentmarket.mapper;

import com.bzb.talentmarket.entity.TalentmarketEmployee;
import org.springframework.stereotype.Repository;

@Repository
public interface TalentmarketEmployeeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TalentmarketEmployee record);

    int insertSelective(TalentmarketEmployee record);

    TalentmarketEmployee selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TalentmarketEmployee record);

    int updateByPrimaryKey(TalentmarketEmployee record);
}