package com.bzb.talentmarket.controller;

import com.bzb.talentmarket.bean.ResultModel;
import com.bzb.talentmarket.entity.TalentmarketFactory;
import com.bzb.talentmarket.service.FactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author bzb
 * @Description:
 * @date 2018/9/25 18:21
 */
@RestController
@RequestMapping(value = "/factory")
public class FactoryController {

    @Autowired
    private FactoryService factoryService;

    /**
     * 获取电子厂列表
     * @return
     */
    @RequestMapping(value = "/factories", method = RequestMethod.GET)
    public ResultModel getFactorys() {
        List<TalentmarketFactory> factories = factoryService.getFactories();
        return new ResultModel(true, "SUCESS", factories);
    }
}
