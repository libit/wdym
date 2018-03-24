/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class SmsCodeVerifyInfo
{
	@SerializedName("number")
	private String number;
	@SerializedName("verifyType")
	private String verifyType;
	@SerializedName("ip")
	private String ip;
	@SerializedName("code")
	private String code;
	@SerializedName("verifyDateLong")
	private Long verifyDateLong;
	@SerializedName("status")
	private byte status;
}