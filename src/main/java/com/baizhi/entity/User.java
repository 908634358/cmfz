package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description
 * @Author JKB
 * @Date 2019-07-09
 */

@Data
@Entity
@AllArgsConstructor // lombok 注解 有参构造
@NoArgsConstructor // lombok 注解
@Table(name = "c_user")
public class User implements Serializable {
    private static final long serialVersionUID = 6948635306525867022L;

    @Column(name = "id")
    @Id
    @Excel(name = "编号", width = 20, height = 15) // essypoi 注解
    private String id;

    /**
     * 电话号码
     */
    @Column(name = "phone")
    @Excel(name = "电话号码", width = 20, height = 15)
    private String phone;

    /**
     * 用户名
     */
    @Column(name = "username")
    @Excel(name = "用户名", width = 20, height = 15)
    private String username;

    /**
     * 密码
     */
    @Excel(name = "密码", width = 20, height = 15)
    @Column(name = "password")
    private String password;

    /**
     * 盐值
     */
    @Excel(name = "盐值", width = 20, height = 15)
    @Column(name = "salt")
    private String salt;

    /**
     * 法名
     */
    @Excel(name = "上师的Id", width = 20, height = 15)
    @Column(name = "guru_id")
    private String guruId;
    //省份
    @Column(name = "province")
    @Excel(name = "省份", width = 20, height = 15)
    private String province;

    /**
     * 城市
     */
    @Excel(name = "城市", width = 20, height = 15)
    @Column(name = "city")
    private String city;

    /**
     * 签名
     */
    @Excel(name = "签名", width = 20, height = 15)
    @Column(name = "sign")
    private String sign;

    /**
     * 照片
     * 照片的位置：需要远程网络地址的位置
     * 如：http://localhost:9090/cmfz/user/img/图片名字.格式
     * 因为图片在远程服务器中储存，我需要在导出表格的时候传入图片地址
     */
    @Excel(name = "照片", width = 20, height = 15, type = 2)
    @Column(name = "photo")
    private String photo;

    /**
     * 创建时间
     */
    @Excel(name = "注册时间", width = 20, height = 15, format = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_date")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @Excel(name = "性别")
    @Column(name = "sex")
    private String sex;

}
