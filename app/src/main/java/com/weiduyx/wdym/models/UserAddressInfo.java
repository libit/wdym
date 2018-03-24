/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class UserAddressInfo
{
	@SerializedName("addressId")
	private String addressId;
	@SerializedName("userId")
	private String userId;
	@SerializedName("name")
	private String name;
	@SerializedName("number")
	private String number;
	@SerializedName("address")
	private String address;
	@SerializedName("isDefault")
	private byte isDefault;
	@SerializedName("status")
	private byte status;
	@SerializedName("addDateLong")
	private long addDateLong;
	@SerializedName("updateDateLong")
	private long updateDateLong;
}