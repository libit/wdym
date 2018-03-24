/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class AdInfo
{
	@SerializedName("adId")
	private String adId;
	@SerializedName("typeId")
	private String typeId;
	@SerializedName("userId")
	private String userId;
	@SerializedName("name")
	private String name;
	@SerializedName("title")
	private String title;
	@SerializedName("content")
	private String content;
	@SerializedName("picUrl")
	private String picUrl;
	@SerializedName("industry1Id")
	private String industry1Id;
	@SerializedName("industry2Id")
	private String industry2Id;
	@SerializedName("displayCount")
	private int displayCount;@SerializedName("displayedAdCount")
	private int displayedAdCount;
	@SerializedName("adMinUnitPrice")
	private int adMinUnitPrice;
	@SerializedName("adMaxUnitPrice")
	private int adMaxUnitPrice;
	@SerializedName("authorizerAppId")
	private String authorizerAppId;
	@SerializedName("startDateLong")
	private long startDateLong;
	@SerializedName("userRange1")
	private String userRange1;
	@SerializedName("userRange2")
	private String userRange2;
	@SerializedName("ageRange1")
	private Integer ageRange1;
	@SerializedName("ageRange2")
	private Integer ageRange2;
	@SerializedName("location")
	private String location;
	@SerializedName("url")
	private String url;
	@SerializedName("urlContent")
	private String urlContent;
	@SerializedName("isCommon")
	private byte isCommon;
	@SerializedName("sortIndex")
	private int sortIndex;
	@SerializedName("status")
	private byte status;
	@SerializedName("addDateLong")
	private long addDateLong;
	@SerializedName("updateDateLong")
	private long updateDateLong;

	public String getAdId()
	{
		return adId;
	}

	public void setAdId(String adId)
	{
		this.adId = adId;
	}

	public String getTypeId()
	{
		return typeId;
	}

	public void setTypeId(String typeId)
	{
		this.typeId = typeId;
	}

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public String getPicUrl()
	{
		return picUrl;
	}

	public void setPicUrl(String picUrl)
	{
		this.picUrl = picUrl;
	}

	public String getIndustry1Id()
	{
		return industry1Id;
	}

	public void setIndustry1Id(String industry1Id)
	{
		this.industry1Id = industry1Id;
	}

	public String getIndustry2Id()
	{
		return industry2Id;
	}

	public void setIndustry2Id(String industry2Id)
	{
		this.industry2Id = industry2Id;
	}

	public int getDisplayCount()
	{
		return displayCount;
	}

	public void setDisplayCount(int displayCount)
	{
		this.displayCount = displayCount;
	}

	public int getDisplayedAdCount()
	{
		return displayedAdCount;
	}

	public void setDisplayedAdCount(int displayedAdCount)
	{
		this.displayedAdCount = displayedAdCount;
	}

	public int getAdMinUnitPrice()
	{
		return adMinUnitPrice;
	}

	public void setAdMinUnitPrice(int adMinUnitPrice)
	{
		this.adMinUnitPrice = adMinUnitPrice;
	}

	public int getAdMaxUnitPrice()
	{
		return adMaxUnitPrice;
	}

	public void setAdMaxUnitPrice(int adMaxUnitPrice)
	{
		this.adMaxUnitPrice = adMaxUnitPrice;
	}

	public String getAuthorizerAppId()
	{
		return authorizerAppId;
	}

	public void setAuthorizerAppId(String authorizerAppId)
	{
		this.authorizerAppId = authorizerAppId;
	}

	public long getStartDateLong()
	{
		return startDateLong;
	}

	public void setStartDateLong(long startDateLong)
	{
		this.startDateLong = startDateLong;
	}

	public String getUserRange1()
	{
		return userRange1;
	}

	public void setUserRange1(String userRange1)
	{
		this.userRange1 = userRange1;
	}

	public String getUserRange2()
	{
		return userRange2;
	}

	public void setUserRange2(String userRange2)
	{
		this.userRange2 = userRange2;
	}

	public Integer getAgeRange1()
	{
		return ageRange1;
	}

	public void setAgeRange1(Integer ageRange1)
	{
		this.ageRange1 = ageRange1;
	}

	public Integer getAgeRange2()
	{
		return ageRange2;
	}

	public void setAgeRange2(Integer ageRange2)
	{
		this.ageRange2 = ageRange2;
	}

	public String getLocation()
	{
		return location;
	}

	public void setLocation(String location)
	{
		this.location = location;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getUrlContent()
	{
		return urlContent;
	}

	public void setUrlContent(String urlContent)
	{
		this.urlContent = urlContent;
	}

	public byte getIsCommon()
	{
		return isCommon;
	}

	public void setIsCommon(byte isCommon)
	{
		this.isCommon = isCommon;
	}

	public int getSortIndex()
	{
		return sortIndex;
	}

	public void setSortIndex(int sortIndex)
	{
		this.sortIndex = sortIndex;
	}

	public byte getStatus()
	{
		return status;
	}

	public void setStatus(byte status)
	{
		this.status = status;
	}

	public long getAddDateLong()
	{
		return addDateLong;
	}

	public void setAddDateLong(long addDateLong)
	{
		this.addDateLong = addDateLong;
	}

	public long getUpdateDateLong()
	{
		return updateDateLong;
	}

	public void setUpdateDateLong(long updateDateLong)
	{
		this.updateDateLong = updateDateLong;
	}
}