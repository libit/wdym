/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.utils;

import cn.lrapps.utils.apptools.SystemToolsFactory;

public class AppConfig
{
	/**
	 * 代理ID
	 */
	public static final String AGENT_ID = "wdym";
	/**
	 * 平台类型
	 */
	public static final String PLATFORM = "android";
	/**
	 * 程序存放数据的目录
	 */
	public static final String APP_FOLDER = "wdym";
	/**
	 * 数据分享的名字
	 */
	public static final String AUTHORITY_NAME = "wdym";

	/**
	 * 调试模式
	 * 用于显示LOG，签名验证等
	 *
	 * @return
	 */
	public static boolean isDebug()
	{
		LogTools.info("isDebug", "md5:" + CryptoTools.getMD5Str(SystemToolsFactory.getInstance().getCertInfo()));
		if (CryptoTools.getMD5Str(SystemToolsFactory.getInstance().getCertInfo()).equalsIgnoreCase("d77d764391ef9ad87066d19bb6ef3bc3"))
		{
			PreferenceUtils.getInstance().setBooleanValue(PreferenceUtils.IS_DEBUG, false);
			return false;
		}
		return true;
	}

	/**
	 * 获取程序存放数据的目录
	 *
	 * @return
	 */
	public static String getSDCardFolder()
	{
		return APP_FOLDER + "/" + AGENT_ID;
	}

	/**
	 * 升级程序的文件夹
	 *
	 * @return
	 */
	public static String getUpdateFolder()
	{
		return "update";
	}

	/**
	 * 日志记录的文件夹
	 *
	 * @return
	 */
	public static String getLogcatFolder()
	{
		return "logcat";
	}
}
