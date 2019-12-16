package com.cdf.factory.user.entity;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class AdminDO{
   private Long id;
   private Long merchantId;
   private String userName;
   private String nickName;
   private String password;
   private String salt;
   private String email;
   private String telephone;
   private Integer gender;
   private Integer sort;
   private Integer status;
   private String remark;
   private String loginIp;
   private LocalDateTime loginDate;
   private LocalDateTime createTime;
   private LocalDateTime updateTime;
}