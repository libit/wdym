/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class AdminPayLogInfo
{
	@SerializedName("adminId")
	private String adminId;
	@SerializedName("payType")
	private String payType;
	@SerializedName("payId")
	private String payId;
	@SerializedName("money")
	private int money;
	@SerializedName("moneyUnit")
	private byte moneyUnit;
	@SerializedName("addDateLong")
	private long addDateLong;
}