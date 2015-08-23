package com.sqzr.ccbbs.core.controller;

import com.xiaoleilu.hutool.Log;
import com.xiaoleilu.hutool.log.LogWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 基础controller类,所有controller继承此类
 *
 * @author weiyang
 */
@Controller
public abstract class BaseController {
    protected final LogWrapper logger = Log.get(getClass());

    /**
     * 取得request对象
     *
     * @return HttpServletRequest
     */
    public HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 取得session对象
     *
     * @return HttpSession
     */
    public HttpSession getSession() {
        return getRequest().getSession();
    }
}
