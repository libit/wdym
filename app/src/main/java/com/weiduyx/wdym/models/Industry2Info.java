/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class Industry2Info
{
	@SerializedName("industryId")
	private String industryId;
	@SerializedName("name")
	private String name;
	@SerializedName("industry1Id")
	private String industry1Id;
	@SerializedName("status")
	private byte status;
	@SerializedName("addDateLong")
	private long addDateLong;
	@SerializedName("industry1Info")
	private Industry1Info industry1Info;
}