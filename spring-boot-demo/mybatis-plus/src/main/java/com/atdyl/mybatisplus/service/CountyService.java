package com.atdyl.mybatisplus.service;

import com.atdyl.mybatisplus.entities.AreaPO;
import com.atdyl.mybatisplus.entities.CountyPO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface CountyService extends IService<CountyPO> {
    List<AreaPO> getList();

}
