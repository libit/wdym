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
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.callback.AjaxStatus;
import com.external.xlistview.XListView;
import com.squareup.picasso.Picasso;
import com.weiduyx.wdym.R;
import com.weiduyx.wdym.models.ReturnInfo;
import com.weiduyx.wdym.models.UserInfo;
import com.weiduyx.wdym.services.ApiConfig;
import com.weiduyx.wdym.services.IAjaxDataResponse;
import com.weiduyx.wdym.services.UserService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import cn.lrapps.android.ui.adapter.GridFuliFuncAdapter;
import cn.lrapps.enums.StatusType;
import cn.lrapps.enums.UserEvent;
import cn.lrapps.enums.UserType;
import cn.lrapps.models.FuncInfo;
import cn.lrapps.utils.GsonTools;
import cn.lrapps.utils.StringTools;
import cn.lrapps.utils.viewtools.DisplayTools;
import cn.lrapps.utils.viewtools.ViewHeightCalTools;

public class FragmentUser extends MyBasePageFragment implements IAjaxDataResponse, View.OnClickListener
{
	private static final String TAG = FragmentUser.class.getSimpleName();
	public static final int COL_SIZE = 3;
	private View headView, layoutMakerApply, viewMakerApply, layoutMaker, viewMaker, layoutAgent, viewAgent;
	private ImageView ivPhoto;
	private TextView tvTitle;
	private ImageView ivRight;
	private TextView tvName, tvBalance;
	private UserService mUserService;
	private UserInfo mUserInfo;
	private GridView lvFunc, lvFunc2;
	protected GridFuliFuncAdapter mGridFuncAdapter, mGridFuncAdapter2;
	protected List<FuncInfo> mFuncInfoList, mFuncInfoList2;

	public FragmentUser()
	{
	}

	public static FragmentUser newInstance()
	{
		return new FragmentUser();
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
		View rootView = inflater.inflate(R.layout.fragment_main, container, false);
		mUserService = new UserService(this.getContext());
		mUserService.addDataResponse(this);
		viewInit(rootView);
		if (!EventBus.getDefault().isRegistered(this))
		{
			EventBus.getDefault().register(this);
		}
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
		tvTitle = (TextView) rootView.findViewById(R.id.tv_title);
		tvTitle.setText(getString(R.string.title_activity_user));
		ivRight = (ImageView) rootView.findViewById(R.id.iv_right);
		ivRight.setVisibility(View.VISIBLE);
		ivRight.setImageResource(R.drawable.ic_settings);
		ivRight.setOnClickListener(this);
		xListView = (XListView) rootView.findViewById(R.id.xlist);
		headView = LayoutInflater.from(this.getContext()).inflate(R.layout.fragment_user_head, null);
		xListView.setPullRefreshEnable(true);
		xListView.setPullLoadEnable(false);
		xListView.addHeaderView(headView);
		xListView.setAdapter(null);
		xListView.setXListViewListener(this);
		ivPhoto = (ImageView) rootView.findViewById(R.id.iv_pic);
		ivPhoto.setOnClickListener(this);
		tvName = (TextView) rootView.findViewById(R.id.tv_name);
		tvBalance = (TextView) rootView.findViewById(R.id.tv_banalce);
		rootView.findViewById(R.id.layout_user).setOnClickListener(this);
		rootView.findViewById(R.id.layout_wallet).setOnClickListener(this);
		rootView.findViewById(R.id.layout_redpack_log).setOnClickListener(this);
		rootView.findViewById(R.id.layout_maker).setOnClickListener(this);
		rootView.findViewById(R.id.layout_agent).setOnClickListener(this);
		rootView.findViewById(R.id.layout_guid).setOnClickListener(this);
		rootView.findViewById(R.id.layout_advice).setOnClickListener(this);
		rootView.findViewById(R.id.tv_exit).setOnClickListener(this);
		//		rootView.findViewById(R.id.btn_setting).setOnClickListener(this);
		layoutMakerApply = rootView.findViewById(R.id.layout_maker_apply);
		viewMakerApply = rootView.findViewById(R.id.view_maker_apply);
		layoutMaker = rootView.findViewById(R.id.layout_maker);
		viewMaker = rootView.findViewById(R.id.view_maker);
		layoutAgent = rootView.findViewById(R.id.layout_agent);
		viewAgent = rootView.findViewById(R.id.view_agent);
		layoutMakerApply.setOnClickListener(this);
		lvFunc = (GridView) rootView.findViewById(R.id.lvFunc);
		lvFunc.setNumColumns(COL_SIZE);
		lvFunc2 = (GridView) rootView.findViewById(R.id.lvFunc2);
		lvFunc2.setNumColumns(COL_SIZE);
		super.viewInit(rootView);
	}

