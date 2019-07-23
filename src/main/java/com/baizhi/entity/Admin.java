package com.baizhi.entity;

import javax.persistence.*;
import java.io.Serializable;

import lombok.*;

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
@Table ( name ="c_admin" )
@ToString
public class Admin  implements Serializable {
	private static final long serialVersionUID =  8726059359884296778L;

	/**
	 * 管理员的id

	 */
	@Id
   	@Column(name = "id" )
	private String id;

	/**
	 * 用户名
	 */
   	@Column(name = "username" )
	private String username;

	/**
	 * 密码
	 */
   	@Column(name = "password" )
	private String password;

	/**
	 * 昵称
	 */
   	@Column(name = "nickname" )
	private String nickname;

}
