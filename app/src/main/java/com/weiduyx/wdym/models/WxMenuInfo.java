/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class WxMenuInfo
{
	@SerializedName("menuId")
	private String menuId;
	@SerializedName("wxPublicId")
	private String wxPublicId;
	@SerializedName("name")
	private String name;
	@SerializedName("menuType")
	private String menuType;
	@SerializedName("menuKey")
	private String menuKey;
	@SerializedName("content")
	private String content;
	@SerializedName("recordId")
	private int recordId;
	@SerializedName("lastMenuId")
	private String lastMenuId;
	@SerializedName("status")
	private byte status;
	@SerializedName("addDateLong")
	private long addDateLong;
	@SerializedName("updateDateLong")
	private long updateDateLong;
}