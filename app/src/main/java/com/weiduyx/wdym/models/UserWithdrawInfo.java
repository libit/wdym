/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class UserWithdrawInfo
{
	@SerializedName("withdrawId")
	private String withdrawId;
	@SerializedName("userId")
	private String userId;
	@SerializedName("money")
	private int money;
	@SerializedName("moneyType")
	private byte moneyType;
	@SerializedName("mchBillno")
	private String mchBillno;
	@SerializedName("redpackType")
	private String redpackType;
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
	@SerializedName("userInfo")
	private UserInfo userInfo;

	public String getWithdrawId()
	{
		return withdrawId;
	}

	public void setWithdrawId(String withdrawId)
	{
		this.withdrawId = withdrawId;
	}

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

	public byte getMoneyType()
	{
		return moneyType;
	}

	public void setMoneyType(byte moneyType)
	{
		this.moneyType = moneyType;
	}

	public String getMchBillno()
	{
		return mchBillno;
	}

	public void setMchBillno(String mchBillno)
	{
		this.mchBillno = mchBillno;
	}

	public String getRedpackType()
	{
		return redpackType;
	}

	public void setRedpackType(String redpackType)
	{
		this.redpackType = redpackType;
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

	public UserInfo getUserInfo()
	{
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo)
	{
		this.userInfo = userInfo;
	}
}