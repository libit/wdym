/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class WxMenuFuncInfo
{
	@SerializedName("menuFuncId")
	private String menuFuncId;
	@SerializedName("wxPublicId")
	private String wxPublicId;
	@SerializedName("menuKey")
	private String menuKey;
	@SerializedName("responseType")
	private String responseType;
	@SerializedName("responseId")
	private String responseId;
	@SerializedName("status")
	private byte status;
	@SerializedName("addDateLong")
	private long addDateLong;
	@SerializedName("updateDateLong")
	private long updateDateLong;
}