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
@Table(name = "c_article")
public class Article implements Serializable {
    private static final long serialVersionUID = 5543810671024484982L;

    @Column(name = "id")
    @Id
    private String id;

    /**
     * 标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 文章的内容
     */
    @Column(name = "content")
    private String content;

    /**
     * 文章的上传时间
     */
    @Column(name = "create_date")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    /**
     * 上师和文章的 Id 关联
     */
    @Column(name = "guru_id")
    private String guruId;

}
