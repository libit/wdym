/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.enums;

/**
 * 状态
 * Created by libit on 16/7/12.
 */
public enum StatusType
{
	ENABLE((byte) 0), DISABLE((byte) 1);
	private byte status;

	StatusType(byte status)
	{
		this.status = status;
	}

	public byte getStatus()
	{
		return status;
	}
}
