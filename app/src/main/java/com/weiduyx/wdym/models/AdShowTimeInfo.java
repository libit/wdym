/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class AdShowTimeInfo
{
	@SerializedName("adId")
	private String adId;
	@SerializedName("userId")
	private String userId;
	@SerializedName("redpackId")
	private String redpackId;
	@SerializedName("startTime")
	private long startTime;
	@SerializedName("endTime")
	private Long endTime;
	@SerializedName("showTime")
	private Integer showTime;
	@SerializedName("status")
	private byte status;
	@SerializedName("addDateLong")
	private long addDateLong;
	@SerializedName("updateDateLong")
	private long updateDateLong;
}