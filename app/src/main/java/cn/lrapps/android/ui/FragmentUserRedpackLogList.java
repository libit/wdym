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
import cn.lrapps.android.ui.adapter.RedpackLogAdapter;
import cn.lrapps.utils.GsonTools;
import com.weiduyx.wdym.models.TableData;
import com.weiduyx.wdym.models.UserRedpackLogInfo;
import com.weiduyx.wdym.services.ApiConfig;
import com.weiduyx.wdym.services.IAjaxDataResponse;
import com.weiduyx.wdym.services.UserRedpackService;
import com.weiduyx.wdym.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentUserRedpackLogList extends MyBasePageFragment implements IAjaxDataResponse
{
	private static final String TAG = FragmentUserRedpackLogList.class.getSimpleName();
	private View layoutList, layoutNoData;
	private UserRedpackService mUserRedpackService;
	private List<UserRedpackLogInfo> mUserRedpackLogInfoList = new ArrayList<>();
	private RedpackLogAdapter mRedpackLogAdapter;

	public FragmentUserRedpackLogList()
	{
	}

	public static FragmentUserRedpackLogList newInstance()
	{
		return new FragmentUserRedpackLogList();
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
		mUserRedpackService = new UserRedpackService(this.getContext());
		mUserRedpackService.addDataResponse(this);
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
		mUserRedpackLogInfoList.clear();
		if (mRedpackLogAdapter != null)
		{
			mRedpackLogAdapter.notifyDataSetChanged();
		}
		mRedpackLogAdapter = null;
		loadMoreData();
	}

	//加载更多
	@Override
	synchronized public void loadMoreData()
	{
		String tips = (mDataStart == 0 ? "请稍后..." : "");
		mUserRedpackService.getUserRedpackLogInfoList(mDataStart, getPageSize(), tips, true);
	}

	synchronized private void refreshUserRedpackLogInfoList(List<UserRedpackLogInfo> userRedpackLogInfoList)
	{
		if (userRedpackLogInfoList == null || userRedpackLogInfoList.size() < 1)
		{
			xListView.setPullLoadEnable(false);
			if (mUserRedpackLogInfoList.size() < 1)
			{
				layoutList.setVisibility(View.GONE);
				layoutNoData.setVisibility(View.VISIBLE);
			}
			return;
		}
		layoutList.setVisibility(View.VISIBLE);
		layoutNoData.setVisibility(View.GONE);
		xListView.setPullLoadEnable(userRedpackLogInfoList.size() >= getPageSize());
		for (UserRedpackLogInfo userRedpackLogInfo : userRedpackLogInfoList)
		{
			mUserRedpackLogInfoList.add(userRedpackLogInfo);
		}
		if (mRedpackLogAdapter == null)
		{
			final Context context = FragmentUserRedpackLogList.this.getContext();
			RedpackLogAdapter.IItemClick iItemClick = new RedpackLogAdapter.IItemClick()
			{
				@Override
				public void onItemClicked(View v, final UserRedpackLogInfo userRedpackLogInfo)
				{
					if (userRedpackLogInfo != null)
					{
					}
				}
			};
			mRedpackLogAdapter = new RedpackLogAdapter(context, mUserRedpackLogInfoList, iItemClick);
			xListView.setAdapter(mRedpackLogAdapter);
		}
		else
		{
			mRedpackLogAdapter.notifyDataSetChanged();
		}
	}

	@Override
	public boolean onAjaxDataResponse(String url, String result, AjaxStatus status)
	{
		xListView.stopRefresh();
		xListView.stopLoadMore();
		if (url.endsWith(ApiConfig.GET_USER_REDPACK_LOG_INFO_LIST))
		{
			List<UserRedpackLogInfo> userRedpackLogInfoList = null;
			TableData tableData = GsonTools.getObject(result, TableData.class);
			if (tableData != null)
			{
				userRedpackLogInfoList = GsonTools.getObjects(GsonTools.toJson(tableData.getData()), new TypeToken<List<UserRedpackLogInfo>>()
				{
				}.getType());
			}
			refreshUserRedpackLogInfoList(userRedpackLogInfoList);
			return true;
		}
		return true;
	}
}
