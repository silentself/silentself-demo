package com.atdyl.smartdocdemo.comtroller;

import com.atdyl.smartdocdemo.entities.feo.RsqParamFEO;
import com.atdyl.smartdocdemo.entities.vo.ResultVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 测试controller
 *
 * @author Dong YL
 * @version V1.0 2019/12/20 10:47
 */
@RestController
public class HelloController {

    /**
     * 测试smart-doc
     *
     * @param object 请求参数
     * @return result
     */
    @PostMapping("/hello")
    public ResultVO hello(@Valid @RequestBody RsqParamFEO object) {

        return null;
    }

    /**
     * 测试smart-doc2
     *
     * @param object 请求参数
     * @return result
     */
    @PostMapping("/hello2")
    public ResultVO hello2(@Valid @RequestBody RsqParamFEO object) {

        return null;
    }
}
