/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class SmsCodeConfigInfo
{
	@SerializedName("configId")
	private String configId;
	@SerializedName("name")
	private String name;
	@SerializedName("platform")
	private String platform;
	@SerializedName("url")
	private String url;
	@SerializedName("appKey")
	private String appKey;
	@SerializedName("appSecret")
	private String appSecret;
	@SerializedName("smsType")
	private String smsType;
	@SerializedName("signName")
	private String signName;
	@SerializedName("smsParams")
	private String smsParams;
	@SerializedName("smsTemplateCode")
	private String smsTemplateCode;
	@SerializedName("extend")
	private String extend;
	@SerializedName("sortIndex")
	private int sortIndex;
	@SerializedName("validateSeconds")
	private int validateSeconds;
	@SerializedName("status")
	private byte status;
	@SerializedName("remark")
	private String remark;
	@SerializedName("addDateLong")
	private long addDateLong;
	@SerializedName("updateDateLong")
	private long updateDateLong;
}