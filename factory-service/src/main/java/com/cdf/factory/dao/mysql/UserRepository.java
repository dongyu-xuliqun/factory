package com.cdf.factory.dao.mysql;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdf.factory.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

}
