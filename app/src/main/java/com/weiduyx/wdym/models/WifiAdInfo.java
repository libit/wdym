/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class WifiAdInfo
{
	@SerializedName("adId")
	private String adId;
	@SerializedName("userId")
	private String userId;
	@SerializedName("title")
	private String title;
	@SerializedName("content")
	private String content;
	@SerializedName("picUrl")
	private String picUrl;
	@SerializedName("industry1Id")
	private String industry1Id;
	@SerializedName("industry2Id")
	private String industry2Id;
	@SerializedName("displayCount")
	private int displayCount;
	@SerializedName("typeId")
	private String typeId;
	@SerializedName("unitPrice")
	private int unitPrice;
	@SerializedName("totalPrice")
	private int totalPrice;
	@SerializedName("userRange1")
	private String userRange1;
	@SerializedName("authorizerAppId")
	private String authorizerAppId;
	@SerializedName("location")
	private String location;
	@SerializedName("startDateLong")
	private long startDateLong;
	@SerializedName("number")
	private String number;
	@SerializedName("url")
	private String url;
	@SerializedName("urlContent")
	private String urlContent;
	@SerializedName("referrerId")
	private String referrerId;
	@SerializedName("displayedCount")
	private int displayedCount;
	@SerializedName("isCommon")
	private byte isCommon;
	@SerializedName("sortIndex")
	private int sortIndex;
	@SerializedName("payOrderId")
	private String payOrderId;
	@SerializedName("adminId")
	private String adminId;
	@SerializedName("remark")
	private String remark;
	@SerializedName("verifyDateLong")
	private Long verifyDateLong;
	@SerializedName("showStatus")
	private byte showStatus;
	@SerializedName("status")
	private byte status;
	@SerializedName("addDateLong")
	private long addDateLong;
	@SerializedName("updateDateLong")
	private long updateDateLong;
}