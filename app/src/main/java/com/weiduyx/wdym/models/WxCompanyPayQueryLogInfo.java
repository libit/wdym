/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class WxCompanyPayQueryLogInfo
{
	@SerializedName("partnerTradeNo")
	private String partnerTradeNo;
	@SerializedName("mchId")
	private String mchId;
	@SerializedName("detailId")
	private String detailId;
	@SerializedName("status")
	private String status;
	@SerializedName("reason")
	private String reason;
	@SerializedName("openid")
	private String openid;
	@SerializedName("transferName")
	private String transferName;
	@SerializedName("paymentAmount")
	private String paymentAmount;
	@SerializedName("transferTime")
	private String transferTime;
	@SerializedName("descs")
	private String descs;
	@SerializedName("rawInfo")
	private String rawInfo;
	@SerializedName("addDateLong")
	private long addDateLong;
}