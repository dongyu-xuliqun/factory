/*
 * package com.cdf.factory.oauth.jwt.auth.server.entity;
 * 
 * import java.util.ArrayList; import java.util.Collection; import
 * java.util.Date; import java.util.List;
 * 
 * import javax.persistence.Entity; import javax.persistence.GeneratedValue;
 * import javax.persistence.GenerationType; import javax.persistence.Id; import
 * javax.persistence.Table;
 * 
 * import org.springframework.security.core.GrantedAuthority; import
 * org.springframework.security.core.authority.SimpleGrantedAuthority; import
 * org.springframework.security.core.userdetails.UserDetails;
 * 
 *//**
	 * Created by xw on 2017/3/17. 2017-03-17 19:56
	 *//*
		 * //@Data
		 * 
		 * @Entity
		 * 
		 * @Table(name = "user") public class User implements UserDetails {
		 * 
		 * @Id
		 * 
		 * @GeneratedValue(strategy = GenerationType.IDENTITY) private int id;
		 * 
		 * private String name;
		 * 
		 * private String username;
		 * 
		 * private String password;
		 * 
		 * private Date createAt;
		 * 
		 * @Override public Collection<? extends GrantedAuthority> getAuthorities() {
		 * List<GrantedAuthority> auths = new ArrayList<>(); auths.add(new
		 * SimpleGrantedAuthority("ROLE_USER")); return auths; }
		 * 
		 * @Override public String getUsername() { return this.username; }
		 * 
		 * @Override public String getPassword() { return this.password; }
		 * 
		 * @Override public boolean isAccountNonExpired() { return true; }
		 * 
		 * @Override public boolean isAccountNonLocked() { return true; }
		 * 
		 * @Override public boolean isCredentialsNonExpired() { return true; }
		 * 
		 * @Override public boolean isEnabled() { return true; }
		 * 
		 * public String getName() { return name; }
		 * 
		 * public void setName(String name) { this.name = name; }
		 * 
		 * public Date getCreateAt() { return createAt; }
		 * 
		 * public void setCreateAt(Date createAt) { this.createAt = createAt; }
		 * 
		 * public int getId() { return id; }
		 * 
		 * public void setId(int id) { this.id = id; } }
		 */