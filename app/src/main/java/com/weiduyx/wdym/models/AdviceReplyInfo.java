/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class AdviceReplyInfo
{
	@SerializedName("replyId")
	private String replyId;
	@SerializedName("adviceId")
	private String adviceId;
	@SerializedName("content")
	private String content;
	@SerializedName("parentId")
	private String parentId;
	@SerializedName("status")
	private byte status;
	@SerializedName("addDateLong")
	private long addDateLong;

	public String getReplyId()
	{
		return replyId;
	}

	public void setReplyId(String replyId)
	{
		this.replyId = replyId;
	}

	public String getAdviceId()
	{
		return adviceId;
	}

	public void setAdviceId(String adviceId)
	{
		this.adviceId = adviceId;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public String getParentId()
	{
		return parentId;
	}

	public void setParentId(String parentId)
	{
		this.parentId = parentId;
	}

	public byte getStatus()
	{
		return status;
	}

	public void setStatus(byte status)
	{
		this.status = status;
	}

	public long getAddDateLong()
	{
		return addDateLong;
	}

	public void setAddDateLong(long addDateLong)
	{
		this.addDateLong = addDateLong;
	}
}