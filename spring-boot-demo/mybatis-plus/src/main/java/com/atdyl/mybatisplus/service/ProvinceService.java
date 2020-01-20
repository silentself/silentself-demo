package com.atdyl.mybatisplus.service;

import com.atdyl.mybatisplus.entities.AreaPO;
import com.atdyl.mybatisplus.entities.ProvincePO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ProvinceService extends IService<ProvincePO> {
    List<AreaPO> getList();

}
