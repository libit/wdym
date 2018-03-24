/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.android.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.androidquery.callback.AjaxStatus;
import cn.lrapps.android.ui.customer.ToastView;
import cn.lrapps.utils.ConstValues;
import cn.lrapps.utils.GsonTools;
import com.weiduyx.wdym.models.NewsInfo;
import com.weiduyx.wdym.models.ReturnInfo;
import com.weiduyx.wdym.services.ApiConfig;
import com.weiduyx.wdym.services.NewsService;
import com.weiduyx.wdym.R;

public class FragmentNewsWeb extends MyBaseFragmentWeb
{
	protected static final String TAG = FragmentNewsWeb.class.getSimpleName();
	private NewsService mNewsService;
	private String mNewsId;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		if (getArguments() != null)
		{
			mNewsId = getArguments().getString(ConstValues.DATA_NEWS_ID);
		}
	}

	@Override
	public Activity getAttachedActivity()
	{
		if (mActivity != null)
		{
			return mActivity;
		}
		else
		{
			return this.getActivity();
		}
	}

	@Override
	protected void initService()
	{
		mNewsService = new NewsService(this.getContext());
		mNewsService.addDataResponse(this);
	}

	@Override
	protected void refreshData()
	{
		mNewsService.getNewsInfo(mNewsId, null, true);
	}

	@Override
	public boolean onAjaxDataResponse(String url, String result, AjaxStatus status)
	{
		if (url.endsWith(ApiConfig.GET_NEWS_INFO))
		{
			ReturnInfo returnInfo = GsonTools.getReturnInfo(result);
			if (ReturnInfo.isSuccess(returnInfo))
			{
				NewsInfo newsInfo = GsonTools.getReturnObject(returnInfo, NewsInfo.class);
				if (newsInfo != null)
				{
					getActivity().setTitle(newsInfo.getTitle());
					loadWebData(newsInfo.getContent());
				}
			}
			else
			{
				String msg = result;
				if (returnInfo != null)
				{
					msg = returnInfo.getMsg();
				}
				ToastView.showCenterToast(this.getContext(), R.drawable.ic_do_fail, msg);
			}
			return true;
		}
		return false;
	}
}
