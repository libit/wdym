/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class AdAddInfo
{
	@SerializedName("adId")
	private String adId;
	@SerializedName("addDisplayCount")
	private int addDisplayCount;
	@SerializedName("addDisplayShareCount")
	private int addDisplayShareCount;
	@SerializedName("addAdMinUnitPrice")
	private int addAdMinUnitPrice;
	@SerializedName("addAdMaxUnitPrice")
	private int addAdMaxUnitPrice;
	@SerializedName("addAdTotalPrice")
	private int addAdTotalPrice;
	@SerializedName("addShareMinUnitPrice")
	private int addShareMinUnitPrice;
	@SerializedName("addShareMaxUnitPrice")
	private int addShareMaxUnitPrice;
	@SerializedName("addShareTotalPrice")
	private int addShareTotalPrice;
	@SerializedName("addTotalPrice")
	private int addTotalPrice;
	@SerializedName("displayCount")
	private int displayCount;
	@SerializedName("displayShareCount")
	private int displayShareCount;
	@SerializedName("totalPrice")
	private int totalPrice;
	@SerializedName("adMinUnitPrice")
	private int adMinUnitPrice;
	@SerializedName("adMaxUnitPrice")
	private int adMaxUnitPrice;
	@SerializedName("adTotalPrice")
	private int adTotalPrice;
	@SerializedName("adSendMoney")
	private int adSendMoney;
	@SerializedName("shareMinUnitPrice")
	private int shareMinUnitPrice;
	@SerializedName("shareMaxUnitPrice")
	private int shareMaxUnitPrice;
	@SerializedName("shareTotalPrice")
	private int shareTotalPrice;
	@SerializedName("shareSendMoney")
	private int shareSendMoney;
	@SerializedName("displayedAdCount")
	private int displayedAdCount;
	@SerializedName("displayedShareCount")
	private int displayedShareCount;
	@SerializedName("payOrderId")
	private String payOrderId;
	@SerializedName("adminId")
	private String adminId;
	@SerializedName("remark")
	private String remark;
	@SerializedName("status")
	private byte status;
	@SerializedName("addDateLong")
	private long addDateLong;
	@SerializedName("updateDateLong")
	private long updateDateLong;
}