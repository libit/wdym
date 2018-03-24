/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class WxTemplateInfo
{
	@SerializedName("templateId")
	private String templateId;
	@SerializedName("wxPublicId")
	private String wxPublicId;
	@SerializedName("wxTemplateId")
	private String wxTemplateId;
	@SerializedName("title")
	private String title;
	@SerializedName("level1Industry")
	private String level1Industry;
	@SerializedName("level2Industry")
	private String level2Industry;
	@SerializedName("hasFirst")
	private byte hasFirst;
	@SerializedName("keywordCount")
	private byte keywordCount;
	@SerializedName("keywordReplace")
	private String keywordReplace;
	@SerializedName("hasRemark")
	private byte hasRemark;
	@SerializedName("status")
	private byte status;
	@SerializedName("addDateLong")
	private long addDateLong;
	@SerializedName("updateDateLong")
	private long updateDateLong;
}