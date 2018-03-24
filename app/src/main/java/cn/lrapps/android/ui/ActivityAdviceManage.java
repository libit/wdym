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
import cn.lrapps.android.ui.adapter.AdviceAdapter;
import cn.lrapps.utils.ConstValues;
import cn.lrapps.utils.GsonTools;
import com.weiduyx.wdym.models.AdviceInfo;
import com.weiduyx.wdym.models.TableData;
import com.weiduyx.wdym.services.AdviceService;
import com.weiduyx.wdym.services.ApiConfig;
import com.weiduyx.wdym.services.IAjaxDataResponse;
import com.weiduyx.wdym.R;

import java.util.ArrayList;
import java.util.List;

public class ActivityAdviceManage extends MyBasePageActivity implements IAjaxDataResponse
{
	private static final String TAG = ActivityAdviceManage.class.getSimpleName();
	private View layoutList, layoutNoData;
	private AdviceService mAdviceService;
	private List<AdviceInfo> mAdviceInfoList = new ArrayList<>();
	private AdviceAdapter mAdviceAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_advice_manage);
		mAdviceService = new AdviceService(this);
		mAdviceService.addDataResponse(this);
		viewInit();
		//		if (!EventBus.getDefault().isRegistered(this))
		//		{
		//			EventBus.getDefault().register(this);
		//		}
		onRefresh();
	}
	//	@Override
	//	protected void onDestroy()
	//	{
	//		if (EventBus.getDefault().isRegistered(this))
	//		{
	//			EventBus.getDefault().unregister(this);
	//		}
	//		super.onDestroy();
	//	}
	//
	//	@Subscribe
	//	public void onEventMainThread(UserArticleEvent userArticleEvent)
	//	{
	//		if (userArticleEvent != null)
	//		{
	//			onRefresh();
	//		}
	//	}

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
		mAdviceInfoList.clear();
		if (mAdviceAdapter != null)
		{
			mAdviceAdapter.notifyDataSetChanged();
		}
		mAdviceAdapter = null;
		loadMoreData();
	}

	@Override
	public void loadMoreData()
	{
		String tips = (mDataStart == 0 ? "请稍后..." : "");
		mAdviceService.getAdviceInfoList(mDataStart, getPageSize(), tips, true);
	}

	synchronized private void refreshAdviceInfoList(List<AdviceInfo> adviceInfoList)
	{
		if (adviceInfoList == null || adviceInfoList.size() < 1)
		{
			xListView.setPullLoadEnable(false);
			if (mAdviceInfoList.size() < 1)
			{
				layoutList.setVisibility(View.GONE);
				layoutNoData.setVisibility(View.VISIBLE);
			}
			return;
		}
		layoutList.setVisibility(View.VISIBLE);
		layoutNoData.setVisibility(View.GONE);
		xListView.setPullLoadEnable(adviceInfoList.size() >= getPageSize());
		for (AdviceInfo adviceInfo : adviceInfoList)
		{
			mAdviceInfoList.add(adviceInfo);
		}
		if (mAdviceAdapter == null)
		{
			AdviceAdapter.IItemClick iItemClick = new AdviceAdapter.IItemClick()
			{
				@Override
				public void onItemClicked(View v, final AdviceInfo adviceInfo)
				{
					if (adviceInfo != null)
					{
						Intent intent = new Intent(ActivityAdviceManage.this, ActivityAdviceInfo.class);
						intent.putExtra(ConstValues.DATA_ADVICE_ID, adviceInfo.getAdviceId());
						ActivityAdviceManage.this.startActivity(intent);
					}
				}
			};
			mAdviceAdapter = new AdviceAdapter(this, mAdviceInfoList, iItemClick);
			xListView.setAdapter(mAdviceAdapter);
		}
		else
		{
			mAdviceAdapter.notifyDataSetChanged();
		}
	}

	@Override
	public boolean onAjaxDataResponse(String url, String result, AjaxStatus status)
	{
		xListView.stopRefresh();
		xListView.stopLoadMore();
		if (url.endsWith(ApiConfig.GET_ADVICE_INFO_LIST))
		{
			List<AdviceInfo> adviceInfoList = null;
			TableData tableData = GsonTools.getObject(result, TableData.class);
			if (tableData != null)
			{
				adviceInfoList = GsonTools.getObjects(GsonTools.toJson(tableData.getData()), new TypeToken<List<AdviceInfo>>()
				{
				}.getType());
			}
			refreshAdviceInfoList(adviceInfoList);
			return true;
		}
		return false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.menu_activity_advice_manage, menu);
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
