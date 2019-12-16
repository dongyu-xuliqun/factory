package com.cdf.factory.user.entity;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class UserDO{
   private Long id;
   private String userNo;
   private String mobile;
   private String userName;
   private String realName;
   private String nickName;
   private String password;
   private Integer age;
   private String email;
   private String photo;
   private Integer status;
   private LocalDateTime createTime;
   private LocalDateTime updateTime;
}