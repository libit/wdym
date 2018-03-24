/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.android.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.androidquery.callback.AjaxStatus;
import com.external.xlistview.XListView;
import com.google.gson.reflect.TypeToken;
import com.weiduyx.wdym.models.NewsInfo;
import com.weiduyx.wdym.models.TableData;
import com.weiduyx.wdym.services.ApiConfig;
import com.weiduyx.wdym.services.IAjaxDataResponse;
import com.weiduyx.wdym.services.NewsService;
import com.weiduyx.wdym.R;

import java.util.ArrayList;
import java.util.List;

import cn.lrapps.android.ui.adapter.NewsAdapter;
import cn.lrapps.utils.ConstValues;
import cn.lrapps.utils.GsonTools;

public class ActivityNewsManage extends MyBasePageActivity implements IAjaxDataResponse
{
	private static final String TAG = ActivityNewsManage.class.getSimpleName();
	private View layoutList, layoutNoData;
	private NewsService mNewsService;
	private List<NewsInfo> mNewsInfoList = new ArrayList<>();
	private NewsAdapter mNewsAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news_manage);
		mNewsService = new NewsService(this);
		mNewsService.addDataResponse(this);
		viewInit();
		//		if (!EventBus.getDefault().isRegistered(this))
		//		{
		//			EventBus.getDefault().register(this);
		//		}
		onRefresh();
	}

	@Override
	protected void onDestroy()
	{
		//		if (EventBus.getDefault().isRegistered(this))
		//		{
		//			EventBus.getDefault().unregister(this);
		//		}
		super.onDestroy();
	}

	@Override
	protected void viewInit()
	{
		super.viewInit();
		setBackButton();
		layoutList = findViewById(R.id.layout_list);
		layoutNoData = findViewById(R.id.layout_no_data);
		xListView = (XListView) findViewById(R.id.xlist);
		xListView.setPullRefreshEnable(true);
		xListView.setPullLoadEnable(true);
		xListView.setXListViewListener(this);
	}

	@Override
	public void refreshData()
	{
		mNewsInfoList.clear();
		if (mNewsAdapter != null)
		{
			mNewsAdapter.notifyDataSetChanged();
		}
		mNewsAdapter = null;
		loadMoreData();
	}

	@Override
	public void loadMoreData()
	{
		String tips = (mDataStart == 0 ? "请稍后..." : "");
		mNewsService.getNewsInfoList(mDataStart, getPageSize(), tips, true);
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
			NewsAdapter.IItemClick iItemClick = new NewsAdapter.IItemClick()
			{
				@Override
				public void onItemClicked(View v, final NewsInfo newsInfo)
				{
					if (newsInfo != null)
					{
						Intent intent = new Intent(ActivityNewsManage.this, ActivityNews.class);
						intent.putExtra(ConstValues.DATA_NEWS_ID, newsInfo.getNewsId());
						ActivityNewsManage.this.startActivity(intent);
					}
				}
			};
			mNewsAdapter = new NewsAdapter(this, mNewsInfoList, iItemClick);
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
		return false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.menu_activity_news_manage, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		int id = item.getItemId();
		if (id == R.id.action_refresh)
		{
			onRefresh();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
