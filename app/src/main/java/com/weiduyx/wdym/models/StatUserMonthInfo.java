/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class StatUserMonthInfo
{
	@SerializedName("userId")
	private String userId;
	@SerializedName("dateMonth")
	private int dateMonth;
	@SerializedName("redpackCount")
	private int redpackCount;
	@SerializedName("shareCount")
	private int shareCount;
	@SerializedName("redpackMoney")
	private int redpackMoney;
	@SerializedName("redpackPoint")
	private int redpackPoint;
	@SerializedName("shareRedpackMoney")
	private int shareRedpackMoney;
	@SerializedName("shareRedpackPoint")
	private int shareRedpackPoint;
	@SerializedName("profitMoney")
	private int profitMoney;
	@SerializedName("profitPoint")
	private int profitPoint;
	@SerializedName("totalRedpackMoney")
	private int totalRedpackMoney;
	@SerializedName("totalRedpackPoint")
	private int totalRedpackPoint;
	@SerializedName("fansCount")
	private int fansCount;
	@SerializedName("makerCount")
	private int makerCount;
	@SerializedName("agentCount")
	private int agentCount;
	@SerializedName("yyyDeviceAmount")
	private int yyyDeviceAmount;
	@SerializedName("yyySalesmanDeviceAmount")
	private int yyySalesmanDeviceAmount;
	@SerializedName("wifiDeviceAmount")
	private int wifiDeviceAmount;
	@SerializedName("wifiSalesmanDeviceAmount")
	private int wifiSalesmanDeviceAmount;
	@SerializedName("totalDeviceAmount")
	private int totalDeviceAmount;
	@SerializedName("addDateLong")
	private long addDateLong;
	@SerializedName("updateDateLong")
	private long updateDateLong;
}