package com.atdyl.mybatisplus.service.impl;

import com.atdyl.mybatisplus.dao.SnowDao;
import com.atdyl.mybatisplus.entities.AreaPO;
import com.atdyl.mybatisplus.service.SnowService;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author Dong YL
 * @version V1.0 2020/1/7 17:51
 */
@Service
@DS("snow")
public class SnowServiceImpl extends ServiceImpl<SnowDao, AreaPO> implements SnowService {


    @Override
    @DS("snow")
    public void add(AreaPO areaPO) {
        this.baseMapper.insert(areaPO);
    }
}
