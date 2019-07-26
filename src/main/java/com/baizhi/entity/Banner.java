package com.baizhi.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
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
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate(value = true)
@DynamicInsert(value = true)
@Table(name = "c_banner")
@ToString
public class Banner implements Serializable {
    private static final long serialVersionUID = 7224913183324570654L;

    @Id
    @Column(name = "id")
    private String id;

    /**
     * 图片的名字
     */
    @Column(name = "name")
    private String name;

    /**
     * 封面
     */
    @Column(name = "cover")
    private String cover;

    /**
     * 描述
     */
    @Column(name = "description")
    private String description;

    /**
     * 状态
     */
    @Column(name = "status")
    private String status;

    /**
     * 轮播图的上传时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_date")
    private Date createDate;

}
