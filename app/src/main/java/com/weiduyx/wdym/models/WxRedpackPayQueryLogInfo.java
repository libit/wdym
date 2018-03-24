/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class WxRedpackPayQueryLogInfo
{
	@SerializedName("mchBillno")
	private String mchBillno;
	@SerializedName("mchId")
	private String mchId;
	@SerializedName("detailId")
	private String detailId;
	@SerializedName("status")
	private String status;
	@SerializedName("sendType")
	private String sendType;
	@SerializedName("hbType")
	private String hbType;
	@SerializedName("totalNum")
	private String totalNum;
	@SerializedName("totalAmount")
	private String totalAmount;
	@SerializedName("reason")
	private String reason;
	@SerializedName("sendTime")
	private String sendTime;
	@SerializedName("refundTime")
	private String refundTime;
	@SerializedName("refundAmount")
	private String refundAmount;
	@SerializedName("wishing")
	private String wishing;
	@SerializedName("remark")
	private String remark;
	@SerializedName("actName")
	private String actName;
	@SerializedName("rawInfo")
	private String rawInfo;
	@SerializedName("addDateLong")
	private long addDateLong;
}