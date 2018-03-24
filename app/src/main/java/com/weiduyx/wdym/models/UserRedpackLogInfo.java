/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class UserRedpackLogInfo
{
	@SerializedName("id")
	private Integer id;
	@SerializedName("userId")
	private String userId;
	@SerializedName("redpackFrom")
	private String redpackFrom;
	@SerializedName("sendId")
	private String sendId;
	@SerializedName("money")
	private int money;
	@SerializedName("moneyUnit")
	private byte moneyUnit;
	@SerializedName("shareMoney")
	private int shareMoney;
	@SerializedName("shareMoneyUnit")
	private byte shareMoneyUnit;
	@SerializedName("shareStatus")
	private byte shareStatus;
	@SerializedName("shareDateLong")
	private Long shareDateLong;
	@SerializedName("shareRedpackGetDateLong")
	private Long shareRedpackGetDateLong;
	@SerializedName("remark")
	private String remark;
	@SerializedName("status")
	private byte status;
	@SerializedName("redpackGetDateLong")
	private Long redpackGetDateLong;
	@SerializedName("isRollback")
	private Byte isRollback;
	@SerializedName("addDateLong")
	private long addDateLong;
	@SerializedName("updateDateLong")
	private long updateDateLong;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public String getRedpackFrom()
	{
		return redpackFrom;
	}

	public void setRedpackFrom(String redpackFrom)
	{
		this.redpackFrom = redpackFrom;
	}

	public String getSendId()
	{
		return sendId;
	}

	public void setSendId(String sendId)
	{
		this.sendId = sendId;
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

	public int getShareMoney()
	{
		return shareMoney;
	}

	public void setShareMoney(int shareMoney)
	{
		this.shareMoney = shareMoney;
	}

	public byte getShareMoneyUnit()
	{
		return shareMoneyUnit;
	}

	public void setShareMoneyUnit(byte shareMoneyUnit)
	{
		this.shareMoneyUnit = shareMoneyUnit;
	}

	public byte getShareStatus()
	{
		return shareStatus;
	}

	public void setShareStatus(byte shareStatus)
	{
		this.shareStatus = shareStatus;
	}

	public Long getShareDateLong()
	{
		return shareDateLong;
	}

	public void setShareDateLong(Long shareDateLong)
	{
		this.shareDateLong = shareDateLong;
	}

	public Long getShareRedpackGetDateLong()
	{
		return shareRedpackGetDateLong;
	}

	public void setShareRedpackGetDateLong(Long shareRedpackGetDateLong)
	{
		this.shareRedpackGetDateLong = shareRedpackGetDateLong;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	public byte getStatus()
	{
		return status;
	}

	public void setStatus(byte status)
	{
		this.status = status;
	}

	public Long getRedpackGetDateLong()
	{
		return redpackGetDateLong;
	}

	public void setRedpackGetDateLong(Long redpackGetDateLong)
	{
		this.redpackGetDateLong = redpackGetDateLong;
	}

	public Byte getIsRollback()
	{
		return isRollback;
	}

	public void setIsRollback(Byte isRollback)
	{
		this.isRollback = isRollback;
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