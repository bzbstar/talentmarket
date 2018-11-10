package com.bzb.talentmarket.controller;

import com.bzb.talentmarket.bean.ResultModel;
import com.bzb.talentmarket.entity.TalentmarketChoreboy;
import com.bzb.talentmarket.service.ChoreboyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bzb
 * @Description: 新增临时工资
 * @date 2018/9/25 16:41
 */
@RestController
@RequestMapping(value = "/choreboy")
public class ChoreboyController {

    @Autowired
    private ChoreboyService choreboyService;

    /**
     * 新增临时工工资
     * @param choreboy
     * @return
     */
    @RequestMapping(value = "/choreboys", method = RequestMethod.POST)
    public ResultModel add(TalentmarketChoreboy choreboy) {

        choreboyService.addChoreboy(choreboy);

        return new ResultModel(true, "SUccess");
    }

    @RequestMapping(value = "/choreboys", method = RequestMethod.GET)
    public String list() {


        return "choreboy";
    }
}
