//
// MovieInfo.h
//
// 作者：libit 创建于 2018年3月1日.
// Copyright © 2018年 LR. All rights reserved.
//
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class MovieInfo
{
	@SerializedName("movieId")
	private String movieId;
	@SerializedName("name")
	private String name;
	@SerializedName("author")
	private String author;
	@SerializedName("shortContent")
	private String shortContent;
	@SerializedName("picUrl")
	private String picUrl;
	@SerializedName("url")
	private String url;
	@SerializedName("score")
	private int score;
	@SerializedName("watchCount")
	private int watchCount;
	@SerializedName("sortIndex")
	private int sortIndex;
	@SerializedName("status")
	private byte status;
	@SerializedName("addDateLong")
	private long addDateLong;
	@SerializedName("updateDateLong")
	private long updateDateLong;

	public String getMovieId()
	{
		return movieId;
	}

	public void setMovieId(String movieId)
	{
		this.movieId = movieId;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getAuthor()
	{
		return author;
	}

	public void setAuthor(String author)
	{
		this.author = author;
	}

	public String getShortContent()
	{
		return shortContent;
	}

	public void setShortContent(String shortContent)
	{
		this.shortContent = shortContent;
	}

	public String getPicUrl()
	{
		return picUrl;
	}

	public void setPicUrl(String picUrl)
	{
		this.picUrl = picUrl;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public int getScore()
	{
		return score;
	}

	public void setScore(int score)
	{
		this.score = score;
	}

	public int getWatchCount()
	{
		return watchCount;
	}

	public void setWatchCount(int watchCount)
	{
		this.watchCount = watchCount;
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