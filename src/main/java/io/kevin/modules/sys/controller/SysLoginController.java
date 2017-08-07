package io.kevin.modules.sys.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.sun.org.apache.regexp.internal.RE;
import io.kevin.common.annotation.SysLog;
import io.kevin.common.utils.Result;
import io.kevin.common.utils.ShiroUtils;
import io.kevin.modules.sys.entity.SysUserEntity;
import io.kevin.modules.sys.entity.SysUserTokenEntity;
import io.kevin.modules.sys.service.SysUserService;
import io.kevin.modules.sys.service.SysUserTokenService;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

/**
 * 登录相关
 * @author ZGJ
 * @date 2017/7/16 14:35
 **/
@RestController
public class SysLoginController extends BaseController {
    @Autowired
    private Producer producer;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserTokenService sysUserTokenService;

    @GetMapping("captcha.jpg")
    public void captcha(HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-store no-cache");
        response.setContentType("image/jpeg");

        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        //保存到shiro session
        ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);

        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(image, "jpg", outputStream);
        IOUtils.closeQuietly(outputStream);
    }
    @SysLog("用户登录")
    @PostMapping("/sys/login")
    public Map<String, Object> login(String username, String password, String captcha) throws IOException {
        String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
        if(!captcha.equalsIgnoreCase(kaptcha)){
            return Result.error("验证码不正确");
        }

        //用户信息
        SysUserEntity user = sysUserService.queryByUserName(username);

        //账号不存在、密码错误
        if(user == null || !user.getPassword().equals(new Sha256Hash(password, user.getSalt()).toHex())) {
            return Result.error("账号或密码不正确");
        }

        //账号锁定
        if(user.getStatus() == 0){
            return Result.error("账号已被锁定,请联系管理员");
        }

        //生成token，并保存到数据库
        return sysUserTokenService.createToken(user.getUserId());
    }
    @SysLog("退出登录")
    @PostMapping("/sys/logout")
    public Result logout() {
        sysUserTokenService.logout(getUserId());
        return Result.ok();
    }
}
