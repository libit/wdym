/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class WxThirdUserInfo
{
	@SerializedName("wxOpenId")
	private String wxOpenId;
	@SerializedName("originalId")
	private String originalId;
	@SerializedName("userId")
	private String userId;
	@SerializedName("userFrom")
	private String userFrom;
	@SerializedName("nickname")
	private String nickname;
	@SerializedName("sex")
	private Byte sex;
	@SerializedName("country")
	private String country;
	@SerializedName("province")
	private String province;
	@SerializedName("city")
	private String city;
	@SerializedName("subscribe")
	private String subscribe;
	@SerializedName("subscribeTime")
	private Long subscribeTime;
	@SerializedName("language")
	private String language;
	@SerializedName("groupId")
	private String groupId;
	@SerializedName("headImgUrl")
	private String headImgUrl;
	@SerializedName("wxUpdateDateLong")
	private long wxUpdateDateLong;
	@SerializedName("wxStatus")
	private byte wxStatus;
	@SerializedName("redpackStatus")
	private byte redpackStatus;
	@SerializedName("status")
	private byte status;
	@SerializedName("remark")
	private String remark;
	@SerializedName("addDateLong")
	private long addDateLong;
	@SerializedName("updateDateLong")
	private long updateDateLong;
}