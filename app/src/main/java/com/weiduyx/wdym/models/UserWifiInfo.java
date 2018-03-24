/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class UserWifiInfo
{
	@SerializedName("wifiId")
	private String wifiId;
	@SerializedName("userFrom")
	private String userFrom;
	@SerializedName("userId")
	private String userId;
	@SerializedName("wxOpenId")
	private String wxOpenId;
	@SerializedName("tid")
	private String tid;
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
	@SerializedName("token")
	private String token;
	@SerializedName("authDateLong")
	private long authDateLong;
	@SerializedName("adClickCount")
	private int adClickCount;
	@SerializedName("verifyCount")
	private int verifyCount;
	@SerializedName("status")
	private byte status;
	@SerializedName("addDateLong")
	private long addDateLong;
	@SerializedName("updateDateLong")
	private long updateDateLong;
}