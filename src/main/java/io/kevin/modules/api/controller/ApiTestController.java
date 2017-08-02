package io.kevin.modules.api.controller;

import io.kevin.common.utils.Result;
import io.kevin.modules.api.annotation.AuthIgnore;
import io.kevin.modules.api.annotation.LoginUser;
import io.kevin.modules.api.entity.TokenEntity;
import io.kevin.modules.api.entity.UserEntity;
import org.springframework.web.bind.annotation.*;

/**
 * API测试接口
 * @author ZGJ
 * @date 2017/8/2 21:37
 **/
@RestController
@RequestMapping("/api")
public class ApiTestController {

    /**
     * 获取用户信息
     * @param user
     * @return
     */
    @GetMapping("/userInfo")
    public Result userInfo(@LoginUser UserEntity user) {
        return Result.ok().put("user", user);
    }

    /**
     * 忽略token验证测试
     * @return
     */
    @AuthIgnore
    @GetMapping("/notToken")
    public Result notToken() {
        return Result.ok().put("msg", "无需token也能访问");
    }

    /**
     * 接收json数据
     * @param user
     * @param token
     * @return
     */
    @PostMapping("/jsonData")
    public Result jsonData(@LoginUser UserEntity user, @RequestBody TokenEntity token) {
        return Result.ok().put("user", user).put("token", token);
    }
}
