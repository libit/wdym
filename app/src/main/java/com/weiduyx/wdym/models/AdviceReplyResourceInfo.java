/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class AdviceReplyResourceInfo
{
	@SerializedName("resourceId")
	private String resourceId;
	@SerializedName("replyId")
	private String replyId;
	@SerializedName("resourceType")
	private String resourceType;
	@SerializedName("content")
	private String content;
	@SerializedName("status")
	private byte status;
	@SerializedName("addDateLong")
	private long addDateLong;
}