package com.baizhi.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name = "c_chapter")
public class Chapter implements Serializable {
    private static final long serialVersionUID = 3054053343364737746L;

    @Column(name = "id")
    @Id
    private String id;

    /**
     * 章节名称
     */
    @Column(name = "title")
    private String title;

    /**
     * 章节的大小
     */
    @Column(name = "size")
    private String size;

    /**
     * 时长
     */
    @Column(name = "duration")
    private String duration;

    /**
     * 文件名(带后缀)
     */
    @Column(name = "name")
    private String name;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    /**
     * 关联专辑的id
     */
    @Column(name = "album_id")
    private String albumId;

}
