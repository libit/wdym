/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class WxWifiDeviceInfo
{
	@SerializedName("deviceId")
	private String deviceId;
	@SerializedName("shopId")
	private String shopId;
	@SerializedName("wxPublicId")
	private String wxPublicId;
	@SerializedName("ssid")
	private String ssid;
	@SerializedName("bssid")
	private String bssid;
	@SerializedName("password")
	private String password;
	@SerializedName("qrUrl")
	private String qrUrl;
	@SerializedName("tempTimeLong")
	private int tempTimeLong;
	@SerializedName("unsubscribeTimeLong")
	private int unsubscribeTimeLong;
	@SerializedName("subscribeTimeLong")
	private int subscribeTimeLong;
	@SerializedName("price")
	private Integer price;
	@SerializedName("deviceType")
	private byte deviceType;
	@SerializedName("agentId")
	private String agentId;
	@SerializedName("salesmanId")
	private String salesmanId;
	@SerializedName("userId")
	private String userId;
	@SerializedName("provinceId")
	private String provinceId;
	@SerializedName("cityId")
	private String cityId;
	@SerializedName("districtId")
	private String districtId;
	@SerializedName("countryId")
	private String countryId;
	@SerializedName("address")
	private String address;
	@SerializedName("industry1Id")
	private String industry1Id;
	@SerializedName("industry2Id")
	private String industry2Id;
	@SerializedName("industry3Id")
	private String industry3Id;
	@SerializedName("deviceStatus")
	private byte deviceStatus;
	@SerializedName("isCommon")
	private byte isCommon;
	@SerializedName("remark")
	private String remark;
	@SerializedName("status")
	private byte status;
	@SerializedName("bindAgentDateLong")
	private Long bindAgentDateLong;
	@SerializedName("bindSalesmanDateLong")
	private Long bindSalesmanDateLong;
	@SerializedName("bindShopDateLong")
	private Long bindShopDateLong;
	@SerializedName("activeDateLong")
	private Long activeDateLong;
	@SerializedName("addDateLong")
	private long addDateLong;
	@SerializedName("updateDateLong")
	private long updateDateLong;
}