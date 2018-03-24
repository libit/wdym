/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class ShopWechartInfo
{
	@SerializedName("wxPublicId")
	private String wxPublicId;
	@SerializedName("shopId")
	private String shopId;
	@SerializedName("originalId")
	private String originalId;
	@SerializedName("wechartId")
	private String wechartId;
	@SerializedName("wxName")
	private String wxName;
	@SerializedName("wxToken")
	private String wxToken;
	@SerializedName("appId")
	private String appId;
	@SerializedName("appSecret")
	private String appSecret;
	@SerializedName("encodingAeskey")
	private String encodingAeskey;
	@SerializedName("serverUrl")
	private String serverUrl;
	@SerializedName("email")
	private String email;
	@SerializedName("signData")
	private String signData;
	@SerializedName("useEncrypt")
	private byte useEncrypt;
	@SerializedName("wxType")
	private String wxType;
	@SerializedName("picId")
	private String picId;
	@SerializedName("status")
	private byte status;
	@SerializedName("remark")
	private String remark;
	@SerializedName("addDateLong")
	private long addDateLong;
	@SerializedName("updateDateLong")
	private long updateDateLong;
}