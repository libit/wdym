/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.android.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.androidquery.callback.AjaxStatus;
import com.external.xlistview.XListView;
import com.google.gson.reflect.TypeToken;
import com.weiduyx.wdym.R;
import com.weiduyx.wdym.models.AdInfo;
import com.weiduyx.wdym.models.TableData;
import com.weiduyx.wdym.services.ApiConfig;
import com.weiduyx.wdym.services.IAjaxDataResponse;
import com.weiduyx.wdym.services.UserRedpackService;

import java.util.ArrayList;
import java.util.List;

import cn.lrapps.android.ui.adapter.DownloadAdAdapter;
import cn.lrapps.utils.ConstValues;
import cn.lrapps.utils.GsonTools;

public class ActivityDownloadAdList extends MyBasePageActivity implements IAjaxDataResponse
{
	private static final String TAG = ActivityDownloadAdList.class.getSimpleName();
	private View layoutList, layoutNoData;
	private UserRedpackService mUserRedpackService;
	private List<AdInfo> mAdInfoList = new ArrayList<>();
	private DownloadAdAdapter mDownloadAdAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_download_ad_list);
		mUserRedpackService = new UserRedpackService(this);
		mUserRedpackService.addDataResponse(this);
		viewInit();
		onRefresh();
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
		mAdInfoList.clear();
		if (mDownloadAdAdapter != null)
		{
			mDownloadAdAdapter.notifyDataSetChanged();
		}
		mDownloadAdAdapter = null;
		loadMoreData();
	}

	@Override
	public void loadMoreData()
	{
		String tips = (mDataStart == 0 ? "请稍后..." : "");
		mUserRedpackService.getDownloadAdList(mDataStart, getPageSize(), tips, true);
	}

	synchronized private void refreshAdInfoList(List<AdInfo> adInfoList)
	{
		if (adInfoList == null || adInfoList.size() < 1)
		{
			xListView.setPullLoadEnable(false);
			if (mAdInfoList.size() < 1)
			{
				layoutList.setVisibility(View.GONE);
				layoutNoData.setVisibility(View.VISIBLE);
			}
			return;
		}
		layoutList.setVisibility(View.VISIBLE);
		layoutNoData.setVisibility(View.GONE);
		xListView.setPullLoadEnable(adInfoList.size() >= getPageSize());
		for (AdInfo adInfo : adInfoList)
		{
			mAdInfoList.add(adInfo);
		}
		if (mDownloadAdAdapter == null)
		{
			DownloadAdAdapter.IItemClick iItemClick = new DownloadAdAdapter.IItemClick()
			{
				@Override
				public void onItemClicked(View v, final AdInfo adInfo)
				{
					if (adInfo != null)
					{
						Intent intent = new Intent(ActivityDownloadAdList.this, ActivityDownloadAdInfo.class);
						intent.putExtra(ConstValues.DATA_AD_ID, adInfo.getAdId());
						ActivityDownloadAdList.this.startActivity(intent);
					}
				}
			};
			mDownloadAdAdapter = new DownloadAdAdapter(this, mAdInfoList, iItemClick);
			xListView.setAdapter(mDownloadAdAdapter);
		}
		else
		{
			mDownloadAdAdapter.notifyDataSetChanged();
		}
	}

	@Override
	public boolean onAjaxDataResponse(String url, String result, AjaxStatus status)
	{
		xListView.stopRefresh();
		xListView.stopLoadMore();
		if (url.endsWith(ApiConfig.GET_DOWNLOAD_AD_LIST))
		{
			List<AdInfo> adInfoList = null;
			TableData tableData = GsonTools.getObject(result, TableData.class);
			if (tableData != null)
			{
				adInfoList = GsonTools.getObjects(GsonTools.toJson(tableData.getData()), new TypeToken<List<AdInfo>>()
				{
				}.getType());
			}
			refreshAdInfoList(adInfoList);
			return true;
		}
		return false;
	}
	//	@Override
	//	public boolean onCreateOptionsMenu(Menu menu)
	//	{
	//		getMenuInflater().inflate(R.menu.menu_activity_news_manage, menu);
	//		return true;
	//	}
	//
	//	@Override
	//	public boolean onOptionsItemSelected(MenuItem item)
	//	{
	//		int id = item.getItemId();
	//		if (id == R.id.action_refresh)
	//		{
	//			onRefresh();
	//			return true;
	//		}
	//		return super.onOptionsItemSelected(item);
	//	}
}
