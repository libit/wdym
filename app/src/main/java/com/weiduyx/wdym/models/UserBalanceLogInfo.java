/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class UserBalanceLogInfo
{
	@SerializedName("userId")
	private String userId;
	@SerializedName("money")
	private int money;
	@SerializedName("moneyUnit")
	private byte moneyUnit;
	@SerializedName("logType")
	private String logType;
	@SerializedName("serverId")
	private String serverId;
	@SerializedName("remark")
	private String remark;
	@SerializedName("addDateLong")
	private long addDateLong;

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public int getMoney()
	{
		return money;
	}

	public void setMoney(int money)
	{
		this.money = money;
	}

	public byte getMoneyUnit()
	{
		return moneyUnit;
	}

	public void setMoneyUnit(byte moneyUnit)
	{
		this.moneyUnit = moneyUnit;
	}

	public String getLogType()
	{
		return logType;
	}

	public void setLogType(String logType)
	{
		this.logType = logType;
	}

	public String getServerId()
	{
		return serverId;
	}

	public void setServerId(String serverId)
	{
		this.serverId = serverId;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
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