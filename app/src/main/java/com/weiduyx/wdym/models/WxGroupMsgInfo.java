/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class WxGroupMsgInfo
{
	@SerializedName("msgId")
	private String msgId;
	@SerializedName("wxPublicId")
	private String wxPublicId;
	@SerializedName("sendType")
	private String sendType;
	@SerializedName("sendTypeContent")
	private String sendTypeContent;
	@SerializedName("msgType")
	private String msgType;
	@SerializedName("msgContent")
	private String msgContent;
	@SerializedName("receiveMsgId")
	private String receiveMsgId;
	@SerializedName("msgDataId")
	private String msgDataId;
	@SerializedName("status")
	private byte status;
	@SerializedName("addDateLong")
	private long addDateLong;
	@SerializedName("updateDateLong")
	private long updateDateLong;
}