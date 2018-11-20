package com.bzb.talentmarket.mapper;

import com.bzb.talentmarket.entity.TalentmarketFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TalentmarketFactoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TalentmarketFactory record);

    int insertSelective(TalentmarketFactory record);

    TalentmarketFactory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TalentmarketFactory record);

    int updateByPrimaryKey(TalentmarketFactory record);

    List<TalentmarketFactory> getFactories();
}