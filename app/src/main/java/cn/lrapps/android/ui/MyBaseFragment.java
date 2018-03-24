/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.android.ui;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by libit on 15/9/29.
 */
public abstract class MyBaseFragment extends Fragment
{
	private static final String TAG = MyBaseFragment.class.getSimpleName();
	protected Activity mActivity;
	protected boolean isInit = false;//视图是否已经初始化

	protected void viewInit(View rootView)
	{
		isInit = true;
	}

	public abstract Activity getAttachedActivity();

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser)
	{
		super.setUserVisibleHint(isVisibleToUser);
		//		LogTools.debug(TAG, "fragment setUserVisibleHint:" + isVisibleToUser);
		if (isInit)
		{
			if (isVisibleToUser)
			{
				fragmentShow();
			}
			else
			{
				fragmentHide();
			}
		}
	}

	/**
	 * 当fragment隐藏时调用
	 * 注意：第一次调用时必须等待视图已经初始化完成。
	 */
	public void fragmentHide()
	{
	}

	/**
	 * 当fragment显示时调用
	 * 注意：第一次调用时必须等待视图已经初始化完成。
	 */
	public void fragmentShow()
	{
	}
}
