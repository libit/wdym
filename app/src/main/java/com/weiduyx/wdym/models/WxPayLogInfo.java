/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class WxPayLogInfo
{
	@SerializedName("payId")
	private String payId;
	@SerializedName("userId")
	private String userId;
	@SerializedName("wxAppId")
	private String wxAppId;
	@SerializedName("outTradeNo")
	private String outTradeNo;
	@SerializedName("feeType")
	private String feeType;
	@SerializedName("totalFee")
	private int totalFee;
	@SerializedName("tradeType")
	private String tradeType;
	@SerializedName("openId")
	private String openId;
	@SerializedName("rawInfo")
	private String rawInfo;
	@SerializedName("remark")
	private String remark;
	@SerializedName("status")
	private byte status;
	@SerializedName("addDateLong")
	private long addDateLong;
	@SerializedName("updateDateLong")
	private long updateDateLong;
}