package com.sqzr.ccbbs.core.util;

import com.chinamedcom.auth.entity.PhrMember;
import com.chinamedcom.auth.entity.PhrRole;
import com.chinamedcom.user.entity.PhrPerson;
import com.xiaoleilu.hutool.StrUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * 权限相关工具类
 * User: WeiYang
 * Date: 2015/6/5
 * Time: 17:22
 */
public class AuthUtil {
    private static PhrMember member = null;

    /**
     * 获取当前登陆用户的id
     *
     * @return 当前登陆用户id
     */
    public static Integer getId() {
        PhrMember member = getMember();
        return (member != null) ? member.getId() : null;
    }

    /**
     * 获取当前登陆用户的memeber对象
     *
     * @return member
     */
    public static PhrMember getMember() {
        Subject subject = SecurityUtils.getSubject();
        Object user = subject.getPrincipal();
        if (!(user instanceof PhrMember)) {
            return null;
        }
        return (PhrMember) user;
    }

    /**
     * 获取当前登陆用户的基本信息对象
     *
     * @return 基本信息对象
     */
    public static PhrPerson getPerson() {
        Subject subject = SecurityUtils.getSubject();
        Object user = subject.getPrincipal();
        if (!(user instanceof PhrMember)) {
            return null;
        }
        member = (PhrMember) user;
        return member.getPerson();
    }

    /**
     * 获取当前登陆用户的基本信息id
     *
     * @return基本信息id
     */
    public static int getPersonId() {
        return getPerson().getId();
    }

    /**
     * 判断当前是否登录
     *
     * @param remembered 是否保持登录
     *                   true:保持登录或者已登录都会返回true
     *                   false:只有当前会话已登录才会返回true
     * @return true or false
     */
    public static boolean isLogined(boolean remembered) {
        Subject subject = SecurityUtils.getSubject();
        if (remembered) {
            return (subject.isAuthenticated() || subject.isRemembered());
        } else {
            return (subject.isAuthenticated());
        }
    }

    /**
     * 判断当前是否登录或者处于保持登陆状态
     *
     * @return true or false
     */
    public static boolean isLogined() {
        return isLogined(true);
    }


    /**
     * 判断当前登陆的用户是否为管理员
     *
     * @return true or false
     */
    public static boolean isAdmin() {
        return SecurityUtils.getSubject().hasRole("admin");
    }

    /**
     * 判断当前登陆的用户是否为医生
     *
     * @return true or false
     */
    public static boolean isDoctor() {
        return SecurityUtils.getSubject().hasRole("doctor");
    }

    /**
     * 判断用户是否为医生
     *
     * @param member 用户对象,需判断权限的用户
     * @return true or false
     */
    public static boolean isDoctor(PhrMember member) {
        if (member.getRoles() == null) {
            return false;
        } else {
            for (PhrRole _role : member.getRoles()) {
                if (!StrUtil.isBlank(_role.getNameEn()) || "doctor".equals(_role.getNameEn())) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * 判断当前登陆的用户是否为居民
     *
     * @return true or false
     */
    public static boolean isResident() {
        return SecurityUtils.getSubject().hasRole("resident");
    }

    /**
     * 判断用户是否为居民
     *
     * @param member 用户对象,需判断权限的用户
     * @return true or false
     */
    public static boolean isResident(PhrMember member) {
        if (member.getRoles() == null) {
            return false;
        } else {
            for (PhrRole _role : member.getRoles()) {
                if (!StrUtil.isBlank(_role.getNameEn()) || "resident".equals(_role.getNameEn())) {
                    return true;
                }
            }
            return false;
        }
    }

}
