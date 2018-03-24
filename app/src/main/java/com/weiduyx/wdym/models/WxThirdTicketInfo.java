/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class WxThirdTicketInfo
{
	@SerializedName("ticketId")
	private String ticketId;
	@SerializedName("appId")
	private String appId;
	@SerializedName("userId")
	private String userId;
	@SerializedName("wxOpenId")
	private String wxOpenId;
	@SerializedName("ticketType")
	private String ticketType;
	@SerializedName("useType")
	private String useType;
	@SerializedName("senceStr")
	private String senceStr;
	@SerializedName("ticket")
	private String ticket;
	@SerializedName("expireSeconds")
	private long expireSeconds;
	@SerializedName("url")
	private String url;
	@SerializedName("rawInfo")
	private String rawInfo;
	@SerializedName("status")
	private byte status;
	@SerializedName("addDateLong")
	private long addDateLong;
	@SerializedName("updateDateLong")
	private long updateDateLong;
}