package com.sqzr.ccbbs.auth.entity;

import com.sqzr.ccbbs.core.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * CC_member 用户表,记录了用户的帐号密码等信息
 *
 * @author weiyang
 */
@EqualsAndHashCode(callSuper = false)
@Entity
@DynamicUpdate
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cc_member", schema = "", catalog = "ccbbs")
public class CcMember extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;


    // 字段: 用户详细信息表，关联cc_member_info id
    @OneToOne
    @JoinColumn(name = "info_id", referencedColumnName = "id")
    private CcMemberInfo memberInfo;

    // 字段: 用户名
    @Basic
    @Column(name = "username")
    private String username;

    // 字段: 邮箱
    @Basic
    @Column(name = "email")
    private String email;

    // 字段: 密码
    @Basic
    @Column(name = "password")
    private String password;

    // 字段: 注册ip
    @Basic
    @Column(name = "ip")
    private String ip;

    // 字段: 注册useragnet
    @Basic
    @Column(name = "useragent")
    private String useragent;

    // 字段: 帐号的状态
    @Basic
    @Column(name = "status")
    private String status;

    // 字段: 密码salt
    @Basic
    @Column(name = "salt")
    private String salt;

    // 字段: 注册时间
    @Basic
    @Column(name = "date")
    private Timestamp date;


}
