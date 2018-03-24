/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class WxUploadMediaInfo
{
	@SerializedName("uploadId")
	private String uploadId;
	@SerializedName("wxPublicId")
	private String wxPublicId;
	@SerializedName("type")
	private String type;
	@SerializedName("mediaId")
	private String mediaId;
	@SerializedName("createdAt")
	private String createdAt;
	@SerializedName("status")
	private byte status;
	@SerializedName("addDateLong")
	private long addDateLong;
}