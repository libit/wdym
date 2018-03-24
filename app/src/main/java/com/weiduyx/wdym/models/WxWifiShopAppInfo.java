/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class WxWifiShopAppInfo
{
	@SerializedName("ssid")
	private String ssid;
	@SerializedName("wxPublicId")
	private String wxPublicId;
	@SerializedName("shopId")
	private String shopId;
	@SerializedName("appId")
	private String appId;
	@SerializedName("appSecret")
	private String appSecret;
	@SerializedName("qrUrl")
	private String qrUrl;
	@SerializedName("qrUrl2")
	private String qrUrl2;
	@SerializedName("qrcodeUrl")
	private String qrcodeUrl;
	@SerializedName("qrcodeUrl2")
	private String qrcodeUrl2;
	@SerializedName("deviceCount")
	private int deviceCount;
	@SerializedName("status")
	private byte status;
	@SerializedName("addDateLong")
	private long addDateLong;
	@SerializedName("updateDateLong")
	private long updateDateLong;
}