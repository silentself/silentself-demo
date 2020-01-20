package com.atdyl.mybatisplus.service;

import com.atdyl.mybatisplus.entities.AreaPO;
import com.baomidou.mybatisplus.extension.service.IService;

public interface SnowService extends IService<AreaPO> {
    void add(AreaPO areaPO);
}
