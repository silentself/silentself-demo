package com.atdyl.smalldocdemo.entities.feo;

import com.atdyl.smalldocdemo.entities.vo.ResultVO;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author Dong YL
 * @version V1.0 2019/12/20 10:51
 */
@Data
public class RsqParamFEO {

    private ResultVO resultVO;

    /**
     * 用户名称
     */
    @NotBlank(message = "用户名不能为空")
    private String username;


}
