/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.enums;

/**
 * 账户余额类型
 *
 * @author libit
 */
public enum BalanceType
{
	COMMON((byte) 0, "普通余额"), POINT((byte) 2, "金币");
	private byte type;
	private String desc;

	BalanceType(byte type, String desc)
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
}
