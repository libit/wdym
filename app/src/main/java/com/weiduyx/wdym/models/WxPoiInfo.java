/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class WxPoiInfo
{
	@SerializedName("poiId")
	private int poiId;
	@SerializedName("userId")
	private String userId;
	@SerializedName("wxPublicId")
	private String wxPublicId;
	@SerializedName("sid")
	private String sid;
	@SerializedName("businessName")
	private String businessName;
	@SerializedName("branchName")
	private String branchName;
	@SerializedName("province")
	private String province;
	@SerializedName("city")
	private String city;
	@SerializedName("district")
	private String district;
	@SerializedName("address")
	private String address;
	@SerializedName("telephone")
	private String telephone;
	@SerializedName("categories")
	private String categories;
	@SerializedName("offsetType")
	private byte offsetType;
	@SerializedName("longitude")
	private String longitude;
	@SerializedName("latitude")
	private String latitude;
	@SerializedName("photoList")
	private String photoList;
	@SerializedName("recommend")
	private String recommend;
	@SerializedName("special")
	private String special;
	@SerializedName("introduction")
	private String introduction;
	@SerializedName("openTime")
	private String openTime;
	@SerializedName("avgPrice")
	private Integer avgPrice;
	@SerializedName("availableState")
	private byte availableState;
	@SerializedName("updateStatus")
	private byte updateStatus;
	@SerializedName("status")
	private byte status;
	@SerializedName("addDateLong")
	private long addDateLong;
	@SerializedName("updateDateLong")
	private long updateDateLong;
}