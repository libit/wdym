/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class WxMerchantSortInfo
{
	@SerializedName("sortId")
	private int sortId;
	@SerializedName("name")
	private String name;
	@SerializedName("level")
	private int level;
	@SerializedName("father")
	private Integer father;
	@SerializedName("children")
	private String children;
	@SerializedName("sensitiveType")
	private Integer sensitiveType;
	@SerializedName("scene")
	private Integer scene;
	@SerializedName("qualify")
	private String qualify;
	@SerializedName("status")
	private byte status;
	@SerializedName("addDateLong")
	private long addDateLong;
	@SerializedName("updateDateLong")
	private long updateDateLong;
}