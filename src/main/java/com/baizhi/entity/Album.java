package com.baizhi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description  
 * @Author  JKB
 * @Date 2019-07-09 
 */

@Data
@Entity
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table ( name ="c_album" )
public class Album  implements Serializable {
	private static final long serialVersionUID =  9016017487304583802L;

   	@Column(name = "id" )
	@Id
	private String id;

	/**
	 * 标题
	 */
   	@Column(name = "title" )
	private String title;

	/**
	 * 封面
	 */
   	@Column(name = "cover" )
	private String cover;

	/**
	 * 章节数
	 */
   	@Column(name = "count" )
	private String count;

	/**
	 * 得分
	 *
	 * // 如果出现无法转换的字符则无法转换
	 * NumberFormatException
	 */
   	@Column(name = "score" )
	private Long score;

	/**
	 * 作者
	 */
   	@Column(name = "author" )
	private String author;

	/**
	 * 播音员
	 */
   	@Column(name = "broadcast" )
	private String broadcast;

	/**
	 * 简介
	 */
   	@Column(name = "brief" )
	private String brief;

	/**
	 * 创建时间
	 */
   	@Column(name = "create_date" )
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createDate;

}
