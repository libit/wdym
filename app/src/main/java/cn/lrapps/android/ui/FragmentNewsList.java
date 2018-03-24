/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.android.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidquery.callback.AjaxStatus;
import com.external.xlistview.XListView;
import com.google.gson.reflect.TypeToken;
import cn.lrapps.android.ui.adapter.NewsAdapter;
import cn.lrapps.utils.ConstValues;
import cn.lrapps.utils.GsonTools;
import com.weiduyx.wdym.models.NewsInfo;
import com.weiduyx.wdym.models.TableData;
import com.weiduyx.wdym.services.ApiConfig;
import com.weiduyx.wdym.services.IAjaxDataResponse;
import com.weiduyx.wdym.services.NewsService;
import com.weiduyx.wdym.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentNewsList extends MyBasePageFragment implements IAjaxDataResponse
{
	private static final String TAG = FragmentNewsList.class.getSimpleName();
	private View layoutList, layoutNoData;
	private NewsService mNewsService;
	private List<NewsInfo> mNewsInfoList = new ArrayList<>();
	private NewsAdapter mNewsAdapter;

	public FragmentNewsList()
	{
	}

	public static FragmentNewsList newInstance()
	{
		return new FragmentNewsList();
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
		View rootView = inflater.inflate(R.layout.fragment_news_list, container, false);
		mNewsService = new NewsService(this.getContext());
		mNewsService.addDataResponse(this);
		viewInit(rootView);
		//		if (!EventBus.getDefault().isRegistered(this))
		//		{
		//			EventBus.getDefault().register(this);
		//		}
		return rootView;
	}

	@Override
	public void onDestroyView()
	{
		//		if (EventBus.getDefault().isRegistered(this))
		//		{
		//			EventBus.getDefault().unregister(this);
		//		}
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
		layoutList = rootView.findViewById(R.id.layout_list);
		layoutNoData = rootView.findViewById(R.id.layout_no_data);
		xListView = (XListView) rootView.findViewById(R.id.xlist);
		xListView.setPullRefreshEnable(true);
		xListView.setPullLoadEnable(true);
		xListView.setXListViewListener(this);
		super.viewInit(rootView);
	}

	//刷新数据
	@Override
	synchronized public void refreshData()
	{
		mNewsInfoList.clear();
		if (mNewsAdapter != null)
		{
			mNewsAdapter.notifyDataSetChanged();
		}
		mNewsAdapter = null;
		loadMoreData();
	}

	//加载更多
	@Override
	synchronized public void loadMoreData()
	{
		String tips = (mDataStart == 0 ? "请稍后..." : "");
		mNewsService.getNewsInfoList(mDataStart, getPageSize(), "", true);
	}

	synchronized private void refreshNewsInfoList(List<NewsInfo> newsInfoList)
	{
		if (newsInfoList == null || newsInfoList.size() < 1)
		{
			xListView.setPullLoadEnable(false);
			if (mNewsInfoList.size() < 1)
			{
				layoutList.setVisibility(View.GONE);
				layoutNoData.setVisibility(View.VISIBLE);
			}
			return;
		}
		layoutList.setVisibility(View.VISIBLE);
		layoutNoData.setVisibility(View.GONE);
		xListView.setPullLoadEnable(newsInfoList.size() >= getPageSize());
		for (NewsInfo newsInfo : newsInfoList)
		{
			mNewsInfoList.add(newsInfo);
		}
		if (mNewsAdapter == null)
		{
			final Context context = FragmentNewsList.this.getContext();
			NewsAdapter.IItemClick iItemClick = new NewsAdapter.IItemClick()
			{
				@Override
				public void onItemClicked(View v, final NewsInfo newsInfo)
				{
					if (newsInfo != null)
					{
						Intent intent = new Intent(context, ActivityNews.class);
						intent.putExtra(ConstValues.DATA_NEWS_ID, newsInfo.getNewsId());
						context.startActivity(intent);
					}
				}
			};
			mNewsAdapter = new NewsAdapter(context, mNewsInfoList, iItemClick);
			xListView.setAdapter(mNewsAdapter);
		}
		else
		{
			mNewsAdapter.notifyDataSetChanged();
		}
	}

	@Override
	public boolean onAjaxDataResponse(String url, String result, AjaxStatus status)
	{
		xListView.stopRefresh();
		xListView.stopLoadMore();
		if (url.endsWith(ApiConfig.GET_NEWS_LIST))
		{
			List<NewsInfo> newsInfoList = null;
			TableData tableData = GsonTools.getObject(result, TableData.class);
			if (tableData != null)
			{
				newsInfoList = GsonTools.getObjects(GsonTools.toJson(tableData.getData()), new TypeToken<List<NewsInfo>>()
				{
				}.getType());
			}
			refreshNewsInfoList(newsInfoList);
			return true;
		}
		return true;
	}
}
