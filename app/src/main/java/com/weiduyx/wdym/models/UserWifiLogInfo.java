/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class UserWifiLogInfo
{
	@SerializedName("userId")
	private String userId;
	@SerializedName("userFrom")
	private String userFrom;
	@SerializedName("gwId")
	private String gwId;
	@SerializedName("gwAddress")
	private String gwAddress;
	@SerializedName("gwPort")
	private int gwPort;
	@SerializedName("ip")
	private String ip;
	@SerializedName("mac")
	private String mac;
	@SerializedName("authDateLong")
	private long authDateLong;
	@SerializedName("endDateLong")
	private Long endDateLong;
	@SerializedName("totalTime")
	private Integer totalTime;
	@SerializedName("adClickCount")
	private int adClickCount;
	@SerializedName("addDateLong")
	private long addDateLong;
}