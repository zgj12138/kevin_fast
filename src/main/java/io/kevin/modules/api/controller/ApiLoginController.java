package io.kevin.modules.api.controller;

import io.kevin.common.utils.Result;
import io.kevin.common.validator.Assert;
import io.kevin.modules.api.annotation.AuthIgnore;
import io.kevin.modules.api.service.TokenService;
import io.kevin.modules.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * API授权登录
 * @author ZGJ
 * @date 2017/8/2 21:25
 **/
@RestController
@RequestMapping("/api")
public class ApiLoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;

    @AuthIgnore
    @PostMapping("login")
    public Result login(String mobile, String password) {
        Assert.isBlank(mobile, "手机号不能为空");
        Assert.isBlank(password, "密码不能为空");

        //用户登录
        long userId = userService.login(mobile, password);

        //生成token
        Map<String, Object> map = tokenService.createToken(userId);
        return Result.ok(map);
    }
}
