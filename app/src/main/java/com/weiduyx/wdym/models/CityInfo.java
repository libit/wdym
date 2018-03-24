/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class CityInfo
{
	@SerializedName("cityId")
	private String cityId;
	@SerializedName("name")
	private String name;
	@SerializedName("provinceId")
	private String provinceId;
	@SerializedName("provinceInfo")
	private ProvinceInfo provinceInfo;

	public String getCityId()
	{
		return cityId;
	}

	public void setCityId(String cityId)
	{
		this.cityId = cityId;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getProvinceId()
	{
		return provinceId;
	}

	public void setProvinceId(String provinceId)
	{
		this.provinceId = provinceId;
	}

	public ProvinceInfo getProvinceInfo()
	{
		return provinceInfo;
	}

	public void setProvinceInfo(ProvinceInfo provinceInfo)
	{
		this.provinceInfo = provinceInfo;
	}
}