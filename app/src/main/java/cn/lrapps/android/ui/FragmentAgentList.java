/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.android.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.callback.AjaxStatus;
import com.external.xlistview.XListView;
import com.google.gson.reflect.TypeToken;
import cn.lrapps.android.ui.adapter.UserAdapter;
import cn.lrapps.utils.ConstValues;
import cn.lrapps.utils.GsonTools;
import com.squareup.picasso.Picasso;
import com.weiduyx.wdym.models.TableData;
import com.weiduyx.wdym.models.UserInfo;
import com.weiduyx.wdym.services.ApiConfig;
import com.weiduyx.wdym.services.IAjaxDataResponse;
import com.weiduyx.wdym.services.UserService;
import com.weiduyx.wdym.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentAgentList extends MyBasePageFragment implements IAjaxDataResponse
{
	private static final String TAG = FragmentAgentList.class.getSimpleName();
	private View layoutList, layoutNoData;
	private ImageView ivPic;
	private TextView tvPeopelCount;
	private UserService mUserService;
	private List<UserInfo> mUserInfoList = new ArrayList<>();
	private UserAdapter mUserAdapter;

	public FragmentAgentList()
	{
	}

	public static FragmentAgentList newInstance()
	{
		return new FragmentAgentList();
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
		View rootView = inflater.inflate(R.layout.fragment_agent_list, container, false);
		mUserService = new UserService(this.getContext());
		mUserService.addDataResponse(this);
		viewInit(rootView);
		//		if (!EventBus.getDefault().isRegistered(this))
		//		{
		//			EventBus.getDefault().register(this);
		//		}
		mUserService.getMyUserInfo(null, true);
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
		ivPic = (ImageView) rootView.findViewById(R.id.iv_head);
		tvPeopelCount = (TextView) rootView.findViewById(R.id.tv_total_people);
		super.viewInit(rootView);
	}

	//刷新数据
	@Override
	synchronized public void refreshData()
	{
		mUserInfoList.clear();
		if (mUserAdapter != null)
		{
			mUserAdapter.notifyDataSetChanged();
		}
		mUserAdapter = null;
		loadMoreData();
	}

	//加载更多
	@Override
	synchronized public void loadMoreData()
	{
		String tips = (mDataStart == 0 ? "请稍后..." : "");
		mUserService.getUserFansList(true, null, mDataStart, getPageSize(), tips, true);
	}

	synchronized private void refreshUserInfoList(List<UserInfo> userInfoList)
	{
		if (userInfoList == null || userInfoList.size() < 1)
		{
			xListView.setPullLoadEnable(false);
			if (mUserInfoList.size() < 1)
			{
				layoutList.setVisibility(View.GONE);
				layoutNoData.setVisibility(View.VISIBLE);
			}
			return;
		}
		layoutList.setVisibility(View.VISIBLE);
		layoutNoData.setVisibility(View.GONE);
		xListView.setPullLoadEnable(userInfoList.size() >= getPageSize());
		for (UserInfo userInfo : userInfoList)
		{
			mUserInfoList.add(userInfo);
		}
		if (mUserAdapter == null)
		{
			final Context context = FragmentAgentList.this.getContext();
			UserAdapter.IItemClick iItemClick = new UserAdapter.IItemClick()
			{
				@Override
				public void onItemClicked(View v, final UserInfo userInfo)
				{
					if (userInfo != null)
					{
						Intent intent = new Intent(context, ActivityUserFans.class);
						intent.putExtra(ConstValues.DATA_USER_ID, userInfo.getUserId());
						context.startActivity(intent);
					}
				}
			};
			mUserAdapter = new UserAdapter(context, mUserInfoList, iItemClick);
			xListView.setAdapter(mUserAdapter);
		}
		else
		{
			mUserAdapter.notifyDataSetChanged();
		}
	}

	@Override
	public boolean onAjaxDataResponse(String url, String result, AjaxStatus status)
	{
		xListView.stopRefresh();
		xListView.stopLoadMore();
		if (url.endsWith(ApiConfig.GET_MY_USER_INFO))
		{
			UserInfo userInfo = GsonTools.getReturnObject(result, UserInfo.class);
			if (userInfo != null)
			{
				Picasso.with(this.getContext()).load(Uri.parse(ApiConfig.getServerPicUrl(userInfo.getPicId()))).placeholder(R.drawable.ic_header).error(R.drawable.ic_header).into(ivPic);
			}
			else
			{
				UserService.logout();
			}
		}
		else if (url.endsWith(ApiConfig.GET_REFERRER_USER_INFO_LIST))
		{
			List<UserInfo> userInfoList = null;
			TableData tableData = GsonTools.getObject(result, TableData.class);
			if (tableData != null)
			{
				tvPeopelCount.setText(tableData.getRecordsTotal() + "");
				userInfoList = GsonTools.getObjects(GsonTools.toJson(tableData.getData()), new TypeToken<List<UserInfo>>()
				{
				}.getType());
			}
			refreshUserInfoList(userInfoList);
			return true;
		}
		return true;
	}
}
