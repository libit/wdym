/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class WxShareGroupInfo
{
	@SerializedName("shareId")
	private String shareId;
	@SerializedName("title")
	private String title;
	@SerializedName("shareDesc")
	private String shareDesc;
	@SerializedName("clickUrl")
	private String clickUrl;
	@SerializedName("imgUrl")
	private String imgUrl;
	@SerializedName("shareType")
	private String shareType;
	@SerializedName("dataUrl")
	private String dataUrl;
	@SerializedName("point")
	private int point;
	@SerializedName("content")
	private String content;
	@SerializedName("status")
	private byte status;
	@SerializedName("addDateLong")
	private long addDateLong;
	@SerializedName("updateDateLong")
	private long updateDateLong;
}