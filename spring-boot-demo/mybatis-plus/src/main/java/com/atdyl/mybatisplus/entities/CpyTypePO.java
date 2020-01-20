package com.atdyl.mybatisplus.entities;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Dong YL
 * @version V1.0 2020/1/8 13:41
 */
@Data
@TableName("test_name")
public class CpyTypePO {

    private String a;

    private String b;

    private String c;
}
