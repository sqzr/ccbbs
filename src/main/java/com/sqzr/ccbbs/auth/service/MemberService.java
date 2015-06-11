package com.sqzr.ccbbs.auth.service;

import com.sqzr.ccbbs.auth.entity.CcMember;

/**
 * 用户相关操作service
 * Created by weiyang on 2015/6/11.
 */
public interface MemberService {
    /**
     * 通过用户名查找
     *
     * @param username 用户名
     * @return Member对象
     */
    public CcMember findByUsername(String username);


    /**
     * 查找{@param username}在数据库中存在的个数
     * 用于注册时判断是否已存在等.
     *
     * @return 存在个数
     */
    public Integer countByUsername(String username);
}
