/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 申请状态
 *
 * @author libit
 */
public enum ApplyStatus
{
	APPLY((byte) 0, "已申请"), SUBMIT_WX((byte) 5, "已提交到微信服务器"), PAYED((byte) 8, "已支付"), VERIFY_SUCCESS((byte) 10, "审核成功"), VERIFY_FAIL((byte) 20, "审核失败"), PROCESSED((byte) 30, "已处理");
	private byte status;
	private String desc;

	private ApplyStatus(byte status, String desc)
	{
		this.status = status;
		this.desc = desc;
	}

	public byte getStatus()
	{
		return status;
	}

	public void setStatus(byte status)
	{
		this.status = status;
	}

	public String getDesc()
	{
		return desc;
	}

	public void setDesc(String desc)
	{
		this.desc = desc;
	}

	public static String getDesc(byte status)
	{
		ApplyStatus[] applyStatuses = ApplyStatus.values();
		for (ApplyStatus applyStatus : applyStatuses)
		{
			if (applyStatus.getStatus() == status)
			{
				return applyStatus.getDesc();
			}
		}
		return "";
	}

	public static Map<String, Byte> getMap()
	{
		ApplyStatus[] applyStatuses = ApplyStatus.values();
		Map<String, Byte> map = new HashMap<>();
		for (ApplyStatus applyStatus : applyStatuses)
		{
			map.put(applyStatus.name(), applyStatus.getStatus());
		}
		return map;
	}
}
