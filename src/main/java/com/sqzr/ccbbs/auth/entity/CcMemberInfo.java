package com.sqzr.ccbbs.auth.entity;

import com.sqzr.ccbbs.core.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * Created by weiyang on 2015/6/11.
 */
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
    @Basic
    @Column(name = "website")
    private String website;
    @Basic
    @Column(name = "company")
    private String company;
    @Basic
    @Column(name = "tagline")
    private String tagline;
    @Basic
    @Column(name = "bio")
    private String bio;
    @Basic
    @Column(name = "avatar")
    private String avatar;

}
