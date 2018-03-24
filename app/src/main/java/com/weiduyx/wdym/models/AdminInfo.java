/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class AdminInfo
{
	@SerializedName("userId")
	private String userId;
	@SerializedName("password")
	private String password;
	@SerializedName("userLevel")
	private byte userLevel;
	@SerializedName("name")
	private String name;
	@SerializedName("picId")
	private String picId;
	@SerializedName("lastLoginTime")
	private String lastLoginTime;
	@SerializedName("balance")
	private int balance;
	@SerializedName("point")
	private Integer point;
	@SerializedName("status")
	private byte status;
	@SerializedName("addDateLong")
	private long addDateLong;
	@SerializedName("updateDateLong")
	private long updateDateLong;
}