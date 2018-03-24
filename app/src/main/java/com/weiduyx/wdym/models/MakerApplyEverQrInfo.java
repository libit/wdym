/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class MakerApplyEverQrInfo
{
	@SerializedName("userId")
	private String userId;
	@SerializedName("agentId")
	private String agentId;
	@SerializedName("content")
	private String content;
	@SerializedName("money")
	private int money;
	@SerializedName("payOrderId")
	private String payOrderId;
	@SerializedName("adminId")
	private String adminId;
	@SerializedName("remark")
	private String remark;
	@SerializedName("verifyDateLong")
	private Long verifyDateLong;
	@SerializedName("status")
	private byte status;
	@SerializedName("addDateLong")
	private long addDateLong;
	@SerializedName("updateDateLong")
	private long updateDateLong;

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public String getAgentId()
	{
		return agentId;
	}

	public void setAgentId(String agentId)
	{
		this.agentId = agentId;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public int getMoney()
	{
		return money;
	}

	public void setMoney(int money)
	{
		this.money = money;
	}

	public String getPayOrderId()
	{
		return payOrderId;
	}

	public void setPayOrderId(String payOrderId)
	{
		this.payOrderId = payOrderId;
	}

	public String getAdminId()
	{
		return adminId;
	}

	public void setAdminId(String adminId)
	{
		this.adminId = adminId;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	public Long getVerifyDateLong()
	{
		return verifyDateLong;
	}

	public void setVerifyDateLong(Long verifyDateLong)
	{
		this.verifyDateLong = verifyDateLong;
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

	public long getUpdateDateLong()
	{
		return updateDateLong;
	}

	public void setUpdateDateLong(long updateDateLong)
	{
		this.updateDateLong = updateDateLong;
	}
}