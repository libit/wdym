/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class WxKefuInfo
{
	@SerializedName("accountId")
	private String accountId;
	@SerializedName("wxPublicId")
	private String wxPublicId;
	@SerializedName("nickname")
	private String nickname;
	@SerializedName("password")
	private String password;
	@SerializedName("kefuId")
	private String kefuId;
	@SerializedName("headImg")
	private String headImg;
	@SerializedName("status")
	private byte status;
	@SerializedName("addDateLong")
	private long addDateLong;
	@SerializedName("updateDateLong")
	private long updateDateLong;
}