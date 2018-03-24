/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class ConfigInfo
{
	@SerializedName("configId")
	private String configId;
	@SerializedName("configName")
	private String configName;
	@SerializedName("content")
	private String content;
	@SerializedName("status")
	private byte status;
	@SerializedName("remark")
	private String remark;
}