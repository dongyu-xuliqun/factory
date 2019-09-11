package com.cdf.factory.oauth.jwt.auth.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdf.factory.oauth.jwt.auth.server.entity.SysUser;

/**
 * Created by xw on 2017/3/17. 2017-03-17 19:59
 */
public interface SysUserRepository extends JpaRepository<SysUser, Integer> {

	SysUser findOneByUsername(String userName);
}
