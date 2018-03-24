/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class WifiPingLogInfo
{
	@SerializedName("gwId")
	private String gwId;
	@SerializedName("sysUptime")
	private int sysUptime;
	@SerializedName("sysMemfree")
	private int sysMemfree;
	@SerializedName("sysLoad")
	private String sysLoad;
	@SerializedName("wifidogUptime")
	private int wifidogUptime;
	@SerializedName("addDateLong")
	private long addDateLong;
}