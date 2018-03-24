/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class WxPoiStoreInfo
{
	@SerializedName("storeId")
	private String storeId;
	@SerializedName("wxPublicId")
	private String wxPublicId;
	@SerializedName("userId")
	private String userId;
	@SerializedName("businessName")
	private String businessName;
	@SerializedName("province")
	private String province;
	@SerializedName("city")
	private String city;
	@SerializedName("address")
	private String address;
	@SerializedName("longitude")
	private String longitude;
	@SerializedName("latitude")
	private String latitude;
	@SerializedName("picList")
	private String picList;
	@SerializedName("telephone")
	private String telephone;
	@SerializedName("openTime")
	private String openTime;
	@SerializedName("district")
	private String district;
	@SerializedName("qualificationName")
	private String qualificationName;
	@SerializedName("qualificationNum")
	private String qualificationNum;
	@SerializedName("qualificationList")
	private String qualificationList;
	@SerializedName("poiId")
	private String poiId;
	@SerializedName("mapPoiId")
	private String mapPoiId;
	@SerializedName("auditId")
	private String auditId;
	@SerializedName("auditStatus")
	private String auditStatus;
	@SerializedName("reason")
	private String reason;
	@SerializedName("isUpgrade")
	private String isUpgrade;
	@SerializedName("status")
	private byte status;
	@SerializedName("addDateLong")
	private long addDateLong;
	@SerializedName("updateDateLong")
	private long updateDateLong;
}