/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class WifiLoginLogInfo
{
	@SerializedName("gwId")
	private String gwId;
	@SerializedName("gwAddress")
	private String gwAddress;
	@SerializedName("gwPort")
	private int gwPort;
	@SerializedName("mac")
	private String mac;
	@SerializedName("url")
	private String url;
	@SerializedName("addDateLong")
	private long addDateLong;
}