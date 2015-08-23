package com.sqzr.ccbbs.auth.entity;

import com.sqzr.ccbbs.core.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * CC_member_info 用户信息基础表,记录用户的一些详细资料,个人网站等信息
 *
 * @author weiyang
 */
@EqualsAndHashCode(callSuper = false)
@Entity
@DynamicUpdate
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cc_member_info", schema = "", catalog = "ccbbs")
public class CcMemberInfo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // 字段: 个人网站
    @Basic
    @Column(name = "website")
    private String website;

    // 字段: 所在公司
    @Basic
    @Column(name = "company")
    private String company;

    // 字段: 头像地址
    @Basic
    @Column(name = "avatar")
    private String avatar;

}
