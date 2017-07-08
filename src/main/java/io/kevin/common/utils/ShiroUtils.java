package io.kevin.common.utils;

import io.kevin.common.exception.GJException;
import io.kevin.modules.sys.entity.SysUserEntity;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;


/**
 * Shiro工具类
 * @author ZGJ
 * @date 2017/7/3 23:42
 **/
public class ShiroUtils {
    /**
     * 获取Session
     * @return
     */
    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    /**
     * 获取主体
     * @return
     */
    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    /**
     * 获取系统用户
     * @return
     */
    public static SysUserEntity getUserEntity() {
        return (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * 获取系统用户ID
     * @return
     */
    public static Long getUserId() {
        return getUserEntity().getUserId();
    }

    /**
     * 设置SessionAttribute
     * @param key
     * @param value
     */
    public static void setSessionAttribute(Object key, Object value) {
        getSession().setAttribute(key, value);
    }

    /**
     * 获取SessionAttribute的属性
     * @param key
     */
    public static Object getSessionAttribute(Object key) {
        return getSession().getAttribute(key);
    }
    /**
     * 是否已经登录
     */
    public static boolean isLogin() {
        return SecurityUtils.getSubject().getPrincipal() != null;
    }
    /**
     * 退出登录
     */
    public static void logout() {
        SecurityUtils.getSubject().logout();
    }

    /**
     * 获取验证码
     * @param key
     * @return
     */
    public static String getKaptcha(String key) {
        Object kaptcha = getSessionAttribute(key);
        if(kaptcha == null) {
            throw new GJException("验证码已失效");
        }
        getSession().removeAttribute(key);
        return kaptcha.toString();
    }
}
