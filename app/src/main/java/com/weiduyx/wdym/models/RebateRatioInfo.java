/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class RebateRatioInfo
{
	@SerializedName("ratioId")
	private String ratioId;
	@SerializedName("name")
	private String name;
	@SerializedName("rebateType")
	private String rebateType;
	@SerializedName("userId")
	private String userId;
	@SerializedName("userRatio")
	private byte userRatio;
	@SerializedName("agentRatio")
	private byte agentRatio;
	@SerializedName("shopRatio")
	private byte shopRatio;
	@SerializedName("referrerRatio")
	private byte referrerRatio;
	@SerializedName("adReferrerRatio")
	private byte adReferrerRatio;
	@SerializedName("agentReferrerRatio")
	private byte agentReferrerRatio;
	@SerializedName("adminRatio")
	private byte adminRatio;
	@SerializedName("status")
	private byte status;
	@SerializedName("addDateLong")
	private long addDateLong;
}