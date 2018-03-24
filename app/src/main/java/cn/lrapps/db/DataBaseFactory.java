/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import cn.lrapps.models.AppInfo;

/**
 * Created by libit on 15/8/26.
 */
public class DataBaseFactory
{
	public static class DBHelper extends SQLiteOpenHelper
	{
		private static final int DATABASE_VERSION = 2;
		private static final String[] TABLES = new String[]{  AppInfo.getCreateTableSQL()};

		DBHelper(Context context)
		{
			super(context, DbConstant.AUTHORITY, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db)
		{
			int count = TABLES.length;
			for (int i = 0; i < count; i++)
			{
				db.execSQL(TABLES[i]);
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
		{
			//			if (oldVersion < 2)
			//			{
			//				db.execSQL("DROP TABLE IF EXISTS " + DbConstant.TABLE_NAME_APP_INFO);
			//				db.execSQL("DROP TABLE IF EXISTS " + DbConstant.TABLE_NAME_SEND_LOG_INFO);
			//			}
			onCreate(db);
		}
	}
}
