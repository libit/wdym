/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class WxMediaInfo
{
	@SerializedName("MId")
	private String MId;
	@SerializedName("wxPublicId")
	private String wxPublicId;
	@SerializedName("mediaId")
	private String mediaId;
	@SerializedName("mediaType")
	private String mediaType;
	@SerializedName("sessionType")
	private String sessionType;
	@SerializedName("url")
	private String url;
	@SerializedName("updateTime")
	private Long updateTime;
	@SerializedName("picId")
	private String picId;
	@SerializedName("remark")
	private String remark;
	@SerializedName("userId")
	private String userId;
	@SerializedName("status")
	private byte status;
	@SerializedName("addDateLong")
	private long addDateLong;
	@SerializedName("updateDateLong")
	private long updateDateLong;
}