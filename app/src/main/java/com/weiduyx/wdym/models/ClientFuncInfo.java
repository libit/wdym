/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class ClientFuncInfo
{
	@SerializedName("funcId")
	private String funcId;
	@SerializedName("position")
	private String position;
	@SerializedName("picId")
	private String picId;
	@SerializedName("name")
	private String name;
	@SerializedName("content")
	private String content;
	@SerializedName("addDateLong")
	private long addDateLong;
}