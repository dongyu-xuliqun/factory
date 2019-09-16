package com.cdf.factory.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Data;

@Entity
@Table(name = "f_user")
@Data
public class User{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private String id;
	
	@Column(name = "user_no", unique = true, nullable = false, length = 20)
	private String userNo;

	@Column(name = "mobile", unique = true, nullable = false, length = 15)
	private String mobile;

	@Column(name = "nick_name", unique = true, nullable = false, length = 64)
	private String nickName;
	
	@Column(name = "password", unique = true, nullable = false, length = 128)
	private String password;
	
	@Column(name = "age", length = 11)
	private Integer age;

	@Column(name = "email", length = 64)
	private String email;
	
	@Column(name = "photo", length = 255)
	private String photo;

	@Column(name = "real_name", length = 20)
	private String realName;
	
	@Column(name = "create_by", unique = true, nullable = false, length = 11)
	private Integer createBy;
	
	@Column(name = "update_by", unique = true, nullable = false, length = 11)
	private Integer updateBy;
	
	@Column(name = "status", unique = true, nullable = false, length = 4)
	private Byte status;
	
	@Column(name = "create_time", nullable = false)
	private Timestamp createTime;
	
	@Column(name = "update_time", nullable = false)
	private Timestamp updateTime;
	
	
}
