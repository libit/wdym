/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class PicInfo
{
	@SerializedName("picId")
	private String picId;
	@SerializedName("sortId")
	private String sortId;
	@SerializedName("picUrl")
	private String picUrl;
	@SerializedName("width")
	private int width;
	@SerializedName("height")
	private int height;
	@SerializedName("picSizeInfo")
	private String picSizeInfo;
	@SerializedName("status")
	private byte status;

	public String getPicId()
	{
		return picId;
	}

	public void setPicId(String picId)
	{
		this.picId = picId;
	}

	public String getSortId()
	{
		return sortId;
	}

	public void setSortId(String sortId)
	{
		this.sortId = sortId;
	}

	public String getPicUrl()
	{
		return picUrl;
	}

	public void setPicUrl(String picUrl)
	{
		this.picUrl = picUrl;
	}

	public int getWidth()
	{
		return width;
	}

	public void setWidth(int width)
	{
		this.width = width;
	}

	public int getHeight()
	{
		return height;
	}

	public void setHeight(int height)
	{
		this.height = height;
	}

	public String getPicSizeInfo()
	{
		return picSizeInfo;
	}

	public void setPicSizeInfo(String picSizeInfo)
	{
		this.picSizeInfo = picSizeInfo;
	}

	public byte getStatus()
	{
		return status;
	}

	public void setStatus(byte status)
	{
		this.status = status;
	}
}