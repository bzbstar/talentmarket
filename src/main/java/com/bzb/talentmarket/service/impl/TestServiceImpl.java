package com.bzb.talentmarket.service.impl;

import com.bzb.talentmarket.entity.User;
import com.bzb.talentmarket.mapper.UserMapper;
import com.bzb.talentmarket.service.TestService;
import com.bzb.talentmarket.service.TestService2;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author bzb
 * @Description:
 * @date 2018/10/24 18:15
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TestService2 testService2;

    @Transactional
    @Override
    public void test() {
        insertUser1();

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertUser1() {
        User user = new User();
        user.setUserName("bzb");
        user.setPassword("123");
        user.setPhone("15158415932");
        int rows = userMapper.insert(user);
        Integer maxid = userMapper.getMaxId();
        System.out.println(maxid);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertUser2() {
        User user = new User();
        user.setUserName("bzb");
        user.setPassword("123");
        user.setPhone("15158415932");
        userMapper.insert(user);
        int i = 5 / 0;
        userMapper.insert(user);
    }
}
