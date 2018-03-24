/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class WxAppConfigInfo
{
	@SerializedName("appId")
	private String appId;
	@SerializedName("appSecret")
	private String appSecret;
	@SerializedName("loginId")
	private String loginId;
	@SerializedName("name")
	private String name;
	@SerializedName("description")
	private String description;
	@SerializedName("status")
	private byte status;
	@SerializedName("addDateLong")
	private long addDateLong;
	@SerializedName("updateDateLong")
	private long updateDateLong;
}