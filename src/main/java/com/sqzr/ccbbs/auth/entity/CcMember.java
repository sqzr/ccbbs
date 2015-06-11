package com.sqzr.ccbbs.auth.entity;

import com.sqzr.ccbbs.core.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by weiyang on 2015/6/11.
 */
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
    @Basic
    @Column(name = "username")
    private String username;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "ip")
    private String ip;
    @Basic
    @Column(name = "useragent")
    private String useragent;
    @Basic
    @Column(name = "status")
    private String status;
    @Basic
    @Column(name = "salt")
    private String salt;
    @Basic
    @Column(name = "date")
    private Timestamp date;
    @OneToOne
    @JoinColumn(name = "info_id", referencedColumnName = "id")
    private CcMemberInfo memberInfo;

}
