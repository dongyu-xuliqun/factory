package com.cdf.factory.user.entity;
import java.lang.Long;
import java.lang.String;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class OrderDO{
   private Long id;
   private String orderNo;
   private Long merchantId;
   private String productName;
   private Long productId;
   private String pictureUrl;
   private Integer count;
   private String payment;
   private Long userId;
   private Integer paymentType;
   private String postFee;
   private Integer orderStatus;
   private LocalDateTime paymentTime;
   private LocalDateTime consignTime;
   private LocalDateTime endTime;
   private LocalDateTime closeTime;
   private String shippingName;
   private String shippingCode;
   private String buyerPhone;
   private String buyerMessage;
   private String buyerNick;
   private Integer buyerRate;
   private Integer status;
   private LocalDateTime createTime;
   private LocalDateTime updateTime;
}