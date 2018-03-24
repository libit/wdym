/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.enums;

import java.util.HashMap;
import java.util.Map;

public enum ClientPosition
{
	PAGE_START("START", "启动页"), PAGE_INDEX("INDEX", "首页"), PRODUCT_CONSULT("PRODUCT_CONSULT", "产品资讯页面"), PAGE_HELP("PAGE_HELP", "帮助页面"), PAGE_BRAND_REDPACK("PAGE_YYY", "品牌红包页面"), PAGE_NET_REDPACK("P_NET_REDPACK", "网盟红包页面"), PAGE_REG_REDPACK("P_REG_REDPACK", "注册红包页面"), PAGE_SURVEY_REDPACK("SURVEY_REDPACK", "问卷红包页面"), PAGE_SERVICE_SHOP("P_SERVICE_SHOP", "福利商城页面"), PAGE_SERVICE_MAKER("P_SERVICE_MAKER", "创客页面"), PAGE_SERVICE_AGENT("P_SERVICE_AGENT", "合伙人页面"), PAGE_FANJUAN("P_FANJUAN", "返券页面"), PAGE_ZIXUN("P_ZIXUN", "资讯页面");
	private String position;
	private String desc;

	ClientPosition(String position, String desc)
	{
		this.position = position;
		this.desc = desc;
	}

	public String getPosition()
	{
		return position;
	}

	public void setPosition(String position)
	{
		this.position = position;
	}

	public String getDesc()
	{
		return desc;
	}

	public void setDesc(String desc)
	{
		this.desc = desc;
	}

	public static String getTypeDesc(String position)
	{
		ClientPosition[] list = ClientPosition.values();
		for (ClientPosition clientPosition : list)
		{
			if (clientPosition.getPosition().equals(position))
			{
				return clientPosition.getDesc();
			}
		}
		return "";
	}

	public static Map<String, Object> getMap()
	{
		ClientPosition[] list = ClientPosition.values();
		Map<String, Object> map = new HashMap<>();
		for (ClientPosition clientPosition : list)
		{
			map.put(clientPosition.getPosition(), clientPosition.getDesc());
		}
		return map;
	}
}
