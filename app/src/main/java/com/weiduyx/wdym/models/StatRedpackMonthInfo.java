/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class StatRedpackMonthInfo
{
	@SerializedName("dateMonth")
	private int dateMonth;
	@SerializedName("redpackCount")
	private int redpackCount;
	@SerializedName("money")
	private long money;
	@SerializedName("point")
	private long point;
	@SerializedName("addDateLong")
	private long addDateLong;
	@SerializedName("updateDateLong")
	private long updateDateLong;
}