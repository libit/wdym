/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class WxRedpackPayLogInfo
{
	@SerializedName("payId")
	private String payId;
	@SerializedName("userId")
	private String userId;
	@SerializedName("redpackType")
	private String redpackType;
	@SerializedName("wxAppId")
	private String wxAppId;
	@SerializedName("mchId")
	private String mchId;
	@SerializedName("mchBillno")
	private String mchBillno;
	@SerializedName("sendName")
	private String sendName;
	@SerializedName("openId")
	private String openId;
	@SerializedName("totalAmount")
	private int totalAmount;
	@SerializedName("totalNum")
	private int totalNum;
	@SerializedName("wishing")
	private String wishing;
	@SerializedName("clientIp")
	private String clientIp;
	@SerializedName("actName")
	private String actName;
	@SerializedName("remark")
	private String remark;
	@SerializedName("rawInfo")
	private String rawInfo;
	@SerializedName("status")
	private byte status;
	@SerializedName("addDateLong")
	private long addDateLong;
	@SerializedName("updateDateLong")
	private long updateDateLong;
}