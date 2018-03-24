/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class AdviceInfo
{
	@SerializedName("adviceId")
	private String adviceId;
	@SerializedName("platform")
	private String platform;
	@SerializedName("userId")
	private String userId;
	@SerializedName("number")
	private String number;
	@SerializedName("email")
	private String email;
	@SerializedName("qq")
	private String qq;
	@SerializedName("content")
	private String content;
	@SerializedName("readStatus")
	private byte readStatus;
	@SerializedName("replyStatus")
	private byte replyStatus;
	@SerializedName("status")
	private byte status;
	@SerializedName("addDateLong")
	private long addDateLong;

	public String getAdviceId()
	{
		return adviceId;
	}

	public void setAdviceId(String adviceId)
	{
		this.adviceId = adviceId;
	}

	public String getPlatform()
	{
		return platform;
	}

	public void setPlatform(String platform)
	{
		this.platform = platform;
	}

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public String getNumber()
	{
		return number;
	}

	public void setNumber(String number)
	{
		this.number = number;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getQq()
	{
		return qq;
	}

	public void setQq(String qq)
	{
		this.qq = qq;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public byte getReadStatus()
	{
		return readStatus;
	}

	public void setReadStatus(byte readStatus)
	{
		this.readStatus = readStatus;
	}

	public byte getReplyStatus()
	{
		return replyStatus;
	}

	public void setReplyStatus(byte replyStatus)
	{
		this.replyStatus = replyStatus;
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