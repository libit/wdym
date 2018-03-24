/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class WifiAuthLogInfo
{
	@SerializedName("stage")
	private String stage;
	@SerializedName("ip")
	private String ip;
	@SerializedName("mac")
	private String mac;
	@SerializedName("token")
	private String token;
	@SerializedName("incoming")
	private String incoming;
	@SerializedName("outgoing")
	private String outgoing;
	@SerializedName("addDateLong")
	private long addDateLong;
}