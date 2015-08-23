package com.sqzr.ccbbs.auth.service;

import com.sqzr.ccbbs.auth.entity.CcMember;
import com.sqzr.ccbbs.auth.repository.MemberRepository;
import com.sqzr.ccbbs.core.base.BaseRepository;
import com.sqzr.ccbbs.core.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户服务类,操作用户表或用户基本信息
 *
 * @author weiyang
 */
@Service
public class MemberService extends BaseService<CcMember,Integer> {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    protected BaseRepository<CcMember, Integer> getEntityRepository() {
        return memberRepository;
    }

    /**
     * 通过用户名查询用户
     * @param username  用户名
     * @return PhrMember
     */
    public CcMember findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }
}
