/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.android.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import cn.lrapps.utils.LogTools;
import cn.lrapps.utils.PreferenceUtils;
import cn.lrapps.utils.StringTools;

/**
 * 开机启动接收者
 *
 * @author libit
 */
public class SystemBootReceiver extends BroadcastReceiver
{
	private static final String TAG = SystemBootReceiver.class.getSimpleName();

	@Override
	public void onReceive(Context context, Intent intent)
	{
		String action = intent.getAction();
		if (!StringTools.isNull(action))
		{
			if (action.equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED))
			{
				LogTools.debug(TAG, "开机启动事件");
				if (PreferenceUtils.getInstance().getBooleanValue(PreferenceUtils.IS_BOOT_START))
				{
				}
			}
		}
	}
}
