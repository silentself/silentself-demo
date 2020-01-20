package com.atdyl.smalldocdemo.controller;

import com.atdyl.smalldocdemo.entities.feo.RsqParamFEO;
import com.atdyl.smalldocdemo.entities.vo.ResultVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试controller
 *
 * @author Dong YL
 * @version V1.0 2019/12/20 11:14
 */
@RestController
@RequestMapping
public class HelloController {

    /**
     * 测试small-doc
     *
     * @param rsqParamFEO 请求参数 @{username*,resultVO.userName}
     * @return 权限列表
     * @apiNote 根据用户信息获取用户
     * @author Dong YL
     * @since 1.0
     */
    @PostMapping("/hello")
    public ResultVO hello(@RequestBody RsqParamFEO rsqParamFEO) {

        return null;
    }

    /**
     * 测试small-doc2
     *
     * @param rsqParamFEO 请求参数 @{username*}
     * @return result
     */
    @PostMapping("/hello2")
    public ResultVO hello2(@RequestBody RsqParamFEO rsqParamFEO) {

        return null;
    }
}
