/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class AdCooperationInfo
{
	@SerializedName("copId")
	private String copId;
	@SerializedName("name")
	private String name;
	@SerializedName("number")
	private String number;
	@SerializedName("copType")
	private String copType;
	@SerializedName("area")
	private String area;
	@SerializedName("content")
	private String content;
	@SerializedName("userId")
	private String userId;
	@SerializedName("adminId")
	private String adminId;
	@SerializedName("remark")
	private String remark;
	@SerializedName("verifyDateLong")
	private Long verifyDateLong;
	@SerializedName("status")
	private byte status;
	@SerializedName("addDateLong")
	private long addDateLong;
	@SerializedName("updateDateLong")
	private long updateDateLong;
}