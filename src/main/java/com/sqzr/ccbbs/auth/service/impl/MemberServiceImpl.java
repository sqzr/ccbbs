package com.sqzr.ccbbs.auth.service.impl;

import com.sqzr.ccbbs.auth.entity.CcMember;
import com.sqzr.ccbbs.auth.repository.MemberRepository;
import com.sqzr.ccbbs.auth.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by weiyang on 2015/6/11.
 */
@Service("memberService")
public class MemberServiceImpl implements MemberService {

    @Qualifier("memberRepository")
    @Autowired
    private MemberRepository memberRepository;

    @Override
    public CcMember findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    @Override
    public Integer countByUsername(String username) {
        return memberRepository.countByUsername(username);
    }
}
