/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.utils.apptools;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import cn.lrapps.android.ui.MyApplication;
import cn.lrapps.utils.StringTools;
import com.weiduyx.wdym.models.ErrorInfo;
import com.weiduyx.wdym.models.ReturnInfo;

import java.io.File;

/**
 * Created by libit on 15/9/29.
 */
public class IAppServiceUnRootImpl extends IAppService
{
	/**
	 * 安装App
	 *
	 * @param file 安装包
	 */
	public ReturnInfo installApp(File file)
	{
		if (file == null)
		{
			return new ReturnInfo(ErrorInfo.PARAM_ERROR, "安装文件不能为空");
		}
		Intent intent = new Intent(Intent.ACTION_VIEW);
		Uri data;
		if (AppFactory.isCompatible(Build.VERSION_CODES.N))
		{
			// "com.weiduyx.wdym.fileprovider"即是在清单文件中配置的authorities
			data = FileProvider.getUriForFile(MyApplication.getContext(), "com.weiduyx.wdym.fileprovider", file);
			// 给目标应用一个临时授权
			intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
		}
		else
		{
			data = Uri.fromFile(file);
		}
		intent.setDataAndType(data, "application/vnd.android.package-archive");
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		MyApplication.getContext().startActivity(intent);
		return new ReturnInfo(ErrorInfo.SUCCESS, "安装应用成功");
	}

	/**
	 * 卸载App
	 *
	 * @param packageName App包名
	 */
	public ReturnInfo uninstallApp(String packageName)
	{
		if (StringTools.isNull(packageName))
		{
			return new ReturnInfo(ErrorInfo.PARAM_ERROR, "包名不能为空");
		}
		Uri uri = Uri.parse("package:" + packageName);
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_DELETE);
		intent.setData(uri);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		MyApplication.getContext().startActivity(intent);
		return new ReturnInfo(ErrorInfo.SUCCESS, "卸载应用成功");
	}
}
