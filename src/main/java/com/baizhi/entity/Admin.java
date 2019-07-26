package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description
 * @Author JKB
 * @Date 2019-07-09
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "c_admin")
@ToString
public class Admin implements Serializable {
    private static final long serialVersionUID = 8726059359884296778L;

    /**
     * 管理员的id
     */
    @Id
    @Column(name = "id")
    private String id;

    /**
     * 用户名
     */
    @Column(name = "username")
    private String username;

    /**
     * 密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 昵称
     */
    @Column(name = "nickname")
    private String nickname;

}
