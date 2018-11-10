package com.bzb.talentmarket.service.impl;

import com.bzb.talentmarket.entity.User;
import com.bzb.talentmarket.mapper.UserMapper;
import com.bzb.talentmarket.service.TestService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author bzb
 * @Description:
 * @date 2018/10/25 9:21
 */
@Service
public class TestServiceImpl2 implements TestService2 {

    @Autowired
    private UserMapper userMapper;

    @Transactional(propagation = Propagation.NESTED)
    public void insertUser1() {
        User user = new User();
        user.setUserName("bzb111是是是");
        user.setPassword("123");
        user.setPhone("15158415932");
        userMapper.insert(user);
        userMapper.insert(user);

    }

    @Transactional(propagation = Propagation.NESTED)
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
