/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class NewsInfo
{
	@SerializedName("newsId")
	private String newsId;
	@SerializedName("sortId")
	private String sortId;
	@SerializedName("newsType")
	private byte newsType;
	@SerializedName("title")
	private String title;
	@SerializedName("author")
	private String author;
	@SerializedName("description")
	private String description;
	@SerializedName("content")
	private String content;
	@SerializedName("status")
	private byte status;
	@SerializedName("remark")
	private String remark;
	@SerializedName("valideDateLong")
	private Long valideDateLong;
	@SerializedName("addDateLong")
	private long addDateLong;
	@SerializedName("updateDateLong")
	private long updateDateLong;
	@SerializedName("newsSortInfo")
	private NewsSortInfo newsSortInfo;

	public String getNewsId()
	{
		return newsId;
	}

	public void setNewsId(String newsId)
	{
		this.newsId = newsId;
	}

	public String getSortId()
	{
		return sortId;
	}

	public void setSortId(String sortId)
	{
		this.sortId = sortId;
	}

	public byte getNewsType()
	{
		return newsType;
	}

	public void setNewsType(byte newsType)
	{
		this.newsType = newsType;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getAuthor()
	{
		return author;
	}

	public void setAuthor(String author)
	{
		this.author = author;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public byte getStatus()
	{
		return status;
	}

	public void setStatus(byte status)
	{
		this.status = status;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	public Long getValideDateLong()
	{
		return valideDateLong;
	}

	public void setValideDateLong(Long valideDateLong)
	{
		this.valideDateLong = valideDateLong;
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

	public NewsSortInfo getNewsSortInfo()
	{
		return newsSortInfo;
	}

	public void setNewsSortInfo(NewsSortInfo newsSortInfo)
	{
		this.newsSortInfo = newsSortInfo;
	}
}