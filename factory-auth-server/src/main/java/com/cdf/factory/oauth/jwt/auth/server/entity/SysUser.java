package com.cdf.factory.oauth.jwt.auth.server.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

/**
 * Created by xw on 2017/3/17. 2017-03-17 19:56
 */
@Data
@Entity
@Table(name = "user")
public class SysUser implements UserDetails {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private String id;
	
	@Column(name = "user_no", unique = true, nullable = false, length = 20)
	private String userNo;

	@Column(name = "mobile", unique = true, nullable = false, length = 15)
	private String mobile;

	@Column(name = "user_name", unique = true, nullable = false, length = 64)
	private String username;
	
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
	
	@Column(name = "status", unique = true, nullable = false, length = 4)
	private Byte status;
	
	@Column(name = "create_time", nullable = false)
	private Timestamp createTime;
	
	@Column(name = "update_time", nullable = false)
	private Timestamp updateTime;
	
	//private GrantedAuthority authorities;
	
	//public void setAuthorities(Collection<? extends GrantedAuthority> authorities) { this.authorities = authorities; }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> auths = new ArrayList<>();
		auths.add(new SimpleGrantedAuthority("ROLE_USER"));
		return auths;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}	
}
