/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class WxWifiShopInfo
{
	@SerializedName("shopId")
	private String shopId;
	@SerializedName("wxPublicId")
	private String wxPublicId;
	@SerializedName("userId")
	private String userId;
	@SerializedName("shopName")
	private String shopName;
	@SerializedName("ssid")
	private String ssid;
	@SerializedName("password")
	private String password;
	@SerializedName("ssidList")
	private String ssidList;
	@SerializedName("ssidPasswordList")
	private String ssidPasswordList;
	@SerializedName("protocolType")
	private int protocolType;
	@SerializedName("apCount")
	private Integer apCount;
	@SerializedName("templateId")
	private Integer templateId;
	@SerializedName("homepageUrl")
	private String homepageUrl;
	@SerializedName("barType")
	private Integer barType;
	@SerializedName("finishpageUrl")
	private String finishpageUrl;
	@SerializedName("sid")
	private String sid;
	@SerializedName("poiId")
	private String poiId;
	@SerializedName("status")
	private byte status;
	@SerializedName("addDateLong")
	private long addDateLong;
	@SerializedName("updateDateLong")
	private long updateDateLong;
}