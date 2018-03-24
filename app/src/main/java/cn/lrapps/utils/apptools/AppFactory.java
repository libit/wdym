/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.utils.apptools;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import cn.lrapps.models.AppInfo;
import com.weiduyx.wdym.models.ReturnInfo;

import java.io.File;
import java.util.List;

/**
 * Created by libit on 15/8/19.
 */
public abstract class AppFactory
{
	private static AppFactory appInstance;
	protected final IAppService appService;

	protected AppFactory()
	{
		appService = new IAppServiceUnRootImpl();
	}

	synchronized public static AppFactory getInstance()
	{
		if (appInstance == null)
		{
			if (AppFactory.isCompatible(22))
			{
				appInstance = new AppUtil22();
			}
			else
			{
				appInstance = new AppUtil8();
			}
		}
		return appInstance;
	}

	/**
	 * 判断设备API是否兼容指定的版本号
	 *
	 * @param apiLevel 指定的版本号
	 *
	 * @return 兼容返回true，否则返回false
	 */
	public static boolean isCompatible(int apiLevel)
	{
		return android.os.Build.VERSION.SDK_INT >= apiLevel;
	}

	/**
	 * 安装App
	 *
	 * @param file 安装包
	 * @param root 是否以root执行
	 */
	public abstract ReturnInfo installApp(File file, boolean root);

	/**
	 * 卸载App
	 *
	 * @param packageName App包名
	 * @param root        是否以root执行
	 */
	public abstract ReturnInfo uninstallApp(String packageName, boolean root);

	/**
	 * 获取App列表
	 *
	 * @param type App类型
	 *
	 * @return App列表
	 */
	public abstract List<AppInfo> getApps(int type);

	/**
	 * 获取App信息
	 *
	 * @param packageName App的packageName
	 * @param showPhoto   是否显示图片
	 *
	 * @return App对象
	 */
	public abstract AppInfo getAppInfoByPackageName(String packageName, boolean showPhoto);

	/**
	 * 获取APP的图标
	 *
	 * @param packageName
	 *
	 * @return
	 */
	public abstract Drawable getAppIconByPackageName(String packageName);

	/**
	 * 获取自身App包信息
	 *
	 * @return App对象
	 */
	public abstract PackageInfo getSelfPackageInfo() throws PackageManager.NameNotFoundException;

	/**
	 * 启动应用程序
	 *
	 * @param context     上下文
	 * @param packageName 应用包名
	 *
	 * @return 启动成功返回true，失败返回false
	 */
	public abstract ReturnInfo startApp(Context context, String packageName);

	/**
	 * 获取正在运行的程序列表
	 *
	 * @return App列表
	 */
	public abstract List<AppInfo> getRunningApps();

	/**
	 * 获取apk安装包的信息
	 *
	 * @param apkFile apk文件
	 *
	 * @return
	 */
	public abstract AppInfo getApkInfo(File apkFile);

	/**
	 * 获取apk安装包的信息
	 *
	 * @param apkPath apk路径
	 *
	 * @return
	 */
	public abstract AppInfo getApkInfo(String apkPath);
}
