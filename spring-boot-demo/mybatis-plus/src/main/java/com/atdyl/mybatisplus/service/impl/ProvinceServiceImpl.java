package com.atdyl.mybatisplus.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.atdyl.mybatisplus.dao.ProvinceDao;
import com.atdyl.mybatisplus.entities.AreaPO;
import com.atdyl.mybatisplus.entities.ProvincePO;
import com.atdyl.mybatisplus.service.ProvinceService;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dong YL
 * @version V1.0 2020/1/7 17:46
 */
@Service
@DS("varys")
public class ProvinceServiceImpl extends ServiceImpl<ProvinceDao, ProvincePO> implements ProvinceService {
    @Override
    public List<AreaPO> getList() {
        List<ProvincePO> provincePOS = this.baseMapper.selectList(new QueryWrapper<>());
        ArrayList<AreaPO> areaPOS = new ArrayList<>();
        provincePOS.forEach(provincePO -> {
            AreaPO areaPO = AreaPO.builder().build();
            provincePO.setParentCode(0L);
            BeanUtil.copyProperties(BeanUtil.trimStrFields(provincePO),areaPO);
            areaPOS.add(areaPO);
        });
        return areaPOS;
    }
}
