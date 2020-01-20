package com.atdyl.mybatisplus.service;

import com.atdyl.mybatisplus.entities.AreaPO;
import com.atdyl.mybatisplus.entities.CityPO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface CityService extends IService<CityPO> {

    List<AreaPO> getList();
}
