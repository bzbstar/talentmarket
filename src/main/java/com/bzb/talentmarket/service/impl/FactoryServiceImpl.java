package com.bzb.talentmarket.service.impl;

import com.bzb.talentmarket.entity.TalentmarketFactory;
import com.bzb.talentmarket.mapper.TalentmarketFactoryMapper;
import com.bzb.talentmarket.service.FactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bzb
 * @Description: 电子厂业务层
 * @date 2018/9/25 18:22
 */
@Service
public class FactoryServiceImpl implements FactoryService {

    @Autowired
    private TalentmarketFactoryMapper factoryMapper;

    @Override
    public List<TalentmarketFactory> getFactories() {
        return factoryMapper.getFactories();
    }
}
