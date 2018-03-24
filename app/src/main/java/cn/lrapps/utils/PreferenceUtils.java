/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.utils;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import cn.lrapps.android.ui.MyApplication;
import cn.lrapps.enums.LogLevel;

import java.util.HashMap;
import java.util.Map;

public class PreferenceUtils
{
	private static final String TAG = PreferenceUtils.class.getName();
	public static final String PREF_USER_ID = "pref_username_key";//账号
	public static final String PREF_SESSION_ID = "pref_session_id_key";//登录Session ID
	public static final String PREF_CRASH_FILE_NAME = "PREF_CRASH_FILE";//崩溃的日志文件名
	public static final String LOGCAT_LEVEL = "logcat_level";//日志记录级别
	public static final String LOGCAT_AUTO_UPDATE = "logcat_auto_update";//是否自动提交日志
	public static final String IS_FIRST_RUN = "is_first_run";//是否第一次运行，以便于做一些初始化工作
	public static final String IS_SHOW_NOTIFICATION = "is_show_notification";//是否显示状态栏
	public static final String IS_BOOT_START = "is_boot_start";//是否开机启动
	public static final String IS_DEBUG = "is_debug";//是否调试
	public static final String START_PIC_URL = "start_pic_url";//开屏图片
	private final static HashMap<String, String> STRING_PREFS = new HashMap<String, String>()
	{
		private static final long serialVersionUID = 1L;

		{
			put(PREF_USER_ID, "");
			put(PREF_SESSION_ID, "");
			put(PREF_CRASH_FILE_NAME, "");
			put(LOGCAT_LEVEL, LogLevel.LEVEL_3.getLevel() + "");
			put(START_PIC_URL, "");
		}
	};
	private final static HashMap<String, Boolean> BOOLEAN_PREFS = new HashMap<String, Boolean>()
	{
		private static final long serialVersionUID = 1L;

		{
			put(LOGCAT_AUTO_UPDATE, true);
			put(IS_FIRST_RUN, true);
			put(IS_SHOW_NOTIFICATION, true);
			put(IS_BOOT_START, true);
			put(IS_DEBUG, true);
		}
	};
	protected static PreferenceUtils instance = null;
	private final SharedPreferences prefs;

	protected PreferenceUtils()
	{
		prefs = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext());
	}

	synchronized public static PreferenceUtils getInstance()
	{
		if (instance == null)
		{
			instance = new PreferenceUtils();
		}
		return instance;
	}

	private static String gPrefStringValue(SharedPreferences aPrefs, String key)
	{
		if (STRING_PREFS.containsKey(key))
		{
			return aPrefs.getString(key, STRING_PREFS.get(key));
		}
		return "";
	}

	private static Boolean gPrefBooleanValue(SharedPreferences aPrefs, String key)
	{
		if (BOOLEAN_PREFS.containsKey(key))
		{
			return aPrefs.getBoolean(key, BOOLEAN_PREFS.get(key));
		}
		return false;
	}

	public String getUserId()
	{
		return getStringValue(PREF_USER_ID);
	}

	public boolean setUserId(String value)
	{
		return setStringValue(PREF_USER_ID, value);
	}

	public String getSessionId()
	{
		return getStringValue(PREF_SESSION_ID);
	}

	public boolean setSessionId(String value)
	{
		return setStringValue(PREF_SESSION_ID, value);
	}

	synchronized public boolean setStringValue(String key, String value)
	{
		Editor editor = prefs.edit();
		editor.putString(key, value);
		return editor.commit();
	}

	public String getStringValue(String key)
	{
		return gPrefStringValue(prefs, key);
	}

	synchronized public void setBooleanValue(String key, Boolean value)
	{
		Editor editor = prefs.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}

	public Boolean getBooleanValue(String key)
	{
		return gPrefBooleanValue(prefs, key);
	}

	public int getIntegerValue(String key)
	{
		try
		{
			return Integer.parseInt(getStringValue(key));
		}
		catch (NumberFormatException e)
		{
			LogTools.error(TAG, "Invalid " + key + " format : expect a int");
		}
		return Integer.parseInt(STRING_PREFS.get(key));
	}

	/**
	 * Set all values to default
	 */
	public void resetAllDefaultValues()
	{
		for (String key : STRING_PREFS.keySet())
		{
			setStringValue(key, STRING_PREFS.get(key));
		}
		for (String key : BOOLEAN_PREFS.keySet())
		{
			setBooleanValue(key, BOOLEAN_PREFS.get(key));
		}
	}

	/**
	 * 备份数据
	 *
	 * @return
	 */
	public String backup()
	{
		Map<String, String> map = new HashMap<>();
		for (String key : STRING_PREFS.keySet())
		{
			map.put(key, getStringValue(key));
		}
		for (String key : BOOLEAN_PREFS.keySet())
		{
			setBooleanValue(key, BOOLEAN_PREFS.get(key));
			map.put(key, getBooleanValue(key).toString());
		}
		return GsonTools.toJson(map);
	}
}
