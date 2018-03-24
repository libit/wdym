/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class WxContentImgInfo
{
	@SerializedName("imgId")
	private String imgId;
	@SerializedName("wxPublicId")
	private String wxPublicId;
	@SerializedName("url")
	private String url;
	@SerializedName("picUrl")
	private String picUrl;
	@SerializedName("addDateLong")
	private long addDateLong;
}