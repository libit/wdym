/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class UserAuthInfo
{
	@SerializedName("userId")
	private String userId;
	@SerializedName("name")
	private String name;
	@SerializedName("number")
	private String number;
	@SerializedName("sex")
	private byte sex;
	@SerializedName("birthday")
	private long birthday;
	@SerializedName("identifityNumber")
	private String identifityNumber;
	@SerializedName("province")
	private String province;
	@SerializedName("city")
	private String city;
	@SerializedName("address")
	private String address;
	@SerializedName("picUrl")
	private String picUrl;
	@SerializedName("adminId")
	private String adminId;
	@SerializedName("remark")
	private String remark;
	@SerializedName("verifyDateLong")
	private Long verifyDateLong;
	@SerializedName("phoneStatus")
	private byte phoneStatus;
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

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getNumber()
	{
		return number;
	}

	public void setNumber(String number)
	{
		this.number = number;
	}

	public byte getSex()
	{
		return sex;
	}

	public void setSex(byte sex)
	{
		this.sex = sex;
	}

	public long getBirthday()
	{
		return birthday;
	}

	public void setBirthday(long birthday)
	{
		this.birthday = birthday;
	}

	public String getIdentifityNumber()
	{
		return identifityNumber;
	}

	public void setIdentifityNumber(String identifityNumber)
	{
		this.identifityNumber = identifityNumber;
	}

	public String getProvince()
	{
		return province;
	}

	public void setProvince(String province)
	{
		this.province = province;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getPicUrl()
	{
		return picUrl;
	}

	public void setPicUrl(String picUrl)
	{
		this.picUrl = picUrl;
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

	public byte getPhoneStatus()
	{
		return phoneStatus;
	}

	public void setPhoneStatus(byte phoneStatus)
	{
		this.phoneStatus = phoneStatus;
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