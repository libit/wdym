/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class ClientBannerInfo
{
	@SerializedName("bannerId")
	private String bannerId;
	@SerializedName("bannerPosition")
	private String bannerPosition;
	@SerializedName("bannerType")
	private String bannerType;
	@SerializedName("picId")
	private String picId;
	@SerializedName("name")
	private String name;
	@SerializedName("content")
	private String content;
	@SerializedName("sortIndex")
	private int sortIndex;
	@SerializedName("status")
	private byte status;
	@SerializedName("addDateLong")
	private long addDateLong;
	@SerializedName("updateDateLong")
	private long updateDateLong;

	public String getBannerId()
	{
		return bannerId;
	}

	public void setBannerId(String bannerId)
	{
		this.bannerId = bannerId;
	}

	public String getBannerPosition()
	{
		return bannerPosition;
	}

	public void setBannerPosition(String bannerPosition)
	{
		this.bannerPosition = bannerPosition;
	}

	public String getBannerType()
	{
		return bannerType;
	}

	public void setBannerType(String bannerType)
	{
		this.bannerType = bannerType;
	}

	public String getPicId()
	{
		return picId;
	}

	public void setPicId(String picId)
	{
		this.picId = picId;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
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