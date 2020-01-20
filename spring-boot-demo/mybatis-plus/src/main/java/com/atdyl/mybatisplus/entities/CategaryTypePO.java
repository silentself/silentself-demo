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
 * @version V1.0 2020/1/9 10:54
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("tbl_snow_d_cpy_category_copy")
public class CategaryTypePO {

    @TableId(type = IdType.AUTO)
    private Long cId;

    private Long id;

    private String name;

    private String enName;

    private Long parentId;

}
