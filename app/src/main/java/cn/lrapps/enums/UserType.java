/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户类型
 *
 * @author libit
 */
public enum UserType
{
	COMMON((byte) 0, "普通用户"), SALESMAN((byte) 0, "业务员"), COUNTRY((byte) 5, "县合伙人"), DISTRICT((byte) 10, "区合伙人"), CITY((byte) 20, "市合伙人"), PROVINCE((byte) 30, "省合伙人");
	private byte type;
	private String desc;

	UserType(byte type, String desc)
	{
		this.type = type;
		this.desc = desc;
	}

	public byte getType()
	{
		return type;
	}

	public void setType(byte type)
	{
		this.type = type;
	}

	public String getDesc()
	{
		return desc;
	}

	public void setDesc(String desc)
	{
		this.desc = desc;
	}

	public static String getDesc(byte type)
	{
		UserType[] list = UserType.values();
		{
			for (UserType userType : list)
			{
				if (userType.getType() == type)
				{
					return userType.getDesc();
				}
			}
		}
		return "未知类型";
	}

	public static byte getNextType(byte userType)
	{
		if (userType == UserType.COMMON.getType())
		{
			userType = UserType.DISTRICT.getType();
		}
		else if (userType == UserType.DISTRICT.getType())
		{
			userType = UserType.CITY.getType();
		}
		else if (userType == UserType.CITY.getType())
		{
			userType = UserType.PROVINCE.getType();
		}
		else
		{
			return (byte) -1;
		}
		return userType;
	}

	public static Map<String, UserType> getMap()
	{
		UserType[] userTypes = UserType.values();
		Map<String, UserType> map = new HashMap<>();
		for (UserType userType : userTypes)
		{
			map.put(userType.name(), userType);
		}
		return map;
	}
}
