package com.cdf.factory.consumer.entity;
import java.lang.Long;
import java.lang.String;
import java.lang.Integer;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="sale_order")
public class OrderDO{
	@Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
   private Long id;
   private String orderNo;
   private Long partnerId;
   private Long productId;
   private String productName;
   private String pictureUrl;
   private String payment;
   private int count;
   private Long userId;
   private Integer paymentType;
   private String postFee;
   private Integer status;
   private LocalDateTime createTime;
   private LocalDateTime updateTime;
   private LocalDateTime paymentTime;
   private LocalDateTime consignTime;
   private LocalDateTime endTime;
   private LocalDateTime closeTime;
   private String shippingName;
   private String shippingCode;
   private String buyerMessage;
   private String buyerNick;
   private String buyerPhone;
   private Integer buyerRate;
}