/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class UserMakerApplyInfo
{
	@SerializedName("applyId")
	private String applyId;
	@SerializedName("userId")
	private String userId;
	@SerializedName("name")
	private String name;
	@SerializedName("number")
	private String number;
	@SerializedName("sex")
	private byte sex;
	@SerializedName("birthday")
	private long birthday;
	@SerializedName("identifityNumber")
	private String identifityNumber;
	@SerializedName("picUrl")
	private String picUrl;
	@SerializedName("money")
	private int money;
	@SerializedName("payOrderId")
	private String payOrderId;
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