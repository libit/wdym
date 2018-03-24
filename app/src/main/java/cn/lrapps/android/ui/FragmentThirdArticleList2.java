/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.android.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.callback.AjaxStatus;
import com.external.xlistview.XListView;
import com.google.gson.reflect.TypeToken;
import com.weiduyx.wdym.R;
import com.weiduyx.wdym.models.AdInfo;
import com.weiduyx.wdym.models.TableData;
import com.weiduyx.wdym.models.ThirdArticleInfo;
import com.weiduyx.wdym.services.ApiConfig;
import com.weiduyx.wdym.services.IAjaxDataResponse;
import com.weiduyx.wdym.services.ThirdArticleInfoService;
import com.weiduyx.wdym.services.UserRedpackService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import cn.lrapps.android.ui.adapter.ThirdArticleAdapter;
import cn.lrapps.enums.UserEvent;
import cn.lrapps.utils.ConstValues;
import cn.lrapps.utils.GsonTools;

public class FragmentThirdArticleList2 extends MyBasePageFragment implements IAjaxDataResponse, View.OnClickListener
{
	private static final String TAG = FragmentThirdArticleList2.class.getSimpleName();
	private View vTitle;
	private TextView tvTitle;
	private ImageView ivRight;
	private ThirdArticleInfoService mThirdArticleInfoService;
	private UserRedpackService mUserRedpackService;
	private List<ThirdArticleInfo> mThirdArticleInfoList = new ArrayList<>();
	private ThirdArticleAdapter mThirdArticleAdapter;
	private String mTypeId;
	private List<AdInfo> mAdInfoList = new ArrayList<>();
	private int netAdIndex = 0;

	public FragmentThirdArticleList2()
	{
	}

