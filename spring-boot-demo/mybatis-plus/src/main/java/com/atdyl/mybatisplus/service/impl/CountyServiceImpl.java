package com.atdyl.mybatisplus.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.atdyl.mybatisplus.dao.CountyDao;
import com.atdyl.mybatisplus.entities.AreaPO;
import com.atdyl.mybatisplus.entities.CountyPO;
import com.atdyl.mybatisplus.service.CountyService;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dong YL
 * @version V1.0 2020/1/7 17:47
 */
@Service
@DS("varys")
public class CountyServiceImpl extends ServiceImpl<CountyDao, CountyPO> implements CountyService {
    @Override
    public List<AreaPO> getList() {
        List<CountyPO> countyPOS = this.baseMapper.selectList(new QueryWrapper<>());

        List<AreaPO> areaPOS = new ArrayList<>();

        countyPOS.forEach(countyPO -> {
            AreaPO areaPO = AreaPO.builder().build();
            BeanUtil.copyProperties(BeanUtil.trimStrFields(countyPO), areaPO);
            areaPOS.add(areaPO);
        });
        return areaPOS;
    }
}
