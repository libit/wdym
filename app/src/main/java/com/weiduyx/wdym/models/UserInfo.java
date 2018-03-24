/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class UserInfo
{
	@SerializedName("userId")
	private String userId;
	@SerializedName("wxOpenId")
	private String wxOpenId;
	@SerializedName("wxUnionId")
	private String wxUnionId;
	@SerializedName("sessionId")
	private String sessionId;
	@SerializedName("userType")
	private byte userType;
	@SerializedName("agentId")
	private String agentId;
	@SerializedName("agentId2")
	private String agentId2;
	@SerializedName("referrerId")
	private String referrerId;
	@SerializedName("referrerDateLong")
	private Long referrerDateLong;
	@SerializedName("number")
	private String number;
	@SerializedName("name")
	private String name;
	@SerializedName("nickname")
	private String nickname;
	@SerializedName("sex")
	private Byte sex;
	@SerializedName("email")
	private String email;
	@SerializedName("picId")
	private String picId;
	@SerializedName("birthday")
	private Long birthday;
	@SerializedName("country")
	private String country;
	@SerializedName("province")
	private String province;
	@SerializedName("city")
	private String city;
	@SerializedName("address")
	private String address;
	@SerializedName("language")
	private String language;
	@SerializedName("headImgUrl")
	private String headImgUrl;
	@SerializedName("imgQr")
	private String imgQr;
	@SerializedName("withdrawQr")
	private String withdrawQr;
	@SerializedName("yyyAdCount")
	private int yyyAdCount;
	@SerializedName("wifiAdCount")
	private int wifiAdCount;
	@SerializedName("yyyDeviceCount")
	private int yyyDeviceCount;
	@SerializedName("wifiDeviceCount")
	private int wifiDeviceCount;
	@SerializedName("balance")
	private int balance;
	@SerializedName("freezeBalance")
	private int freezeBalance;
	@SerializedName("point")
	private int point;
	@SerializedName("freezePoint")
	private int freezePoint;
	@SerializedName("makerBalance")
	private int makerBalance;
	@SerializedName("makerFreezeBalance")
	private int makerFreezeBalance;
	@SerializedName("lastLoginTime")
	private String lastLoginTime;
	@SerializedName("location")
	private String location;
	@SerializedName("wxStatus")
	private byte wxStatus;
	@SerializedName("authStatus")
	private byte authStatus;
	@SerializedName("makerStatus")
	private byte makerStatus;
	@SerializedName("aduserStatus")
	private byte aduserStatus;
	@SerializedName("makerEverQrStatus")
	private byte makerEverQrStatus;
	@SerializedName("status")
	private byte status;
	@SerializedName("remark")
	private String remark;
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

	public String getWxOpenId()
	{
		return wxOpenId;
	}

	public void setWxOpenId(String wxOpenId)
	{
		this.wxOpenId = wxOpenId;
	}

	public String getWxUnionId()
	{
		return wxUnionId;
	}

	public void setWxUnionId(String wxUnionId)
	{
		this.wxUnionId = wxUnionId;
	}

	public String getSessionId()
	{
		return sessionId;
	}

	public void setSessionId(String sessionId)
	{
		this.sessionId = sessionId;
	}

	public byte getUserType()
	{
		return userType;
	}

	public void setUserType(byte userType)
	{
		this.userType = userType;
	}

	public String getAgentId()
	{
		return agentId;
	}

	public void setAgentId(String agentId)
	{
		this.agentId = agentId;
	}

	public String getAgentId2()
	{
		return agentId2;
	}

	public void setAgentId2(String agentId2)
	{
		this.agentId2 = agentId2;
	}

	public String getReferrerId()
	{
		return referrerId;
	}

	public void setReferrerId(String referrerId)
	{
		this.referrerId = referrerId;
	}

	public Long getReferrerDateLong()
	{
		return referrerDateLong;
	}

	public void setReferrerDateLong(Long referrerDateLong)
	{
		this.referrerDateLong = referrerDateLong;
	}

	public String getNumber()
	{
		return number;
	}

	public void setNumber(String number)
	{
		this.number = number;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getNickname()
	{
		return nickname;
	}

	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}

	public Byte getSex()
	{
		return sex;
	}

	public void setSex(Byte sex)
	{
		this.sex = sex;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPicId()
	{
		return picId;
	}

	public void setPicId(String picId)
	{
		this.picId = picId;
	}

	public Long getBirthday()
	{
		return birthday;
	}

	public void setBirthday(Long birthday)
	{
		this.birthday = birthday;
	}

	public String getCountry()
	{
		return country;
	}

	public void setCountry(String country)
	{
		this.country = country;
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

	public String getLanguage()
	{
		return language;
	}

	public void setLanguage(String language)
	{
		this.language = language;
	}

	public String getHeadImgUrl()
	{
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl)
	{
		this.headImgUrl = headImgUrl;
	}

	public String getImgQr()
	{
		return imgQr;
	}

	public void setImgQr(String imgQr)
	{
		this.imgQr = imgQr;
	}

	public String getWithdrawQr()
	{
		return withdrawQr;
	}

	public void setWithdrawQr(String withdrawQr)
	{
		this.withdrawQr = withdrawQr;
	}

	public int getYyyAdCount()
	{
		return yyyAdCount;
	}

	public void setYyyAdCount(int yyyAdCount)
	{
		this.yyyAdCount = yyyAdCount;
	}

	public int getWifiAdCount()
	{
		return wifiAdCount;
	}

	public void setWifiAdCount(int wifiAdCount)
	{
		this.wifiAdCount = wifiAdCount;
	}

	public int getYyyDeviceCount()
	{
		return yyyDeviceCount;
	}

	public void setYyyDeviceCount(int yyyDeviceCount)
	{
		this.yyyDeviceCount = yyyDeviceCount;
	}

	public int getWifiDeviceCount()
	{
		return wifiDeviceCount;
	}

	public void setWifiDeviceCount(int wifiDeviceCount)
	{
		this.wifiDeviceCount = wifiDeviceCount;
	}

	public int getBalance()
	{
		return balance;
	}

	public void setBalance(int balance)
	{
		this.balance = balance;
	}

	public int getFreezeBalance()
	{
		return freezeBalance;
	}

	public void setFreezeBalance(int freezeBalance)
	{
		this.freezeBalance = freezeBalance;
	}

	public int getPoint()
	{
		return point;
	}

	public void setPoint(int point)
	{
		this.point = point;
	}

	public int getFreezePoint()
	{
		return freezePoint;
	}

	public void setFreezePoint(int freezePoint)
	{
		this.freezePoint = freezePoint;
	}

	public int getMakerBalance()
	{
		return makerBalance;
	}

	public void setMakerBalance(int makerBalance)
	{
		this.makerBalance = makerBalance;
	}

	public int getMakerFreezeBalance()
	{
		return makerFreezeBalance;
	}

	public void setMakerFreezeBalance(int makerFreezeBalance)
	{
		this.makerFreezeBalance = makerFreezeBalance;
	}

	public String getLastLoginTime()
	{
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime)
	{
		this.lastLoginTime = lastLoginTime;
	}

	public String getLocation()
	{
		return location;
	}

	public void setLocation(String location)
	{
		this.location = location;
	}

	public byte getWxStatus()
	{
		return wxStatus;
	}

	public void setWxStatus(byte wxStatus)
	{
		this.wxStatus = wxStatus;
	}

	public byte getAuthStatus()
	{
		return authStatus;
	}

	public void setAuthStatus(byte authStatus)
	{
		this.authStatus = authStatus;
	}

	public byte getMakerStatus()
	{
		return makerStatus;
	}

	public void setMakerStatus(byte makerStatus)
	{
		this.makerStatus = makerStatus;
	}

	public byte getAduserStatus()
	{
		return aduserStatus;
	}

	public void setAduserStatus(byte aduserStatus)
	{
		this.aduserStatus = aduserStatus;
	}

	public byte getMakerEverQrStatus()
	{
		return makerEverQrStatus;
	}

	public void setMakerEverQrStatus(byte makerEverQrStatus)
	{
		this.makerEverQrStatus = makerEverQrStatus;
	}

	public byte getStatus()
	{
		return status;
	}

	public void setStatus(byte status)
	{
		this.status = status;
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

	public long getUpdateDateLong()
	{
		return updateDateLong;
	}

	public void setUpdateDateLong(long updateDateLong)
	{
		this.updateDateLong = updateDateLong;
	}
}