package com.atdyl.mybatisplus.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.atdyl.mybatisplus.dao.CityDao;
import com.atdyl.mybatisplus.entities.AreaPO;
import com.atdyl.mybatisplus.entities.CityPO;
import com.atdyl.mybatisplus.service.CityService;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dong YL
 * @version V1.0 2020/1/7 17:44
 */
@Service
@DS("varys")
public class CityServiceImpl extends ServiceImpl<CityDao, CityPO> implements CityService {

    @Override
    public List<AreaPO> getList() {
        List<CityPO> cityPOS = this.baseMapper.selectList(new QueryWrapper<>());

        ArrayList<AreaPO> areaPOS = new ArrayList<>();

        cityPOS.forEach(cityPO -> {
            AreaPO areaPO = AreaPO.builder().build();
            BeanUtil.copyProperties(BeanUtil.trimStrFields(cityPO),areaPO);
            areaPOS.add(areaPO);
        });
        return areaPOS;
    }
}
