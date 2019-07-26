package com.baizhi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import java.util.Date;

/**
 * @Description  
 * @Author  JKB
 * @Date 2019-07-09 
 */

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table ( name ="c_counter" )
public class Counter  implements Serializable {
	private static final long serialVersionUID =  4623667785568654889L;

   	@Column(name = "id" )
	@Id
	private String id;

	/**
	 * 关联用户的id，查询用户的信息
	 */
   	@Column(name = "user_id" )
	private String userId;

	/**
	 * 关联计数器的id
	 */
   	@Column(name = "course_id" )
	private String courseId;

	/**
	 * 标题
	 */
   	@Column(name = "title" )
	private String title;

	/**
	 * 计数
	 */
   	@Column(name = "count" )
	private Long count;

	/**
	 * 创建时间
	 */
   	@Column(name = "create_date" )
	private Date createDate;

}
