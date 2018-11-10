package com.bzb.talentmarket.controller;

import com.bzb.talentmarket.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bzb
 * @Description:
 * @date 2018/10/24 18:14
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping("/")
    public String test() {
        testService.test();
        return "SUCCESS";
    }
}
