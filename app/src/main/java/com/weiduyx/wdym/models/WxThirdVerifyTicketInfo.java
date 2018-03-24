/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class WxThirdVerifyTicketInfo
{
	@SerializedName("componentAppId")
	private String componentAppId;
	@SerializedName("infoType")
	private String infoType;
	@SerializedName("componentVerifyTicket")
	private String componentVerifyTicket;
	@SerializedName("createTime")
	private long createTime;
	@SerializedName("status")
	private byte status;
	@SerializedName("addDateLong")
	private long addDateLong;
	@SerializedName("updateDateLong")
	private long updateDateLong;
}