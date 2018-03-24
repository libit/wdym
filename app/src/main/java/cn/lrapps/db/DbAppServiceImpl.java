/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.db;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

import cn.lrapps.android.ui.MyApplication;
import cn.lrapps.models.AppInfo;
import cn.lrapps.utils.LogTools;

/**
 * Created by libit on 15/8/31.
 */
public class DbAppServiceImpl implements DbAppService
{
	private static final String TAG = DbAppFactory.class.getSimpleName();
	private static final String TABLE_NAME = DbConstant.TABLE_NAME_APP_INFO;

	private ContentResolver getContextResolver()
	{
		Context context = MyApplication.getContext();
		if (context == null)
		{
			return null;
		}
		ContentResolver contentResolver = context.getContentResolver();
		if (contentResolver == null)
		{
			return null;
		}
		return contentResolver;
	}

	private Uri getUri()
	{
		return DbConstant.getTableUri(TABLE_NAME);
	}

	/**
	 * 添加App
	 *
	 * @param appInfo App对象
	 *
	 * @return 成功：true，失败：false
	 */
	@Override
	public boolean add(AppInfo appInfo)
	{
		if (appInfo == null)
		{
			return false;
		}
		ContentResolver contentResolver = getContextResolver();
		if (contentResolver == null)
		{
			return false;
		}
		Uri uri = contentResolver.insert(getUri(), appInfo.getObjectContentValues());
		return uri != null;
	}

	/**
	 * 更新App
	 *
	 * @param appInfo App对象
	 *
	 * @return 成功：true，失败：false
	 */
	@Override
	public boolean update(AppInfo appInfo)
	{
		if (appInfo == null)
		{
			return false;
		}
		ContentResolver contentResolver = getContextResolver();
		if (contentResolver == null)
		{
			return false;
		}
		int rows = contentResolver.update(getUri(), appInfo.getObjectContentValues(), AppInfo.FIELD_PACKAGE_NAME + " = ?", new String[]{appInfo.getPackageName()});
		return rows > 0;
	}

	/**
	 * 添加或更新App
	 *
	 * @param appInfo App对象
	 *
	 * @return 成功：true，失败：false
	 */
	@Override
	public boolean addOrUpdate(AppInfo appInfo)
	{
		if (appInfo == null)
		{
			return false;
		}
		ContentResolver contentResolver = getContextResolver();
		if (contentResolver == null)
		{
			return false;
		}
		ContentValues values = appInfo.getObjectContentValues();
		int rows = contentResolver.update(getUri(), values, AppInfo.FIELD_PACKAGE_NAME + " = ?", new String[]{appInfo.getPackageName()});
		if (rows < 1)
		{
			Uri uri = contentResolver.insert(getUri(), values);
			return uri != null;
		}
		return true;
	}

	/**
	 * 增加或更新App列表
	 *
	 * @param appInfoList App列表
	 *
	 * @return 增加成功的个数
	 */
	@Override
	public int addOrUpdateList(List<AppInfo> appInfoList)
	{
		int count = 0;
		if (appInfoList == null || appInfoList.size() < 1)
		{
			return count;
		}
		ContentResolver contentResolver = getContextResolver();
		if (contentResolver == null)
		{
			return 0;
		}
		final Uri tableUri = getUri();
		final String where = AppInfo.FIELD_PACKAGE_NAME + " = ?";
		for (AppInfo appInfo : appInfoList)
		{
			//            AppConfig.showLog(TAG + " addList", "当前App：" + appInfo.toString());
			ContentValues values = appInfo.getObjectContentValues();
			int rows = contentResolver.update(tableUri, values, where, new String[]{appInfo.getPackageName()});
			if (rows < 1)
			{
				Uri uri = contentResolver.insert(tableUri, values);
				if (uri != null)
					count++;
			}
		}
		return count;
	}

