/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.utils.apptools;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import cn.lrapps.android.ui.MyApplication;
import cn.lrapps.enums.AppType;
import cn.lrapps.models.AppInfo;
import cn.lrapps.utils.LogTools;
import cn.lrapps.utils.StringTools;
import com.weiduyx.wdym.models.ErrorInfo;
import com.weiduyx.wdym.models.ReturnInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by libit on 15/8/19.
 */
public class AppUtil8 extends AppFactory
{
	@Override
	public List<AppInfo> getApps(int type)
	{
		long t0 = System.currentTimeMillis();
		List<AppInfo> appInfos = new ArrayList<>();
		PackageManager packageManager = MyApplication.getContext().getPackageManager();
		List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
		int count = packageInfos.size();
		for (int i = 0; i < count; i++)
		{
			PackageInfo packageInfo = packageInfos.get(i);
			ApplicationInfo applicationInfo = packageInfo.applicationInfo;
			int appType = (applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) > 0 ? AppType.SYSTEM.getType() : AppType.USER.getType();
			// 如果要查询的是用户程序
			if (appType != type)
			{
				continue;
			}
			AppInfo appInfo = new AppInfo();
			appInfo.setUid(applicationInfo.uid + "");
			appInfo.setName(applicationInfo.loadLabel(packageManager).toString());
			appInfo.setPackageName(applicationInfo.packageName);
			appInfo.setVersionName(packageInfo.versionName);
			appInfo.setVersionCode(packageInfo.versionCode);
			appInfo.setType(appType);
			LogTools.debug("appInfo", appInfo.toString());
			appInfos.add(appInfo);
		}
		long t1 = System.currentTimeMillis();
		Collections.sort(appInfos, new AppInfo());
		long t2 = System.currentTimeMillis();
		LogTools.debug("getApps", "总花费时间：" + (t2 - t0) + "毫秒,排序花费时间：" + (t2 - t1) + "毫秒");
		return appInfos;
	}

	/**
	 * 获取App信息
	 *
	 * @param packageName App的packageName
	 * @param showPhoto   是否显示图片
	 *
	 * @return App对象
	 */
	@Override
	public AppInfo getAppInfoByPackageName(String packageName, boolean showPhoto)
	{
		AppInfo appInfo = new AppInfo();
		PackageManager packageManager = MyApplication.getContext().getPackageManager();
		try
		{
			PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
			ApplicationInfo applicationInfo = packageInfo.applicationInfo;
			int appType = (applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) > 0 ? AppType.SYSTEM.getType() : AppType.USER.getType();
			appInfo.setUid(applicationInfo.uid + "");
			appInfo.setName(applicationInfo.loadLabel(packageManager).toString());
			appInfo.setPackageName(applicationInfo.packageName);
			appInfo.setVersionName(packageInfo.versionName);
			appInfo.setVersionCode(packageInfo.versionCode);
			if (showPhoto)
			{
				try
				{
					appInfo.setPhoto(((BitmapDrawable) applicationInfo.loadIcon(packageManager)).getBitmap());
				}
				catch (ClassCastException e)
				{
					LogTools.debug("ClassCastException", "\"" + appInfo.getName() + "\"强制转换图片失败了！");
				}
			}
			appInfo.setType(appType);
			LogTools.debug("appInfo", appInfo.toString());
		}
		catch (PackageManager.NameNotFoundException e)
		{
			LogTools.debug("NameNotFoundException", "包名" + packageName + "\"未找到！");
		}
		return appInfo;
	}

	/**
	 * 获取APP的图标
	 *
	 * @param packageName
	 *
	 * @return
	 */
	@Override
	public Drawable getAppIconByPackageName(String packageName)
	{
		PackageManager packageManager = MyApplication.getContext().getPackageManager();
		try
		{
			PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
			ApplicationInfo applicationInfo = packageInfo.applicationInfo;
			return applicationInfo.loadIcon(packageManager);
		}
		catch (PackageManager.NameNotFoundException e)
		{
			LogTools.debug("NameNotFoundException", "包名" + packageName + "\"未找到！");
		}
		return null;
	}

	/**
	 * 获取自身App包信息
	 *
	 * @return App对象
	 */
	@Override
	public PackageInfo getSelfPackageInfo() throws PackageManager.NameNotFoundException
	{
		PackageManager packageManager = MyApplication.getContext().getPackageManager();
		return packageManager.getPackageInfo(MyApplication.getContext().getPackageName(), 0);
	}

