/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class WxShaomaOrderInfo
{
	@SerializedName("orderId")
	private String orderId;
	@SerializedName("userId")
	private String userId;
	@SerializedName("payType")
	private String payType;
	@SerializedName("price")
	private int price;
	@SerializedName("subject")
	private String subject;
	@SerializedName("comment")
	private String comment;
	@SerializedName("picUrl")
	private String picUrl;
	@SerializedName("status")
	private byte status;
	@SerializedName("addDateLong")
	private long addDateLong;
	@SerializedName("updateDateLong")
	private long updateDateLong;
}