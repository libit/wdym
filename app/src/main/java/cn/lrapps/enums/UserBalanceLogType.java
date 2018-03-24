/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.enums;

/**
 * 用户余额变动记录类型
 *
 * @author libit
 */
public enum UserBalanceLogType
{
	RECEIVE_REDPACK("receive_redpack", "收红包"), RECEIVE_SHARE_REDPACK("r_share_redpack", "收分享红包"), AD_REDPACK_PROFIT("a_redpack_profit", "广告红包返利"), WITHDRAW("withdraw", "余额提现"), SHARE_PROFIT("share_profit", "分享朋友圈返利"), RECHARGE_BALANCE("recharge_b", "余额充值");
	private String type;
	private String desc;

	private UserBalanceLogType(String type, String desc)
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

	public static String getLogString(String logType)
	{
		UserBalanceLogType[] userBalanceLogTypes = UserBalanceLogType.values();
		for (UserBalanceLogType userBalanceLogType : userBalanceLogTypes)
		{
			if (logType.equals(userBalanceLogType.type))
			{
				return userBalanceLogType.desc;
			}
		}
		return "";
	}
}
