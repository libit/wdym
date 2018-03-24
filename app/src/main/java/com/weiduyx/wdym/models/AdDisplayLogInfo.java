/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class AdDisplayLogInfo
{
	@SerializedName("logId")
	private String logId;
	@SerializedName("adId")
	private String adId;
	@SerializedName("adDeviceType")
	private String adDeviceType;
	@SerializedName("adType")
	private String adType;
	@SerializedName("deviceId")
	private String deviceId;
	@SerializedName("userId")
	private String userId;
	@SerializedName("wxOpenId")
	private String wxOpenId;
	@SerializedName("clicked")
	private byte clicked;
	@SerializedName("addDateLong")
	private long addDateLong;
	@SerializedName("updateDateLong")
	private long updateDateLong;
}