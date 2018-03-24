/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class AgentRebateRatioInfo
{
	@SerializedName("ratioId")
	private String ratioId;
	@SerializedName("userId")
	private String userId;
	@SerializedName("ratioType")
	private String ratioType;
	@SerializedName("ratio")
	private byte ratio;
	@SerializedName("addDateLong")
	private long addDateLong;
	@SerializedName("updateDateLong")
	private long updateDateLong;
}