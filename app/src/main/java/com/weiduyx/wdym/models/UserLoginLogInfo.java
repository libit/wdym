/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class UserLoginLogInfo
{
	@SerializedName("userId")
	private String userId;
	@SerializedName("ip")
	private String ip;
	@SerializedName("location")
	private String location;
	@SerializedName("viewPage")
	private String viewPage;
	@SerializedName("clientInfo")
	private String clientInfo;
	@SerializedName("remark")
	private String remark;
	@SerializedName("addDateLong")
	private long addDateLong;
}