/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.android.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import cn.lrapps.utils.StringTools;

public class AppStatusReceiver extends BroadcastReceiver
{
	private static final String TAG = AppStatusReceiver.class.getSimpleName();
	private static final String INTENT_APP_INSTALL = "android.intent.action.PACKAGE_ADDED";// 应用安装
	private static final String INTENT_APP_UNINSTALL = "android.intent.action.PACKAGE_REMOVED";//应用卸载

	@Override
	public void onReceive(Context context, Intent intent)
	{
		String action = intent.getAction();
		if (StringTools.isNull(action))
		{
			return;
		}
		if (action.equals(INTENT_APP_INSTALL))// 应用安装
		{
		}
		else if (action.equals(INTENT_APP_UNINSTALL))//应用卸载
		{
		}
	}
}
