/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.enums;

import java.util.HashMap;
import java.util.Map;

public enum ClientType
{
	BANNER("BANNER", "展示图"), BUTTON("BUTTON", "功能按钮图");
	private String type;
	private String desc;

	ClientType(String type, String desc)
	{
		this.type = type;
		this.desc = desc;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
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

	public static String getTypeDesc(String type)
	{
		ClientType[] list = ClientType.values();
		for (ClientType clientBannerType : list)
		{
			if (clientBannerType.getType().equals(type))
			{
				return clientBannerType.getDesc();
			}
		}
		return "";
	}

	public static Map<String, Object> getMap()
	{
		ClientType[] list = ClientType.values();
		Map<String, Object> map = new HashMap<>();
		for (ClientType clientBannerType : list)
		{
			map.put(clientBannerType.getType(), clientBannerType.getDesc());
		}
		return map;
	}
}
