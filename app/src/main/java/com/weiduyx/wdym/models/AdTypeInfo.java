/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class AdTypeInfo
{
	@SerializedName("typeId")
	private String typeId;
	@SerializedName("name")
	private String name;
	@SerializedName("unitPrice")
	private int unitPrice;
	@SerializedName("shareUnitPrice")
	private int shareUnitPrice;
	@SerializedName("status")
	private byte status;
	@SerializedName("addDateLong")
	private long addDateLong;
	@SerializedName("updateDateLong")
	private long updateDateLong;
}