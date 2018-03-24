/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class PromotionRangeInfo
{
	@SerializedName("rangeId")
	private String rangeId;
	@SerializedName("name")
	private String name;
	@SerializedName("rangeType")
	private String rangeType;
	@SerializedName("rangeValue")
	private String rangeValue;
	@SerializedName("status")
	private byte status;
	@SerializedName("addDateLong")
	private long addDateLong;
}