	public static FragmentThirdArticleList2 newInstance(String typeId)
	{
		FragmentThirdArticleList2 fragment = new FragmentThirdArticleList2();
		Bundle args = new Bundle();
		args.putString(ConstValues.DATA_TYPE_ID, typeId);
		fragment.setArguments(args);
		return fragment;
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
	public void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		if (getArguments() != null)
		{
			mTypeId = getArguments().getString(ConstValues.DATA_TYPE_ID);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View rootView = inflater.inflate(R.layout.fragment_third_article_list2, container, false);
		mThirdArticleInfoService = new ThirdArticleInfoService(this.getContext());
		mThirdArticleInfoService.addDataResponse(this);
		mUserRedpackService = new UserRedpackService(this.getContext());
		mUserRedpackService.addDataResponse(this);
		viewInit(rootView);
		if (!EventBus.getDefault().isRegistered(this))
		{
			EventBus.getDefault().register(this);
		}
		onRefresh();
		return rootView;
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
	public void onDestroyView()
	{
		if (EventBus.getDefault().isRegistered(this))
		{
			EventBus.getDefault().unregister(this);
		}
		super.onDestroyView();
	}

	@Override
	protected void viewInit(View rootView)
	{
		vTitle = rootView.findViewById(R.id.view_title);
		vTitle.setVisibility(View.GONE);
		tvTitle = (TextView) rootView.findViewById(R.id.tv_title);
		tvTitle.setText(getString(R.string.func_article));
		ivRight = (ImageView) rootView.findViewById(R.id.iv_right);
		ivRight.setVisibility(View.VISIBLE);
		ivRight.setImageResource(R.drawable.ic_refresh);
		ivRight.setOnClickListener(this);
		xListView = (XListView) rootView.findViewById(R.id.xlist);
		xListView.setPullRefreshEnable(true);
		xListView.setPullLoadEnable(false);
		xListView.setAdapter(null);
		xListView.setXListViewListener(this);
		super.viewInit(rootView);
	}

	private void initData()
	{
	}

	//刷新数据
	@Override
	synchronized public void refreshData()
	{
		initData();
		mUserRedpackService.getNetAdList(0, 10, "", true);
		mThirdArticleInfoList.clear();
		netAdIndex = 0;
		if (mThirdArticleAdapter != null)
		{
			mThirdArticleAdapter.notifyDataSetChanged();
		}
		mThirdArticleAdapter = null;
		//获取更多
		loadMoreData();
	}

	//加载更多商品
	@Override
	synchronized public void loadMoreData()
	{
		mThirdArticleInfoService.getThirdArticleInfoList(mTypeId, null, mDataStart, getPageSize(), null, true);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.iv_right:
			{
				onRefresh();
				break;
			}
		}
	}

	synchronized private void refreshThirdArticleInfoList(List<ThirdArticleInfo> thirdArticleInfoList)
	{
		if (thirdArticleInfoList == null || thirdArticleInfoList.size() < 1)
		{
			xListView.setPullLoadEnable(false);
			if (mThirdArticleInfoList.size() < 1)
			{
			}
			return;
		}
		xListView.setPullLoadEnable(thirdArticleInfoList.size() >= getPageSize());
		int index = 1;
		for (ThirdArticleInfo thirdArticleInfo : thirdArticleInfoList)
		{
			if (index % 3 == 0)//每3篇文章加个广告
			{
				if (mAdInfoList.size() > netAdIndex)
				{
					AdInfo adInfo = mAdInfoList.get(netAdIndex);
					ThirdArticleInfo articleInfo = new ThirdArticleInfo();
					//					articleInfo.setLitpic(ApiConfig.getServerPicUrl(adInfo.getPicUrl()));
					articleInfo.setLitpic(adInfo.getPicUrl());
					articleInfo.setTitle(adInfo.getTitle());
					articleInfo.setSource(adInfo.getUrl());
					articleInfo.setFlag(adInfo.getAdId());
					articleInfo.setAddDateLong(0);
					mThirdArticleInfoList.add(articleInfo);
				}
				else
				{
					netAdIndex = 0;
				}
			}
			mThirdArticleInfoList.add(thirdArticleInfo);
			index++;
		}
		if (mThirdArticleAdapter == null)
		{
			final Context context = FragmentThirdArticleList2.this.getContext();
			ThirdArticleAdapter.IItemClick iItemClick = new ThirdArticleAdapter.IItemClick()
			{
				@Override
				public void onItemClicked(View v, final ThirdArticleInfo thirdArticleInfo)
				{
					if (thirdArticleInfo != null)
					{
						ActivityWebView.startWebActivity(context, thirdArticleInfo.getTitle(), ApiConfig.getThirdArticleUrl(thirdArticleInfo.getId()));
					}
				}
			};
			mThirdArticleAdapter = new ThirdArticleAdapter(context, mThirdArticleInfoList, iItemClick);
			xListView.setAdapter(mThirdArticleAdapter);
		}
		else
		{
			mThirdArticleAdapter.notifyDataSetChanged();
		}
	}

	@Override
	public boolean onAjaxDataResponse(String url, String result, AjaxStatus status)
	{
		if (url.endsWith(ApiConfig.GET_THIRD_ARTICLE_INFO_LIST))
		{
			xListView.stopRefresh();
			xListView.stopLoadMore();
			List<ThirdArticleInfo> thirdArticleInfoList = null;
			TableData tableData = GsonTools.getObject(result, TableData.class);
			if (tableData != null)
			{
				thirdArticleInfoList = GsonTools.getObjects(GsonTools.toJson(tableData.getData()), new TypeToken<List<ThirdArticleInfo>>()
				{
				}.getType());
			}
			refreshThirdArticleInfoList(thirdArticleInfoList);
		}
		else if (url.endsWith(ApiConfig.GET_NET_AD_LIST))
		{
			List<AdInfo> adInfoList = null;
			TableData tableData = GsonTools.getObject(result, TableData.class);
			if (tableData != null)
			{
				adInfoList = GsonTools.getObjects(GsonTools.toJson(tableData.getData()), new TypeToken<List<AdInfo>>()
				{
				}.getType());
			}
			if (adInfoList != null && adInfoList.size() > 0)
			{
				mAdInfoList = adInfoList;
			}
		}
		return true;
	}

	@Subscribe
	public void onEventMainThread(final UserEvent userEvent)
	{
		if (userEvent != null)
		{
			if (userEvent.getEvent().equalsIgnoreCase(UserEvent.REFRESH.getEvent()))
			{
				if (isVisible() && getUserVisibleHint())
				{
					onRefresh();
				}
			}
		}
	}
}
