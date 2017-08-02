package io.kevin.modules.api.controller;

import io.kevin.common.utils.Result;
import io.kevin.common.validator.Assert;
import io.kevin.modules.api.annotation.AuthIgnore;
import io.kevin.modules.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注册
 * @author ZGJ
 * @date 2017/8/2 21:32
 **/
@RestController
@RequestMapping("/api")
public class ApiRegisterController {
    @Autowired
    private UserService userService;

    @AuthIgnore
    @PostMapping("/register")
    public Result register(String mobile, String password) {
        Assert.isBlank(mobile, "手机号不能为空");
        Assert.isBlank(password, "密码不能为空");

        userService.save(mobile, password);
        return Result.ok();
    }
}
