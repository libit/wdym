/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class UserAgentInfo
{
	@SerializedName("userId")
	private String userId;
	@SerializedName("userType")
	private byte userType;
	@SerializedName("provinceId")
	private String provinceId;
	@SerializedName("cityId")
	private String cityId;
	@SerializedName("districtId")
	private String districtId;
	@SerializedName("countryId")
	private String countryId;
	@SerializedName("status")
	private byte status;
	@SerializedName("addDateLong")
	private long addDateLong;
	@SerializedName("updateDateLong")
	private long updateDateLong;
	@SerializedName("provinceInfo")
	private ProvinceInfo provinceInfo;
	@SerializedName("cityInfo")
	private CityInfo cityInfo;
	@SerializedName("districtInfo")
	private DistrictInfo districtInfo;
	@SerializedName("countryInfo")
	private CountryInfo countryInfo;
}