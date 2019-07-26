package com.baizhi.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description
 * @Author JKB
 * @Date 2019-07-09
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "cmfz", type = "article")
public class Article implements Serializable {
    private static final long serialVersionUID = 5543810671024484982L;

    @Id
    private String id;

    /**
     * 标题
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String title;

    /**
     * 文章的内容
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String content;

    /**
     * 文章的上传时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    /**
     * 作者
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String author;

}
