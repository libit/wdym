/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class WxTokenInfo
{
	@SerializedName("wxPublicId")
	private String wxPublicId;
	@SerializedName("accessToken")
	private String accessToken;
	@SerializedName("expires")
	private int expires;
	@SerializedName("scope")
	private String scope;
	@SerializedName("status")
	private byte status;
	@SerializedName("addDateLong")
	private long addDateLong;
	@SerializedName("updateDateLong")
	private long updateDateLong;
}