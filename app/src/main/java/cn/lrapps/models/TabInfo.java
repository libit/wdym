/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.models;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by libit on 16/5/8.
 */
public class TabInfo
{
	private int index;
	private String label;
	private int imgResId;
	private Class loadClass;
	private ImageView imgIcon;
	private TextView viewNotificationMark;
	private TextView tvLabel;

	public TabInfo()
	{
	}

	public TabInfo(int index, String label, Class loadClass)
	{
		this.index = index;
		this.label = label;
		this.loadClass = loadClass;
	}

	public TabInfo(int index, String label, int imgResId, Class loadClass)
	{
		this.index = index;
		this.label = label;
		this.imgResId = imgResId;
		this.loadClass = loadClass;
	}

	public int getIndex()
	{
		return index;
	}

	public void setIndex(int index)
	{
		this.index = index;
	}

	public String getLabel()
	{
		return label;
	}

	public void setLabel(String label)
	{
		this.label = label;
	}

	public int getImgResId()
	{
		return imgResId;
	}

	public void setImgResId(int imgResId)
	{
		this.imgResId = imgResId;
	}

	public Class getLoadClass()
	{
		return loadClass;
	}

	public void setLoadClass(Class loadClass)
	{
		this.loadClass = loadClass;
	}

	public ImageView getImgIcon()
	{
		return imgIcon;
	}

	public void setImgIcon(ImageView imgIcon)
	{
		this.imgIcon = imgIcon;
	}

	public TextView getViewNotificationMark()
	{
		return viewNotificationMark;
	}

	public void setViewNotificationMark(TextView viewNotificationMark)
	{
		this.viewNotificationMark = viewNotificationMark;
	}

	public TextView getTvLabel()
	{
		return tvLabel;
	}

	public void setTvLabel(TextView tvLabel)
	{
		this.tvLabel = tvLabel;
	}
}
