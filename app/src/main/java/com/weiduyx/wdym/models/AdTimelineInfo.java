/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class AdTimelineInfo
{
	@SerializedName("timelineId")
	private String timelineId;
	@SerializedName("name")
	private String name;
	@SerializedName("startTime")
	private int startTime;
	@SerializedName("endTime")
	private int endTime;
	@SerializedName("status")
	private byte status;
	@SerializedName("addDateLong")
	private long addDateLong;
	@SerializedName("updateDateLong")
	private long updateDateLong;
}