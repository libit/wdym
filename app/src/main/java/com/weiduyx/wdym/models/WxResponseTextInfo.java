/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class WxResponseTextInfo
{
	@SerializedName("responseId")
	private String responseId;
	@SerializedName("wxPublicId")
	private String wxPublicId;
	@SerializedName("content")
	private String content;
	@SerializedName("status")
	private byte status;
	@SerializedName("addDateLong")
	private long addDateLong;
	@SerializedName("updateDateLong")
	private long updateDateLong;
}