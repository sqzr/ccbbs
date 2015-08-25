package com.sqzr.ccbbs.auth.controller;

import com.sqzr.ccbbs.core.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 登录相关控制器
 *
 * @author weiyang
 */
@Controller
@RequestMapping("/auth")
public class LoginController extends BaseController {
    /**
     * 登录页面解析
     * @return 解析到 /WEB-INF/viewer/auth/login.jsp
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "/auth/login2";
    }
}
