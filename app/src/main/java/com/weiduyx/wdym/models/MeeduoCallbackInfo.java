/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class MeeduoCallbackInfo
{
	@SerializedName("eventid")
	private String eventid;
	@SerializedName("ip")
	private String ip;
	@SerializedName("memberid")
	private String memberid;
	@SerializedName("userId")
	private String userId;
	@SerializedName("point")
	private String point;
	@SerializedName("totalpoint")
	private String totalpoint;
	@SerializedName("sid")
	private String sid;
	@SerializedName("acode")
	private String acode;
	@SerializedName("title")
	private String title;
	@SerializedName("immediate")
	private String immediate;
	@SerializedName("pm1")
	private String pm1;
	@SerializedName("sign")
	private String sign;
	@SerializedName("status")
	private byte status;
	@SerializedName("addDateLong")
	private long addDateLong;
	@SerializedName("updateDateLong")
	private long updateDateLong;
}