/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class TxMapAreaInfo
{
	@SerializedName("areaId")
	private String areaId;
	@SerializedName("name")
	private String name;
	@SerializedName("fullName")
	private String fullName;
	@SerializedName("pinyin")
	private String pinyin;
	@SerializedName("latitude")
	private String latitude;
	@SerializedName("longitude")
	private String longitude;
	@SerializedName("level")
	private int level;
	@SerializedName("father")
	private String father;
	@SerializedName("cidx")
	private String cidx;
	@SerializedName("status")
	private byte status;
	@SerializedName("addDateLong")
	private long addDateLong;
	@SerializedName("updateDateLong")
	private long updateDateLong;
}