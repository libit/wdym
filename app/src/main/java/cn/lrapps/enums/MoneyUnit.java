/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 货币单位
 *
 * @author libit
 */
public enum MoneyUnit
{
	RMB((byte) 0, "人民币"), JINBI((byte) 1, "金币");
	private byte type;
	private String desc;

	MoneyUnit(byte type, String desc)
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

	public static Map<String, Object> getMap()
	{
		MoneyUnit[] list = MoneyUnit.values();
		Map<String, Object> modelMap = new HashMap<>();
		for (MoneyUnit moneyUnit : list)
		{
			modelMap.put(moneyUnit.name(), moneyUnit.getType());
		}
		return modelMap;
	}
}
