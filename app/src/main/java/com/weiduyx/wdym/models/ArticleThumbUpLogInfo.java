/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class ArticleThumbUpLogInfo
{
	@SerializedName("AId")
	private String AId;
	@SerializedName("userId")
	private String userId;
	@SerializedName("ip")
	private String ip;
	@SerializedName("status")
	private byte status;
	@SerializedName("addDateLong")
	private long addDateLong;
}