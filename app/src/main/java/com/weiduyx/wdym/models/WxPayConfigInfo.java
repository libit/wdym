/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class WxPayConfigInfo
{
	@SerializedName("payId")
	private String payId;
	@SerializedName("wxPublicId")
	private String wxPublicId;
	@SerializedName("mchId")
	private String mchId;
	@SerializedName("apiKey")
	private String apiKey;
	@SerializedName("appId")
	private String appId;
	@SerializedName("status")
	private byte status;
	@SerializedName("addDateLong")
	private long addDateLong;
	@SerializedName("updateDateLong")
	private long updateDateLong;
}