	/**
	 * 删除App
	 *
	 * @param packageName App包名
	 *
	 * @return 成功：true，失败：false
	 */
	@Override
	public boolean delete(String packageName)
	{
		ContentResolver contentResolver = getContextResolver();
		if (contentResolver == null)
		{
			return false;
		}
		int rows = contentResolver.delete(getUri(), AppInfo.FIELD_PACKAGE_NAME + " = ?", new String[]{packageName});
		return rows > 0;
	}

	/**
	 * 清空App列表
	 */
	@Override
	public int deleteAll()
	{
		ContentResolver contentResolver = getContextResolver();
		if (contentResolver == null)
		{
			return 0;
		}
		int rows = contentResolver.delete(getUri(), null, null);
		LogTools.debug(TAG + " deleteAll", "清空数据库条数：" + rows);
		return rows;
	}

	/**
	 * 获取指定的App
	 *
	 * @param packageName App包名
	 *
	 * @return AppInfo
	 */
	@Override
	public AppInfo getAppInfo(String packageName)
	{
		AppInfo appInfo = null;
		ContentResolver contentResolver = getContextResolver();
		if (contentResolver == null)
		{
			return null;
		}
		Cursor cursor = contentResolver.query(getUri(), null, AppInfo.FIELD_PACKAGE_NAME + " = ?", new String[]{packageName}, null);
		if (cursor != null)
		{
			if (cursor.moveToFirst())
			{
				appInfo = AppInfo.getObjectFromDb(cursor);
				if (appInfo != null)
				{
					LogTools.debug(TAG + " query", "packageName:" + packageName + ",APP信息：" + appInfo.toString());
				}
			}
			cursor.close();
		}
		return appInfo;
	}

	/**
	 * 获取App列表
	 *
	 * @param appType App类型
	 *
	 * @return
	 */
	@Override
	public List<AppInfo> getAppInfoList(Integer appType, String orderCol, String orderType)
	{
		List<AppInfo> appInfoList = new ArrayList<>();
		String condition = "";
		List<String> params = new ArrayList<>();
		if (appType != null)
		{
			condition += AppInfo.FIELD_TYPE + " = ?";
			params.add(appType + "");
		}
		String[] args = null;
		int size = params.size();
		if (size > 0)
		{
			args = new String[size];
			for (int i = 0; i < size; i++)
			{
				args[i] = params.get(i);
			}
		}
		ContentResolver contentResolver = getContextResolver();
		if (contentResolver == null)
		{
			return appInfoList;
		}
		Cursor cursor = contentResolver.query(getUri(), null, condition, args, orderCol + " " + orderType);
		if (cursor != null)
		{
			if (cursor.moveToFirst())
			{
				do
				{
					AppInfo appInfo = AppInfo.getObjectFromDb(cursor);
					if (appInfo != null)
					{
						appInfoList.add(appInfo);
					}
				}
				while (cursor.moveToNext());
			}
			cursor.close();
		}
		return appInfoList;
	}

	/**
	 * 获取App列表数量
	 *
	 * @param appType App类型
	 *
	 * @return
	 */
	@Override
	public int getAppInfoListCount(Integer appType)
	{
		List<String> list = new ArrayList<>();
		String condition = "";
		List<String> params = new ArrayList<>();
		if (appType != null)
		{
			condition += AppInfo.FIELD_TYPE + " = ?";
			params.add(appType + "");
		}
		String[] args = null;
		int size = params.size();
		if (size > 0)
		{
			args = new String[size];
			for (int i = 0; i < size; i++)
			{
				args[i] = params.get(i);
			}
		}
		ContentResolver contentResolver = getContextResolver();
		if (contentResolver == null)
		{
			return 0;
		}
		Cursor cursor = contentResolver.query(getUri(), new String[]{AppInfo.FIELD_PACKAGE_NAME}, condition, args, AppInfo.FIELD_NAME_LABEL + " ASC");
		if (cursor != null)
		{
			if (cursor.moveToFirst())
			{
				do
				{
					String packageName = cursor.getString(0);
					int isHide = cursor.getInt(1);
					if (packageName != null)
					{
						list.add(packageName);
					}
				}
				while (cursor.moveToNext());
			}
			cursor.close();
		}
		return list.size();
	}
}
