/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class DistrictInfo
{
	@SerializedName("districtId")
	private String districtId;
	@SerializedName("name")
	private String name;
	@SerializedName("provinceId")
	private String provinceId;
	@SerializedName("cityId")
	private String cityId;
	@SerializedName("provinceInfo")
	private ProvinceInfo provinceInfo;
	@SerializedName("cityInfo")
	private CityInfo cityInfo;
}