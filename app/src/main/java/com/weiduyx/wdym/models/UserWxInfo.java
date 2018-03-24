/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class UserWxInfo
{
	@SerializedName("wxOpenId")
	private String wxOpenId;
	@SerializedName("originalId")
	private String originalId;
	@SerializedName("userId")
	private String userId;
	@SerializedName("userFrom")
	private String userFrom;
	@SerializedName("wxUnionId")
	private String wxUnionId;
	@SerializedName("wxPublicId")
	private String wxPublicId;
	@SerializedName("subscribe")
	private Byte subscribe;
	@SerializedName("subscribeTime")
	private Long subscribeTime;
	@SerializedName("groupId")
	private Integer groupId;
	@SerializedName("wxRemark")
	private String wxRemark;
	@SerializedName("wxTagidList")
	private String wxTagidList;
	@SerializedName("wxStatus")
	private byte wxStatus;
	@SerializedName("status")
	private byte status;
	@SerializedName("addDateLong")
	private long addDateLong;
	@SerializedName("updateDateLong")
	private long updateDateLong;
}