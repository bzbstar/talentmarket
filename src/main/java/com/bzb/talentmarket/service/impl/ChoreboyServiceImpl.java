package com.bzb.talentmarket.service.impl;

import com.bzb.talentmarket.common.FinalData;
import com.bzb.talentmarket.entity.TalentmarketChoreboy;
import com.bzb.talentmarket.exception.CheckParamsException;
import com.bzb.talentmarket.mapper.TalentmarketChoreboyMapper;
import com.bzb.talentmarket.service.ChoreboyService;
import com.bzb.talentmarket.utils.UniqueIdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * @author bzb
 * @Description: 临时工业务层
 * @date 2018/9/25 16:55
 */
@Service
public class ChoreboyServiceImpl implements ChoreboyService {

    @Autowired
    private TalentmarketChoreboyMapper choreboyMapper;

    @Override
    public int addChoreboy(TalentmarketChoreboy choreboy) {

        // 参考校验
        checkAddChoreBoy(choreboy);

        choreboy.setUid(UniqueIdUtils.getUUID());
        choreboy.setStatus((byte) FinalData.Choreboy.STATUS_ON);
        Date now = new Date();
        choreboy.setCredate(now);
        choreboy.setUpddate(now);

        int updateRows = choreboyMapper.insertSelective(choreboy);

        return updateRows;
    }

    /**
     * 新增临时工资参数校验
     * @param choreboy
     */
    private void checkAddChoreBoy(TalentmarketChoreboy choreboy) {
        if (!StringUtils.hasText(choreboy.getCbname())) {
            throw new CheckParamsException("姓名不存在");
        }
        if (choreboy.getMoneyClass() != FinalData.Choreboy.MONEY_CLASS_IN
                && choreboy.getMoneyClass() != FinalData.Choreboy.MONEY_CLASS_OUT) {
            throw new CheckParamsException("请选择正确的收支类型");
        }
        if (choreboy.getMoney() <= 0) {
            throw new CheckParamsException("请输入正确的临时工资");
        }

        if (!StringUtils.hasText(choreboy.getFactoryuid())) {
            throw new CheckParamsException("请选择厂区");
        }
    }
}
