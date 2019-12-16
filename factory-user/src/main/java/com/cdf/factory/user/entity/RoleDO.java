package com.cdf.factory.user.entity;

import java.lang.Long;
import java.lang.String;
import java.lang.Integer;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleDO {
   private Long id;
   private Long partnerId;
   private String name;
   private String authority;
   private Integer dataScope;
   private Integer roleCategory;
   private Integer sort;
   private Integer status;
   private String remark;
   private LocalDateTime createTime;
   private LocalDateTime updateTime;
}