	private void initData()
	{
		mFuncInfoList = new ArrayList<>();
		mFuncInfoList.add(0, new FuncInfo(R.drawable.ic_user_data, getString(R.string.func_user_data)));
		mFuncInfoList.add(1, new FuncInfo(R.drawable.ic_wallet, getString(R.string.func_wallet)));
		mFuncInfoList.add(2, new FuncInfo(R.drawable.ic_wallet, getString(R.string.func_redpack_log_list)));
		if (mGridFuncAdapter == null)
		{
			mGridFuncAdapter = new GridFuliFuncAdapter(getAttachedActivity(), mFuncInfoList);
			lvFunc.setAdapter(mGridFuncAdapter);
			lvFunc.setOnItemClickListener(new AdapterView.OnItemClickListener()
			{
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id)
				{
					if (!UserService.isLogin())
					{
						UserService.logout();
						return;
					}
					final Context context = FragmentUser.this.getContext();
					FuncInfo funcInfo = mFuncInfoList.get(position);
					if (funcInfo.getName().equals(getString(R.string.func_user_data)))
					{
						context.startActivity(new Intent(context, ActivityUser.class));
					}
					else if (funcInfo.getName().equals(getString(R.string.func_wallet)))
					{
						context.startActivity(new Intent(context, ActivityUserWallet.class));
					}
					else if (funcInfo.getName().equals(getString(R.string.func_redpack_log_list)))
					{
						context.startActivity(new Intent(context, ActivityUserRedpackLogs.class));
					}
				}
			});
		}
		else
		{
			mGridFuncAdapter.notifyDataSetChanged();
		}
		ViewHeightCalTools.setGridViewHeight(lvFunc, COL_SIZE, true);
		mFuncInfoList2 = new ArrayList<>();
		if (mUserInfo.getUserType() >= UserType.COUNTRY.getType())
		{
			mFuncInfoList2.add(new FuncInfo(R.drawable.ic_agent_service, getString(R.string.func_agent)));
		}
		if (mUserInfo.getMakerStatus() == StatusType.ENABLE.getStatus())
		{
			mFuncInfoList2.add(new FuncInfo(R.drawable.ic_maker_service, getString(R.string.func_maker)));
		}
		else
		{
			mFuncInfoList2.add(new FuncInfo(R.drawable.ic_maker_service, getString(R.string.func_maker_apply)));
		}
		mFuncInfoList2.add(new FuncInfo(R.drawable.ic_user_guid, getString(R.string.func_help)));
		mFuncInfoList2.add(new FuncInfo(R.drawable.ic_advice, getString(R.string.func_advice)));
		if (mGridFuncAdapter2 == null)
		{
			mGridFuncAdapter2 = new GridFuliFuncAdapter(getAttachedActivity(), mFuncInfoList2);
			lvFunc2.setAdapter(mGridFuncAdapter2);
			lvFunc2.setOnItemClickListener(new AdapterView.OnItemClickListener()
			{
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id)
				{
					if (!UserService.isLogin())
					{
						UserService.logout();
						return;
					}
					final Context context = FragmentUser.this.getContext();
					FuncInfo funcInfo = mFuncInfoList2.get(position);
					if (funcInfo.getName().equals(getString(R.string.func_agent)))
					{
						context.startActivity(new Intent(context, ActivityAgent.class));
					}
					else if (funcInfo.getName().equals(getString(R.string.func_maker)))
					{
						context.startActivity(new Intent(context, ActivityMaker.class));
					}
					else if (funcInfo.getName().equals(getString(R.string.func_maker_apply)))
					{
						context.startActivity(new Intent(context, ActivityMakerApply.class));
					}
					else if (funcInfo.getName().equals(getString(R.string.func_help)))
					{
						ActivityWebView.startWebActivity(context, getString(R.string.func_help), ApiConfig.getHelpUrl());
					}
					else if (funcInfo.getName().equals(getString(R.string.func_advice)))
					{
						context.startActivity(new Intent(context, ActivityAdvice.class));
					}
				}
			});
		}
		else
		{
			mGridFuncAdapter2.notifyDataSetChanged();
		}
		ViewHeightCalTools.setGridViewHeight(lvFunc2, COL_SIZE, true);
	}

	//刷新数据
	@Override
	synchronized public void refreshData()
	{
		mUserService.getMyUserInfo(null, true);
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
		final Context context = FragmentUser.this.getContext();
		switch (v.getId())
		{
			case R.id.iv_pic:
			{
				if (!UserService.isLogin())
				{
					UserService.logout();
				}
				else
				{
					EventBus.getDefault().post(UserEvent.CHANGE_HEADER);
				}
				break;
			}
			case R.id.layout_user:
			{
				if (!UserService.isLogin())
				{
					UserService.logout();
				}
				else
				{
					context.startActivity(new Intent(context, ActivityUser.class));
				}
				break;
			}
			case R.id.layout_wallet:
			{
				if (!UserService.isLogin())
				{
					UserService.logout();
				}
				else
				{
					context.startActivity(new Intent(context, ActivityUserWallet.class));
				}
				break;
			}
			case R.id.layout_redpack_log:
			{
				if (!UserService.isLogin())
				{
					UserService.logout();
				}
				else
				{
					context.startActivity(new Intent(context, ActivityUserRedpackLogs.class));
				}
				break;
			}
			case R.id.layout_maker_apply:
			{
				if (!UserService.isLogin())
				{
					UserService.logout();
				}
				else
				{
					context.startActivity(new Intent(context, ActivityMakerApply.class));
				}
				break;
			}
			case R.id.layout_maker:
			{
				if (!UserService.isLogin())
				{
					UserService.logout();
				}
				else
				{
					context.startActivity(new Intent(context, ActivityMaker.class));
				}
				break;
			}
			case R.id.layout_agent:
			{
				if (!UserService.isLogin())
				{
					UserService.logout();
				}
				else
				{
					context.startActivity(new Intent(context, ActivityAgent.class));
				}
				break;
			}
			case R.id.layout_guid:
			{
				if (!UserService.isLogin())
				{
					UserService.logout();
				}
				else
				{
					ActivityWebView.startWebActivity(context, getString(R.string.func_help), ApiConfig.getHelpUrl());
				}
				break;
			}
			case R.id.layout_advice:
			{
				if (!UserService.isLogin())
				{
					UserService.logout();
				}
				else
				{
					context.startActivity(new Intent(context, ActivityAdvice.class));
				}
				break;
			}
			case R.id.tv_exit:
			{
				UserService.logout();
				break;
			}
			case R.id.iv_right:
			{
				context.startActivity(new Intent(context, ActivitySettings.class));
				break;
			}
		}
	}

	@Override
	public boolean onAjaxDataResponse(String url, String result, AjaxStatus status)
	{
		xListView.stopRefresh();
		xListView.stopLoadMore();
		if (url.endsWith(ApiConfig.GET_MY_USER_INFO))
		{
			ReturnInfo returnInfo = GsonTools.getReturnInfo(result);
			if (ReturnInfo.isSuccess(returnInfo))
			{
				mUserInfo = GsonTools.getReturnObject(returnInfo, UserInfo.class);
				if (mUserInfo != null)
				{
					initData();
					if (mUserInfo.getUserType() >= UserType.COUNTRY.getType())
					{
						layoutAgent.setVisibility(View.VISIBLE);
						viewAgent.setVisibility(View.VISIBLE);
					}
					else
					{
						layoutAgent.setVisibility(View.GONE);
						viewAgent.setVisibility(View.GONE);
					}
					if (mUserInfo.getMakerStatus() == StatusType.ENABLE.getStatus())
					{
						layoutMaker.setVisibility(View.VISIBLE);
						viewMaker.setVisibility(View.VISIBLE);
						layoutMakerApply.setVisibility(View.GONE);
						viewMakerApply.setVisibility(View.GONE);
					}
					else
					{
						layoutMakerApply.setVisibility(View.VISIBLE);
						viewMakerApply.setVisibility(View.VISIBLE);
						layoutMaker.setVisibility(View.GONE);
						viewMaker.setVisibility(View.GONE);
					}
					tvTitle.setText(mUserInfo.getNickname() + getString(R.string.title_activity_user));
					tvName.setText(mUserInfo.getUserId());
					tvBalance.setText(StringTools.convertToRmb(mUserInfo.getBalance()) + "元  " + mUserInfo.getPoint() + "金币");
					int width = DisplayTools.getWindowWidth(this.getContext()) / 6;
					Picasso.with(this.getContext()).load(Uri.parse(ApiConfig.getServerPicUrl(mUserInfo.getPicId()))).placeholder(R.drawable.ic_header).error(R.drawable.ic_header).resize(width, width).into(ivPhoto);
				}
			}
		}
		return true;
	}

	@Subscribe
	public void onEventMainThread(final UserEvent userEvent)
	{
		if (userEvent != null)
		{
			if (userEvent.getEvent().equalsIgnoreCase(UserEvent.CHANGED_HEADER.getEvent()))
			{
				mUserService.getMyUserInfo(null, true);
			}
		}
	}
}
