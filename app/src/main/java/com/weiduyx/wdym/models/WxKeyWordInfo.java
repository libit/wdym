/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class WxKeyWordInfo
{
	@SerializedName("keyId")
	private String keyId;
	@SerializedName("wxPublicId")
	private String wxPublicId;
	@SerializedName("keyWord")
	private String keyWord;
	@SerializedName("responseType")
	private String responseType;
	@SerializedName("responseId")
	private String responseId;
	@SerializedName("status")
	private byte status;
	@SerializedName("addDateLong")
	private long addDateLong;
	@SerializedName("updateDateLong")
	private long updateDateLong;
}