/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class WxMerchantApplyInfo
{
	@SerializedName("applyId")
	private String applyId;
	@SerializedName("wxPublicId")
	private String wxPublicId;
	@SerializedName("userId")
	private String userId;
	@SerializedName("firstCatid")
	private int firstCatid;
	@SerializedName("secondCatid")
	private int secondCatid;
	@SerializedName("qualificationList")
	private String qualificationList;
	@SerializedName("headimgMediaid")
	private String headimgMediaid;
	@SerializedName("nickname")
	private String nickname;
	@SerializedName("intro")
	private String intro;
	@SerializedName("orgCode")
	private String orgCode;
	@SerializedName("otherFiles")
	private String otherFiles;
	@SerializedName("auditId")
	private String auditId;
	@SerializedName("auditStatus")
	private String auditStatus;
	@SerializedName("reason")
	private String reason;
	@SerializedName("status")
	private byte status;
	@SerializedName("addDateLong")
	private long addDateLong;
	@SerializedName("updateDateLong")
	private long updateDateLong;
}