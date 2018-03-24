/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class WxMapPoiApplyInfo
{
	@SerializedName("applyId")
	private String applyId;
	@SerializedName("wxPublicId")
	private String wxPublicId;
	@SerializedName("userId")
	private String userId;
	@SerializedName("name")
	private String name;
	@SerializedName("longitude")
	private String longitude;
	@SerializedName("latitude")
	private String latitude;
	@SerializedName("province")
	private String province;
	@SerializedName("city")
	private String city;
	@SerializedName("district")
	private String district;
	@SerializedName("address")
	private String address;
	@SerializedName("firstCatid")
	private int firstCatid;
	@SerializedName("secondCatid")
	private int secondCatid;
	@SerializedName("telephone")
	private String telephone;
	@SerializedName("photo")
	private String photo;
	@SerializedName("license")
	private String license;
	@SerializedName("introduct")
	private String introduct;
	@SerializedName("districtid")
	private String districtid;
	@SerializedName("poiId")
	private String poiId;
	@SerializedName("baseId")
	private String baseId;
	@SerializedName("richId")
	private String richId;
	@SerializedName("auditId")
	private String auditId;
	@SerializedName("auditStatus")
	private String auditStatus;
	@SerializedName("mapPoiId")
	private String mapPoiId;
	@SerializedName("shRemark")
	private String shRemark;
	@SerializedName("status")
	private byte status;
	@SerializedName("addDateLong")
	private long addDateLong;
	@SerializedName("updateDateLong")
	private long updateDateLong;
}