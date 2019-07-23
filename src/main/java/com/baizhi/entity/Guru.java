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
@Table ( name ="c_guru" )
public class Guru  implements Serializable {
	private static final long serialVersionUID =  5225730350576500552L;

   	@Column(name = "id" )
	@Id
	private String id;

	/**
	 * 上师的名字
	 */
   	@Column(name = "name" )
	private String name;

	/**
	 * 上师的照片
	 */
   	@Column(name = "photo" )
	private String photo;

	/**
	 * 上师的状态
	 */
   	@Column(name = "status" )
	private String status;

	/**
	 * 性别
	 */
   	@Column(name = "sex" )
	private String sex;

}
