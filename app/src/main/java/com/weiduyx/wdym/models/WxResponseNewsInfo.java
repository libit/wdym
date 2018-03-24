/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class WxResponseNewsInfo
{
	@SerializedName("responseId")
	private String responseId;
	@SerializedName("wxPublicId")
	private String wxPublicId;
	@SerializedName("title")
	private String title;
	@SerializedName("content")
	private String content;
	@SerializedName("picId")
	private String picId;
	@SerializedName("multiNewsPicId")
	private String multiNewsPicId;
	@SerializedName("clickUrl")
	private String clickUrl;
	@SerializedName("status")
	private byte status;
	@SerializedName("addDateLong")
	private long addDateLong;
	@SerializedName("updateDateLong")
	private long updateDateLong;
}