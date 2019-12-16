package com.cdf.factory.user.entity;
import java.lang.Long;
import java.lang.String;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class MenuDO{
   private Long id;
   private String name;
   private Long parentId;
   private String remark;
   private String route;
   private String path;
   private String authority;
   private Integer type;
   private Integer isVisible;
   private String icon;
   private Integer sort;
   private Integer status;
   private LocalDateTime createTime;
   private LocalDateTime updateTime;
}