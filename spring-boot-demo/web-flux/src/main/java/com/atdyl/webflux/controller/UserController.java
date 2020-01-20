package com.atdyl.webflux.controller;

import com.atdyl.webflux.pojo.SexEnum;
import com.atdyl.webflux.pojo.User;
import com.atdyl.webflux.service.UserService;
import com.atdyl.webflux.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Dong YL
 * @version V1.0 2020/1/2 18:37
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("/{id}")
    public Mono<UserVO> getUser(@PathVariable("id") Long id) {
        return userService.getUser(id)
                .map(this::translate);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteUser(@PathVariable("id") Long id) {
        return userService.deleteUser(id);
    }

    @GetMapping("/{userName}/{note}")
    public Flux<UserVO> findUsers(@PathVariable("userName") String userName, @PathVariable("note") String note) {
        User build = User.builder().sex(SexEnum.FEMALE).note("111").userName("222").build();
        mongoTemplate.insert(build);
        return userService.findUsers(userName, note)
                .map(this::translate);
    }

    //添加有一定的问题
//    @PostMapping("/add")
//    public Mono<UserVO> insertUser(@RequestBody User user){
//
//       return userService.insertUser(user)
//               .map(this::translate);
//    }


    private UserVO translate(User user) {
        UserVO userVO = new UserVO();
        userVO.setUserName(user.getUserName());
        userVO.setSexCode(user.getSex().getCode());
        userVO.setSexName(user.getSex().getSex());
        userVO.setNote(user.getNote());
        return userVO;
    }
}
