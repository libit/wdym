/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.utils.apptools;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import cn.lrapps.android.ui.MyApplication;
import cn.lrapps.utils.LogTools;
import cn.lrapps.utils.StringTools;
import com.weiduyx.wdym.models.ErrorInfo;
import com.weiduyx.wdym.models.ReturnInfo;

import java.io.File;
import java.util.List;

/**
 * Created by libit on 15/9/29.
 */
public abstract class IAppService
{
	/**
	 * 安装App
	 *
	 * @param file 安装包
	 */
	abstract ReturnInfo installApp(File file);

	/**
	 * 卸载App
	 *
	 * @param packageName App包名
	 */
	abstract ReturnInfo uninstallApp(String packageName);

	/**
	 * 启动应用程序
	 *
	 * @param packageName 应用包名
	 *
	 * @return
	 */
	public ReturnInfo startApp(Context context, String packageName)
	{
		if (StringTools.isNull(packageName))
		{
			return new ReturnInfo(ErrorInfo.PARAM_ERROR, "包名不能为空");
		}
		boolean isFind = false;
		//		if (context == null)
		{
			context = MyApplication.getInstance();
		}
		LogTools.debug("startApp", packageName + "启动context：" + context);
		PackageManager packageManager = MyApplication.getContext().getPackageManager();
		PackageInfo packageInfo = null;
		try
		{
			packageInfo = packageManager.getPackageInfo(packageName, 0);
		}
		catch (PackageManager.NameNotFoundException e)
		{
			e.printStackTrace();
		}
		if (packageInfo == null)
		{
			return new ReturnInfo(ErrorInfo.NOT_EXIST_ERROR, "包名不存在");
		}
		Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
		resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		resolveIntent.setPackage(packageName);
		List<ResolveInfo> resolveInfoList = packageManager.queryIntentActivities(resolveIntent, 0);
		while (resolveInfoList.iterator().hasNext())
		{
			ResolveInfo resolveInfo = resolveInfoList.iterator().next();
			if (resolveInfo != null)
			{
				String className = resolveInfo.activityInfo.name;
				Intent intent = new Intent(Intent.ACTION_MAIN);
				intent.addCategory(Intent.CATEGORY_LAUNCHER);
				intent.setComponent(new ComponentName(packageName, className));
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(intent);
				isFind = true;
				LogTools.debug("startApp", packageName + "启动class：" + className);
				break;
			}
		}
		if (isFind)
		{
			return new ReturnInfo(ErrorInfo.SUCCESS, "启动成功");
		}
		else
		{
			return new ReturnInfo(ErrorInfo.UNKNOWN_ERROR, "启动失败");
		}
	}
}
