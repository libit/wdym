/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class WxUserDialogInfo
{
	@SerializedName("wxPublicId")
	private String wxPublicId;
	@SerializedName("wxOpenId")
	private String wxOpenId;
	@SerializedName("sendMsgType")
	private String sendMsgType;
	@SerializedName("sendMsg")
	private String sendMsg;
	@SerializedName("receiveMsgType")
	private String receiveMsgType;
	@SerializedName("receiveMsg")
	private String receiveMsg;
	@SerializedName("addDateLong")
	private long addDateLong;
	@SerializedName("updateDateLong")
	private long updateDateLong;
}