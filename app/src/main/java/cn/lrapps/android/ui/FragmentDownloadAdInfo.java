/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.android.ui;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.callback.AjaxStatus;
import com.external.xlistview.XListView;
import com.squareup.picasso.Picasso;
import com.weiduyx.wdym.R;
import com.weiduyx.wdym.models.AdInfo;
import com.weiduyx.wdym.models.ReturnInfo;
import com.weiduyx.wdym.services.AdService;
import com.weiduyx.wdym.services.ApiConfig;
import com.weiduyx.wdym.services.IAjaxDataResponse;

import org.greenrobot.eventbus.EventBus;

import cn.lrapps.utils.ConstValues;
import cn.lrapps.utils.GsonTools;
import cn.lrapps.utils.viewtools.DisplayTools;

public class FragmentDownloadAdInfo extends MyBasePageFragment implements IAjaxDataResponse, View.OnClickListener
{
	private static final String TAG = FragmentDownloadAdInfo.class.getSimpleName();
	private View headView;
	private ImageView ivPhoto;
	private TextView tvName, tvContent;
	private String mAdId;
	private AdInfo mAdInfo;
	private AdService mAdService;

	public FragmentDownloadAdInfo()
	{
	}

	public static FragmentDownloadAdInfo newInstance()
	{
		return new FragmentDownloadAdInfo();
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		if (getArguments() != null)
		{
			mAdId = getArguments().getString(ConstValues.DATA_AD_ID);
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
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View rootView = inflater.inflate(R.layout.fragment_download_ad_info, container, false);
		mAdService = new AdService(this.getContext());
		mAdService.addDataResponse(this);
		viewInit(rootView);
		return rootView;
	}

	@Override
	public void onDestroyView()
	{
		if (EventBus.getDefault().isRegistered(this))
		{
			EventBus.getDefault().unregister(this);
		}
		super.onDestroyView();
	}

	@Override
	public void onAttach(Activity activity)
	{
		super.onAttach(activity);
		this.mActivity = activity;
	}

	@Override
	public void onDetach()
	{
		super.onDetach();
		this.mActivity = null;
	}

	@Override
	public void onResume()
	{
		super.onResume();
		onRefresh();
	}

	@Override
	protected void viewInit(View rootView)
	{
		xListView = (XListView) rootView.findViewById(R.id.xlist);
		headView = LayoutInflater.from(this.getContext()).inflate(R.layout.fragment_download_ad_info_head, null);
		xListView.setPullRefreshEnable(true);
		xListView.setPullLoadEnable(false);
		xListView.addHeaderView(headView);
		xListView.setAdapter(null);
		xListView.setXListViewListener(this);
		ivPhoto = (ImageView) rootView.findViewById(R.id.iv_pic);
		ivPhoto.setOnClickListener(this);
		tvName = (TextView) rootView.findViewById(R.id.tv_name);
		tvContent = (TextView) rootView.findViewById(R.id.tv_content);
		rootView.findViewById(R.id.btn_download).setOnClickListener(this);
		super.viewInit(rootView);
	}

	//刷新数据
	@Override
	synchronized public void refreshData()
	{
		mAdService.getAdInfo(mAdId, "", true);
		loadMoreData();
	}

	//加载更多
	@Override
	synchronized public void loadMoreData()
	{
		xListView.stopRefresh();
		xListView.stopLoadMore();
		xListView.setPullLoadEnable(false);
	}

	@Override
	public void onClick(View v)
	{
		final Context context = FragmentDownloadAdInfo.this.getContext();
		switch (v.getId())
		{
			case R.id.btn_download:
			{
				ActivityWebView.startWebActivity(context, mAdInfo.getName(), mAdInfo.getUrl());
				break;
			}
		}
	}

	@Override
	public boolean onAjaxDataResponse(String url, String result, AjaxStatus status)
	{
		xListView.stopRefresh();
		xListView.stopLoadMore();
		if (url.endsWith(ApiConfig.GET_AD_INFO))
		{
			ReturnInfo returnInfo = GsonTools.getReturnInfo(result);
			if (ReturnInfo.isSuccess(returnInfo))
			{
				mAdInfo = GsonTools.getReturnObject(returnInfo, AdInfo.class);
				if (mAdInfo != null)
				{
					tvName.setText(mAdInfo.getName());
					tvContent.setText(mAdInfo.getContent());
					int width = DisplayTools.getWindowWidth(this.getContext()) / 6;
					Picasso.with(this.getContext()).load(Uri.parse(ApiConfig.getServerPicUrl(mAdInfo.getPicUrl()))).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).resize(width, width).into(ivPhoto);
				}
			}
		}
		return true;
	}
}
