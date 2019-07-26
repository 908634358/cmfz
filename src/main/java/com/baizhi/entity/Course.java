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
@Table ( name ="c_course" )
public class Course  implements Serializable {
	private static final long serialVersionUID =  1057396494840880351L;

   	@Column(name = "id" )
	@Id
	private String id;

	/**
	 * 和用户的id关联
	 */
   	@Column(name = "user_id" )
	private String userId;

	/**
	 * 标题
	 */
   	@Column(name = "title" )
	private String title;

	/**
	 * 标识，必修课和选修课的标识
	 */
   	@Column(name = "mark" )
	private String mark;

	/**
	 * 创建时间
	 */
   	@Column(name = "create_date" )
	private Date createDate;

}
