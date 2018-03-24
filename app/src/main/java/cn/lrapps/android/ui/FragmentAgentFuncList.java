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
import cn.lrapps.android.ui.adapter.BalanceLogAdapter;
import cn.lrapps.enums.MoneyUnit;
import cn.lrapps.utils.GsonTools;
import cn.lrapps.utils.StringTools;
import com.squareup.picasso.Picasso;
import com.weiduyx.wdym.models.ReturnInfo;
import com.weiduyx.wdym.models.TableData;
import com.weiduyx.wdym.models.UserBalanceLogInfo;
import com.weiduyx.wdym.models.UserInfo;
import com.weiduyx.wdym.services.AgentService;
import com.weiduyx.wdym.services.ApiConfig;
import com.weiduyx.wdym.services.IAjaxDataResponse;
import com.weiduyx.wdym.services.UserBalanceLogInfoService;
import com.weiduyx.wdym.services.UserService;
import com.weiduyx.wdym.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentAgentFuncList extends MyBasePageFragment implements IAjaxDataResponse, View.OnClickListener
{
	private static final String TAG = FragmentAgentFuncList.class.getSimpleName();
	private View headView;
	private ImageView ivHead;
	private TextView tvProfitMoney, tvProfitPoint;
	private UserService mUserService;
	private AgentService mAgentService;
	private UserInfo mUserInfo;
	private UserBalanceLogInfoService mUserBalanceLogInfoService;
	private List<UserBalanceLogInfo> mUserBalanceLogInfoList = new ArrayList<>();
	private BalanceLogAdapter mBalanceLogAdapter;

	public FragmentAgentFuncList()
	{
	}

	public static FragmentAgentFuncList newInstance()
	{
		return new FragmentAgentFuncList();
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
		View rootView = inflater.inflate(R.layout.fragment_xlist, container, false);
		mUserService = new UserService(this.getContext());
		mUserService.addDataResponse(this);
		mAgentService = new AgentService(this.getContext());
		mAgentService.addDataResponse(this);
		mUserBalanceLogInfoService = new UserBalanceLogInfoService(this.getContext());
		mUserBalanceLogInfoService.addDataResponse(this);
		viewInit(rootView);
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
	public void onResume()
	{
		super.onResume();
		onRefresh();
	}

	@Override
	protected void viewInit(View rootView)
	{
		xListView = (XListView) rootView.findViewById(R.id.xlist);
		headView = LayoutInflater.from(this.getContext()).inflate(R.layout.fragment_agent_head, null);
		xListView.setPullRefreshEnable(true);
		xListView.setPullLoadEnable(false);
		xListView.addHeaderView(headView);
		xListView.setAdapter(null);
		xListView.setXListViewListener(this);
		ivHead = (ImageView) rootView.findViewById(R.id.iv_head);
		tvProfitMoney = (TextView) rootView.findViewById(R.id.tv_profit_money);
		tvProfitPoint = (TextView) rootView.findViewById(R.id.tv_profit_point);
		rootView.findViewById(R.id.layout_agent_share).setOnClickListener(this);
		rootView.findViewById(R.id.layout_agent_fans).setOnClickListener(this);
		rootView.findViewById(R.id.layout_agent_makers).setOnClickListener(this);
		rootView.findViewById(R.id.layout_agent_agents).setOnClickListener(this);
		rootView.findViewById(R.id.layout_agent_fans_profit).setOnClickListener(this);
		rootView.findViewById(R.id.layout_agent_agent_profit).setOnClickListener(this);
		super.viewInit(rootView);
	}

	private void getTotalMoney(final TextView tv, final byte moneyUnit, boolean isToday)
	{
		AgentService agentService = new AgentService(this.getContext());
		agentService.addDataResponse(new IAjaxDataResponse()
		{
			@Override
			public boolean onAjaxDataResponse(String url, String result, AjaxStatus status)
			{
				if (moneyUnit == MoneyUnit.RMB.getType())
				{
					tv.setText(StringTools.convertToRmb(Integer.parseInt(result)) + "元");
				}
				else
				{
					tv.setText(result + "金币");
				}
				return false;
			}
		});
		agentService.getAgentBalanceLogMoney(moneyUnit, isToday, "", true);
	}

	//刷新数据
	@Override
	synchronized public void refreshData()
	{
		mUserBalanceLogInfoList.clear();
		if (mBalanceLogAdapter != null)
		{
			mBalanceLogAdapter.notifyDataSetChanged();
		}
		mBalanceLogAdapter = null;
		mUserService.getMyUserInfo(null, true);
		getTotalMoney(tvProfitMoney, MoneyUnit.RMB.getType(), false);
		getTotalMoney(tvProfitPoint, MoneyUnit.JINBI.getType(), false);
		loadMoreData();
	}

	//加载更多商品
	@Override
	synchronized public void loadMoreData()
	{
		String tips = (mDataStart == 0 ? "请稍后..." : "");
		mUserBalanceLogInfoService.getAgentProfitBalanceLogInfoList(mDataStart, getPageSize(), tips, true);
	}

	synchronized private void refreshUserBalanceLogInfoList(List<UserBalanceLogInfo> userBalanceLogInfoList)
	{
		if (userBalanceLogInfoList == null || userBalanceLogInfoList.size() < 1)
		{
			xListView.setPullLoadEnable(false);
			if (mUserBalanceLogInfoList.size() < 1)
			{
			}
			return;
		}
		xListView.setPullLoadEnable(userBalanceLogInfoList.size() >= getPageSize());
		for (UserBalanceLogInfo userBalanceLogInfo : userBalanceLogInfoList)
		{
			mUserBalanceLogInfoList.add(userBalanceLogInfo);
		}
		if (mBalanceLogAdapter == null)
		{
			final Context context = this.getContext();
			BalanceLogAdapter.IItemClick iItemClick = new BalanceLogAdapter.IItemClick()
			{
				@Override
				public void onItemClicked(View v, final UserBalanceLogInfo userBalanceLogInfo)
				{
					if (userBalanceLogInfo != null)
					{
					}
				}
			};
			mBalanceLogAdapter = new BalanceLogAdapter(context, mUserBalanceLogInfoList, iItemClick);
			xListView.setAdapter(mBalanceLogAdapter);
		}
		else
		{
			mBalanceLogAdapter.notifyDataSetChanged();
		}
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.layout_agent_share:
			{
				ActivityWebView.startWebActivity(this.getContext(), "", ApiConfig.getAgentShareUrl());
				break;
			}
			case R.id.layout_agent_fans:
			{
				startActivity(new Intent(FragmentAgentFuncList.this.getContext(), ActivityFansList.class));
				break;
			}
			case R.id.layout_agent_makers:
			{
				startActivity(new Intent(FragmentAgentFuncList.this.getContext(), ActivityMakerList.class));
				break;
			}
			case R.id.layout_agent_agents:
			{
				startActivity(new Intent(FragmentAgentFuncList.this.getContext(), ActivityAgentList.class));
				break;
			}
			case R.id.layout_agent_fans_profit:
			{
				startActivity(new Intent(FragmentAgentFuncList.this.getContext(), ActivityAgentFansProfits.class));
				break;
			}
			case R.id.layout_agent_agent_profit:
			{
				startActivity(new Intent(FragmentAgentFuncList.this.getContext(), ActivityAgentAgentProfits.class));
				break;
			}
		}
	}

	@Override
	public boolean onAjaxDataResponse(String url, String result, AjaxStatus status)
	{
		xListView.stopRefresh();
		xListView.stopLoadMore();
		if (url.endsWith(ApiConfig.GET_AGENT_PROFIT_BALANCE_LOG_INFO_LIST))
		{
			List<UserBalanceLogInfo> userBalanceLogInfoList = null;
			TableData tableData = GsonTools.getObject(result, TableData.class);
			if (tableData != null)
			{
				userBalanceLogInfoList = GsonTools.getObjects(GsonTools.toJson(tableData.getData()), new TypeToken<List<UserBalanceLogInfo>>()
				{
				}.getType());
			}
			refreshUserBalanceLogInfoList(userBalanceLogInfoList);
			return true;
		}
		else if (url.endsWith(ApiConfig.GET_MY_USER_INFO))
		{
			ReturnInfo returnInfo = GsonTools.getReturnInfo(result);
			if (ReturnInfo.isSuccess(returnInfo))
			{
				mUserInfo = GsonTools.getReturnObject(returnInfo, UserInfo.class);
				if (mUserInfo != null)
				{
					Uri uri = Uri.parse(ApiConfig.getServerPicUrl(mUserInfo.getPicId()));
					Picasso.with(this.getContext()).load(uri).placeholder(R.drawable.ic_header).error(R.drawable.ic_header).into(ivHead);
				}
			}
		}
		return true;
	}
}
