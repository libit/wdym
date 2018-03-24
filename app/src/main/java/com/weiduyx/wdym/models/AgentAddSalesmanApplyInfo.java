/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class AgentAddSalesmanApplyInfo
{
	@SerializedName("applyId")
	private String applyId;
	@SerializedName("agentId")
	private String agentId;
	@SerializedName("salesmanId")
	private String salesmanId;
	@SerializedName("status")
	private byte status;
	@SerializedName("addDateLong")
	private long addDateLong;
	@SerializedName("updateDateLong")
	private long updateDateLong;
	@SerializedName("agentInfo")
	private UserInfo agentInfo;
	@SerializedName("salesmanInfo")
	private UserInfo salesmanInfo;
}