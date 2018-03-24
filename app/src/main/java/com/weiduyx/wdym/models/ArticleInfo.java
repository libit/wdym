/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class ArticleInfo
{
	@SerializedName("AId")
	private String AId;
	@SerializedName("userId")
	private String userId;
	@SerializedName("articleType")
	private String articleType;
	@SerializedName("title")
	private String title;
	@SerializedName("description")
	private String description;
	@SerializedName("logo")
	private String logo;
	@SerializedName("content")
	private String content;
	@SerializedName("readCount")
	private int readCount;
	@SerializedName("thumbUpCount")
	private int thumbUpCount;
	@SerializedName("status")
	private byte status;
	@SerializedName("addDateLong")
	private long addDateLong;
	@SerializedName("updateDateLong")
	private long updateDateLong;
	@SerializedName("articleSortInfo")
	private ArticleSortInfo articleSortInfo;
	@SerializedName("picInfo")
	private PicInfo picInfo;
}