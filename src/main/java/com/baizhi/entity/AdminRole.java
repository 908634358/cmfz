package com.baizhi.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "c_adminRole")
public class AdminRole {
    @Id
    private String id;
    @Column(name = "adminId")
    private String adminId;
    @Column(name = "roleId")
    private String roleId;

}
