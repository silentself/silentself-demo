package com.atdyl.mybatisplus.dao;

import com.atdyl.mybatisplus.entities.ProductTypePO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Dong YL
 * @version V1.0 2020/1/8 14:00
 */
@Mapper
public interface ProductTypeDao extends BaseMapper<ProductTypePO> {
}
