/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class ArticleSortInfo
{
	@SerializedName("sortId")
	private String sortId;
	@SerializedName("name")
	private String name;
	@SerializedName("parentId")
	private String parentId;
	@SerializedName("levelId")
	private byte levelId;
	@SerializedName("status")
	private byte status;
}