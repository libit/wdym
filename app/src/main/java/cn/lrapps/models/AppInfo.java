/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.models;

import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;
import cn.lrapps.db.DbConstant;
import cn.lrapps.utils.PinyinTools;
import cn.lrapps.utils.StringTools;

import java.util.Comparator;

/**
 * Created by libit on 15/8/19.
 */
public class AppInfo extends DbObject implements Comparator<AppInfo>
{
	public static final String FIELD_UID = "uid";
	public static final String FIELD_NAME = "name";
	public static final String FIELD_NAME_LABEL = "name_label";
	public static final String FIELD_PACKAGE_NAME = "package_name";
	public static final String FIELD_LAUNCH_CLASS = "launch_class";
	public static final String FIELD_VERSION_NAME = "version_name";
	public static final String FIELD_VERSION_CODE = "version_code";
	//    public static final String FIELD_PHOTO = "photo";
	public static final String FIELD_TYPE = "type";
	@SerializedName("id")
	private String id;// 主键
	@SerializedName("uid")
	private String uid;// 程序的用户ID，不同的程序ID可能一样
	@SerializedName("name")
	private String name;// 程序名称
	@SerializedName("nameLabel")
	private String nameLabel;// 程序名称的拼音，用户检索
	@SerializedName("packageName")
	private String packageName;// 包名，唯一标识
	@SerializedName("launchClassName")
	private String launchClassName;// 启动的class
	@SerializedName("versionName")
	private String versionName;// 版本号
	@SerializedName("versionCode")
	private Integer versionCode;// 版本代号
	@SerializedName("photo")
	private Bitmap photo;// 程序图片
	@SerializedName("type")
	private int type;// 程序类型（系统程序或用户程序）

	public AppInfo()
	{
	}

	public AppInfo(String id, String uid, String name, String nameLabel, String packageName, String launchClassName, String versionName, Integer versionCode, Bitmap photo, int type)
	{
		this.id = id;
		this.uid = uid;
		this.name = name;
		this.nameLabel = nameLabel;
		this.packageName = packageName;
		this.launchClassName = launchClassName;
		this.versionName = versionName;
		this.versionCode = versionCode;
		this.photo = photo;
		this.type = type;
	}

	/**
	 * 获取创建表的SQL语句
	 *
	 * @return SQL语句
	 */
	public static String getCreateTableSQL()
	{
		// App信息表
		return String.format("CREATE TABLE IF NOT EXISTS %s (%s INTEGER PRIMARY KEY AUTOINCREMENT,%s TEXT NOT NULL,%s TEXT NOT NULL,%s TEXT NOT NULL,%s TEXT NOT NULL UNIQUE,%s TEXT,%s TEXT,%s INTEGER,%s INTEGER NOT NULL);", DbConstant.TABLE_NAME_APP_INFO, FIELD_ID, AppInfo.FIELD_UID, AppInfo.FIELD_NAME, AppInfo.FIELD_NAME_LABEL, AppInfo.FIELD_PACKAGE_NAME, AppInfo.FIELD_LAUNCH_CLASS, AppInfo.FIELD_VERSION_NAME, AppInfo.FIELD_VERSION_CODE, AppInfo.FIELD_TYPE);
	}

	/**
	 * 从数据库中取出对象
	 *
	 * @param cursor
	 *
	 * @return
	 */
	public static AppInfo getObjectFromDb(Cursor cursor)
	{
		AppInfo appInfo = new AppInfo();
		appInfo.setId(cursor.getString(cursor.getColumnIndex(FIELD_ID)));
		appInfo.setUid(cursor.getString(cursor.getColumnIndex(FIELD_UID)));
		appInfo.setName(cursor.getString(cursor.getColumnIndex(FIELD_NAME)));
		appInfo.setNameLabel(cursor.getString(cursor.getColumnIndex(FIELD_NAME_LABEL)));
		appInfo.setPackageName(cursor.getString(cursor.getColumnIndex(FIELD_PACKAGE_NAME)));
		appInfo.setLaunchClassName(cursor.getString(cursor.getColumnIndex(FIELD_LAUNCH_CLASS)));
		appInfo.setVersionName(cursor.getString(cursor.getColumnIndex(FIELD_VERSION_NAME)));
		appInfo.setVersionCode(cursor.getInt(cursor.getColumnIndex(FIELD_VERSION_CODE)));
		appInfo.setType(cursor.getInt(cursor.getColumnIndex(FIELD_TYPE)));
		return appInfo;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getUid()
	{
		return uid;
	}

	public void setUid(String uid)
	{
		this.uid = uid;
	}

	public String getPackageName()
	{
		return packageName;
	}

	public void setPackageName(String packageName)
	{
		this.packageName = packageName;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getNameLabel()
	{
		return nameLabel;
	}

	public void setNameLabel(String nameLabel)
	{
		this.nameLabel = nameLabel;
	}

	public String getLaunchClassName()
	{
		return launchClassName;
	}

	public void setLaunchClassName(String launchClassName)
	{
		this.launchClassName = launchClassName;
	}

	public String getVersionName()
	{
		return versionName;
	}

	public void setVersionName(String versionName)
	{
		this.versionName = versionName;
	}

	public Integer getVersionCode()
	{
		return versionCode;
	}

	public void setVersionCode(Integer versionCode)
	{
		this.versionCode = versionCode;
	}

	public Bitmap getPhoto()
	{
		return photo;
	}

	public void setPhoto(Bitmap photo)
	{
		this.photo = photo;
	}

	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
	}

	/**
	 * 转换成数据库存储的数据
	 *
	 * @return ContentValues
	 */
	public ContentValues getObjectContentValues()
	{
		ContentValues contentValues = new ContentValues();
		if (id != null)
		{
			contentValues.put(FIELD_ID, id);
		}
		contentValues.put(AppInfo.FIELD_UID, uid);
		contentValues.put(AppInfo.FIELD_NAME, name);
		if (StringTools.isNull(nameLabel))
		{
			nameLabel = PinyinTools.Chinese2Pinyin(name);
		}
		contentValues.put(AppInfo.FIELD_NAME_LABEL, nameLabel);
		contentValues.put(AppInfo.FIELD_PACKAGE_NAME, packageName);
		if (!StringTools.isNull(getLaunchClassName()))
		{
			contentValues.put(AppInfo.FIELD_LAUNCH_CLASS, launchClassName);
		}
		contentValues.put(AppInfo.FIELD_VERSION_NAME, versionName);
		contentValues.put(AppInfo.FIELD_VERSION_CODE, versionCode);
		//        if (photo != null)
		//        {
		//            ByteArrayOutputStream os = new ByteArrayOutputStream();
		//            photo.compress(Bitmap.CompressFormat.PNG, 100, os);
		//            contentValues.put(AppInfo.FIELD_PHOTO, os.toByteArray());
		//        }
		contentValues.put(AppInfo.FIELD_TYPE, type);
		return contentValues;
	}

	@Override
	public int compare(AppInfo lhs, AppInfo rhs)
	{
		if (lhs == null && rhs == null)
		{
			return 0;
		}
		if (lhs == null)
		{
			return -1;
		}
		if (rhs == null)
		{
			return -1;
		}
		if (lhs == rhs)
		{
			return 0;
		}
		String lName = PinyinTools.Chinese2Pinyin(lhs.getName());
		String rName = PinyinTools.Chinese2Pinyin(rhs.getName());
		return lName.compareToIgnoreCase(rName);
	}
}
