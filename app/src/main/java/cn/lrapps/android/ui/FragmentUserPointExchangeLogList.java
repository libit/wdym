/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.android.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidquery.callback.AjaxStatus;
import com.external.xlistview.XListView;
import com.google.gson.reflect.TypeToken;
import cn.lrapps.android.ui.adapter.PointExchangeLogAdapter;
import cn.lrapps.utils.GsonTools;
import com.weiduyx.wdym.models.TableData;
import com.weiduyx.wdym.models.UserPointExchangeInfo;
import com.weiduyx.wdym.services.ApiConfig;
import com.weiduyx.wdym.services.IAjaxDataResponse;
import com.weiduyx.wdym.services.UserPointExchangeService;
import com.weiduyx.wdym.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentUserPointExchangeLogList extends MyBasePageFragment implements IAjaxDataResponse
{
	private static final String TAG = FragmentUserPointExchangeLogList.class.getSimpleName();
	private View layoutList, layoutNoData;
	private UserPointExchangeService mUserPointExchangeService;
	private List<UserPointExchangeInfo> mUserPointExchangeInfoList = new ArrayList<>();
	private PointExchangeLogAdapter mPointExchangeLogAdapter;

	public FragmentUserPointExchangeLogList()
	{
	}

	public static FragmentUserPointExchangeLogList newInstance()
	{
		return new FragmentUserPointExchangeLogList();
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
		View rootView = inflater.inflate(R.layout.fragment_list, container, false);
		mUserPointExchangeService = new UserPointExchangeService(this.getContext());
		mUserPointExchangeService.addDataResponse(this);
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
		mUserPointExchangeInfoList.clear();
		if (mPointExchangeLogAdapter != null)
		{
			mPointExchangeLogAdapter.notifyDataSetChanged();
		}
		mPointExchangeLogAdapter = null;
		loadMoreData();
	}

	//加载更多
	@Override
	synchronized public void loadMoreData()
	{
		String tips = (mDataStart == 0 ? "请稍后..." : "");
		mUserPointExchangeService.getUserPointExchangeInfoList(mDataStart, getPageSize(), tips, true);
	}

	synchronized private void refreshUserPointExchangeInfoList(List<UserPointExchangeInfo> userPointExchangeInfoList)
	{
		if (userPointExchangeInfoList == null || userPointExchangeInfoList.size() < 1)
		{
			xListView.setPullLoadEnable(false);
			if (mUserPointExchangeInfoList.size() < 1)
			{
				layoutList.setVisibility(View.GONE);
				layoutNoData.setVisibility(View.VISIBLE);
			}
			return;
		}
		layoutList.setVisibility(View.VISIBLE);
		layoutNoData.setVisibility(View.GONE);
		xListView.setPullLoadEnable(userPointExchangeInfoList.size() >= getPageSize());
		for (UserPointExchangeInfo userPointExchangeInfo : userPointExchangeInfoList)
		{
			mUserPointExchangeInfoList.add(userPointExchangeInfo);
		}
		if (mPointExchangeLogAdapter == null)
		{
			final Context context = FragmentUserPointExchangeLogList.this.getContext();
			PointExchangeLogAdapter.IItemClick iItemClick = new PointExchangeLogAdapter.IItemClick()
			{
				@Override
				public void onItemClicked(View v, final UserPointExchangeInfo userPointExchangeInfo)
				{
					if (userPointExchangeInfo != null)
					{
						//						Intent intent = new Intent(context, ActivityNews.class);
						//						intent.putExtra(ConstValues.DATA_NEWS_ID, userBalanceLogInfo.getServerId());
						//						context.startActivity(intent);
					}
				}
			};
			mPointExchangeLogAdapter = new PointExchangeLogAdapter(context, mUserPointExchangeInfoList, iItemClick);
			xListView.setAdapter(mPointExchangeLogAdapter);
		}
		else
		{
			mPointExchangeLogAdapter.notifyDataSetChanged();
		}
	}

	@Override
	public boolean onAjaxDataResponse(String url, String result, AjaxStatus status)
	{
		xListView.stopRefresh();
		xListView.stopLoadMore();
		if (url.endsWith(ApiConfig.GET_USER_POINT_EXCHANGE_INFO_LIST))
		{
			List<UserPointExchangeInfo> userPointExchangeInfoList = null;
			TableData tableData = GsonTools.getObject(result, TableData.class);
			if (tableData != null)
			{
				userPointExchangeInfoList = GsonTools.getObjects(GsonTools.toJson(tableData.getData()), new TypeToken<List<UserPointExchangeInfo>>()
				{
				}.getType());
			}
			refreshUserPointExchangeInfoList(userPointExchangeInfoList);
			return true;
		}
		return true;
	}
}
