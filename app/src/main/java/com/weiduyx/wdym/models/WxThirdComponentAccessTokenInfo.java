/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class WxThirdComponentAccessTokenInfo
{
	@SerializedName("componentAppId")
	private String componentAppId;
	@SerializedName("componentAccessToken")
	private String componentAccessToken;
	@SerializedName("expires")
	private int expires;
	@SerializedName("status")
	private byte status;
	@SerializedName("addDateLong")
	private long addDateLong;
	@SerializedName("updateDateLong")
	private long updateDateLong;
}