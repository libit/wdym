/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class WxThirdWechartInfo
{
	@SerializedName("authorizerAppId")
	private String authorizerAppId;
	@SerializedName("userId")
	private String userId;
	@SerializedName("originalId")
	private String originalId;
	@SerializedName("nickName")
	private String nickName;
	@SerializedName("headImg")
	private String headImg;
	@SerializedName("serviceTypeInfo")
	private Byte serviceTypeInfo;
	@SerializedName("verifyTypeInfo")
	private Byte verifyTypeInfo;
	@SerializedName("principalName")
	private String principalName;
	@SerializedName("businessInfo")
	private String businessInfo;
	@SerializedName("alias")
	private String alias;
	@SerializedName("qrcodeUrl")
	private String qrcodeUrl;
	@SerializedName("qrcodePicId")
	private String qrcodePicId;
	@SerializedName("funcInfo")
	private String funcInfo;
	@SerializedName("status")
	private byte status;
	@SerializedName("addDateLong")
	private long addDateLong;
	@SerializedName("updateDateLong")
	private long updateDateLong;
}