	/**
	 * 启动应用程序
	 *
	 * @param packageName 应用包名
	 *
	 * @return 启动成功返回true，失败返回false
	 */
	@Override
	public ReturnInfo startApp(Context context, String packageName)
	{
		if (StringTools.isNull(packageName))
		{
			return new ReturnInfo(ErrorInfo.PARAM_ERROR, "包名不能为空");
		}
		return appService.startApp(context, packageName);
	}

	/**
	 * 获取正在运行的程序列表
	 *
	 * @return App列表
	 */
	@Override
	public List<AppInfo> getRunningApps()
	{
		PackageInfo myPackageInfo = null;// 程序自身的包信息
		try
		{
			myPackageInfo = getSelfPackageInfo();// 程序自身的包信息
		}
		catch (PackageManager.NameNotFoundException e)
		{
		}
		List<AppInfo> appInfoList = new ArrayList<>();
		ActivityManager activityManager = (ActivityManager) MyApplication.getContext().getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningAppProcessInfo> runningAppProcessInfoList = new CopyOnWriteArrayList<>(activityManager.getRunningAppProcesses());
		for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcessInfoList)
		{
			LogTools.debug("runningAppProcessInfo", "uid:" + runningAppProcessInfo.uid + ",processName:" + runningAppProcessInfo.processName);
			for (String packageName : runningAppProcessInfo.pkgList)
			{
				if (myPackageInfo != null && myPackageInfo.packageName.equals(packageName))// 如果是自身，则跳过
				{
					continue;
				}
				// 如果已存在，则跳过继续
				boolean isExist = false;
				for (AppInfo appInfo : appInfoList)
				{
					if (appInfo.getPackageName().equals(packageName))
					{
						isExist = true;
						break;
					}
				}
				if (isExist)
				{
					continue;
				}
				AppInfo newAppInfo = AppFactory.getInstance().getAppInfoByPackageName(packageName, true);
				if (newAppInfo != null)
				{
					LogTools.debug("runningAppProcessInfo", "newAppInfo:" + newAppInfo.toString());
					appInfoList.add(newAppInfo);
				}
			}
		}
		return appInfoList;
	}

	/**
	 * 安装App
	 *
	 * @param file 安装包
	 */
	@Override
	public ReturnInfo installApp(File file, boolean root)
	{
		return appService.installApp(file);
	}

	/**
	 * 卸载App
	 *
	 * @param packageName App包名
	 */
	@Override
	public ReturnInfo uninstallApp(String packageName, boolean root)
	{
		ReturnInfo returnInfo = appService.uninstallApp(packageName);
		return returnInfo;
	}

	/**
	 * 获取apk安装包的信息
	 *
	 * @param apkFile
	 *
	 * @return App列表
	 */
	@Override
	public AppInfo getApkInfo(File apkFile)
	{
		String apkPath = apkFile.getAbsolutePath();
		return getApkInfo(apkPath);
	}

	/**
	 * 获取apk安装包的信息
	 *
	 * @param apkPath apk路径
	 *
	 * @return
	 */
	@Override
	public AppInfo getApkInfo(String apkPath)
	{
		AppInfo appInfo = null;
		PackageManager packageManager = MyApplication.getInstance().getPackageManager();
		PackageInfo packageInfo = packageManager.getPackageArchiveInfo(apkPath, PackageManager.GET_ACTIVITIES);
		if (packageInfo != null)
		{
			ApplicationInfo applicationInfo = packageInfo.applicationInfo;
			/* 必须加这两句，不然下面icon获取是default icon而不是应用包的icon */
			applicationInfo.sourceDir = apkPath;
			applicationInfo.publicSourceDir = apkPath;
			String appName = packageManager.getApplicationLabel(applicationInfo).toString();// 得到应用名
			//			String packageName = applicationInfo.packageName; // 得到包名
			//			String version = packageInfo.versionName; // 得到版本信息
			/* icon1和icon2其实是一样的 */
			Drawable icon1 = packageManager.getApplicationIcon(applicationInfo);// 得到图标信息
			Drawable icon2 = applicationInfo.loadIcon(packageManager);
			Bitmap bitmap = null;
			try
			{
				bitmap = ((BitmapDrawable) icon2).getBitmap();
			}
			catch (OutOfMemoryError e)
			{
				LogTools.error("ApkIconLoader", e.toString());
			}
			appInfo = new AppInfo(apkPath, applicationInfo.uid + "", appName, null, applicationInfo.packageName, null, packageInfo.versionName, packageInfo.versionCode, bitmap, AppType.USER.getType());
		}
		return appInfo;
	}
}
