package com.atdyl.smartdocdemo.entities.feo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Dong YL
 * @version V1.0 2019/12/20 10:51
 */
@Data
public class RsqParamFEO {

    /**
     * 用户名称
     */
    @NotBlank(message = "用户名不能为空")
    private String username;
}
