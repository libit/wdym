/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class ApplyUserAgentInfo
{
	@SerializedName("applyId")
	private String applyId;
	@SerializedName("userId")
	private String userId;
	@SerializedName("applyUserType")
	private byte applyUserType;
	@SerializedName("provinceId")
	private String provinceId;
	@SerializedName("cityId")
	private String cityId;
	@SerializedName("districtId")
	private String districtId;
	@SerializedName("countryId")
	private String countryId;
	@SerializedName("money")
	private int money;
	@SerializedName("payOrderId")
	private String payOrderId;
	@SerializedName("adminId")
	private String adminId;
	@SerializedName("remark")
	private String remark;
	@SerializedName("verifyDateLong")
	private Long verifyDateLong;
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