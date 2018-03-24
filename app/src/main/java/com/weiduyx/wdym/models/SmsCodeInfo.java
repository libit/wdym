/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class SmsCodeInfo
{
	@SerializedName("number")
	private String number;
	@SerializedName("verifyType")
	private String verifyType;
	@SerializedName("code")
	private String code;
	@SerializedName("startDateLong")
	private Long startDateLong;
	@SerializedName("validateTimeLong")
	private int validateTimeLong;
	@SerializedName("status")
	private byte status;
	@SerializedName("addDateLong")
	private long addDateLong;
}