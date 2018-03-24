/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class TxMapConfigInfo
{
	@SerializedName("configId")
	private String configId;
	@SerializedName("appName")
	private String appName;
	@SerializedName("appType")
	private String appType;
	@SerializedName("appKey")
	private String appKey;
	@SerializedName("appDevName")
	private String appDevName;
	@SerializedName("status")
	private byte status;
	@SerializedName("addDateLong")
	private long addDateLong;
	@SerializedName("updateDateLong")
	private long updateDateLong;
}