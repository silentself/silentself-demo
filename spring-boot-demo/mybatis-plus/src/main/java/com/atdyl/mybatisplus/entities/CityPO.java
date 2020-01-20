package com.atdyl.mybatisplus.entities;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Dong YL
 * @version V1.0 2020/1/7 11:00
 */
@Data
@TableName("tbl_varys_d_area_city")
public class CityPO {


    @TableField("ac_id")
    private Long code;

    @TableField("ac_name")
    private String name;

    @TableField("ac_province_id")
    private Long parentCode;
}
