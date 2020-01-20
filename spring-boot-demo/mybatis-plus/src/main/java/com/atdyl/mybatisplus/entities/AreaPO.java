package com.atdyl.mybatisplus.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Dong YL
 * @version V1.0 2020/1/7 10:57
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("tbl_snow_d_area")
public class AreaPO {

    /**
     * 行政区划
     */
    @TableId
    private Long code;

    /**
     * 地区名称
     */
    private String name;

    /**
     * 父级行政区划
     */
    private Long parentCode;

    private Integer level;

}
