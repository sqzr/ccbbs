package com.sqzr.ccbbs.auth.repository;

import com.sqzr.ccbbs.auth.entity.CcMember;
import com.sqzr.ccbbs.core.base.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * 用户操作相关repository
 * Created by weiyang on 2015/6/11.
 */
@Repository("memberRepository")
public interface MemberRepository extends BaseRepository<CcMember, Integer> {
    CcMember findByUsername(String username);

    Integer countByUsername(String username);
}
