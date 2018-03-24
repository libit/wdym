/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class WifiDeviceTypeInfo
{
	@SerializedName("typeId")
	private int typeId;
	@SerializedName("name")
	private String name;
	@SerializedName("price")
	private Integer price;
	@SerializedName("picUrl")
	private String picUrl;
	@SerializedName("status")
	private byte status;
	@SerializedName("addDateLong")
	private long addDateLong;
	@SerializedName("updateDateLong")
	private long updateDateLong;
}