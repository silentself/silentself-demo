package com.atdyl.websocket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Dong YL
 * @version V1.0 2020/1/2 16:34
 */
@Controller
public class WebSocketController {

    //跳转websocket
    @GetMapping("/websocket/index")
    public String websocketIndex() {

        return "websocket";
    }

    //跳转websocket
    @GetMapping("/index")
    public String index() {

        return "/WEB-INF/index.jsp";
    }

}
