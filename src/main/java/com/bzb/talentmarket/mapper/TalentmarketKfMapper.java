package com.bzb.talentmarket.mapper;

import com.bzb.talentmarket.entity.TalentmarketKf;
import org.springframework.stereotype.Repository;

@Repository
public interface TalentmarketKfMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TalentmarketKf record);

    int insertSelective(TalentmarketKf record);

    TalentmarketKf selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TalentmarketKf record);

    int updateByPrimaryKey(TalentmarketKf record);
}