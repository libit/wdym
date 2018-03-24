/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 广告类型
 *
 * @author libit
 */
public enum AdType
{
	COMMON("1", "品牌广告"), WECHART("2", "公众号吸粉"), ARTICLE("3", "注册广告"), NET("4", "网盟广告"), SURVEY("5", "问卷红包"), DOWNLOAD_APP("6", "下载红包");
	private String type;
	private String desc;

	private AdType(String type, String desc)
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

	public static String getDesc(String type)
	{
		AdType[] list = AdType.values();
		for (AdType adType : list)
		{
			if (adType.getType().equals(type))
			{
				return adType.getDesc();
			}
		}
		return "";
	}

	public static Map<String, Object> getMap()
	{
		AdType[] list = AdType.values();
		Map<String, Object> modelMap = new HashMap<>();
		for (AdType adType : list)
		{
			modelMap.put(adType.name(), adType.getType());
		}
		return modelMap;
	}
}
