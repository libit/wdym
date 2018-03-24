/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.android.ui;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.tencent.smtt.sdk.QbSdk;

import cn.lrapps.utils.LogTools;

/**
 * Created by libit on 15/8/19.
 */
public class MyApplication extends Application
{
	private static MyApplication instance;

	public static MyApplication getInstance()
	{
		return instance;
	}

	public static Context getContext()
	{
		return getInstance();
	}

	@Override
	public void onCreate()
	{
		super.onCreate();
		instance = this;
		new Thread("startLogService")
		{
			@Override
			public void run()
			{
				super.run();
				LogTools.getInstance().start();
			}
		}.start();
		//搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
		QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback()
		{
			@Override
			public void onViewInitFinished(boolean arg0)
			{
				//x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
				LogTools.info("app", " onViewInitFinished is " + arg0);
			}

			@Override
			public void onCoreInitFinished()
			{
				LogTools.info("app", "QQ浏览器内核加载成功");
			}
		};
		//x5内核初始化接口
		QbSdk.initX5Environment(getApplicationContext(), cb);
	}

	@Override
	public void onTerminate()
	{
		LogTools.getInstance().stop();
		instance = null;
		super.onTerminate();
	}

	public void backToHome()
	{
		try
		{
			startActivity(new Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_HOME).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
