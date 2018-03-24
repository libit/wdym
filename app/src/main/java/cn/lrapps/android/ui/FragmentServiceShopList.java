/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.android.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.androidquery.callback.AjaxStatus;
import com.external.xlistview.XListView;
import com.google.gson.reflect.TypeToken;
import cn.lrapps.android.ui.adapter.GridFuncAdapter;
import cn.lrapps.enums.ClientPosition;
import cn.lrapps.enums.ClientType;
import cn.lrapps.enums.UserEvent;
import cn.lrapps.models.FuncInfo;
import cn.lrapps.utils.GsonTools;
import cn.lrapps.utils.viewtools.DisplayTools;
import cn.lrapps.utils.viewtools.ViewHeightCalTools;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.weiduyx.wdym.models.ClientBannerInfo;
import com.weiduyx.wdym.models.TableData;
import com.weiduyx.wdym.services.ApiConfig;
import com.weiduyx.wdym.services.ClientBannerService;
import com.weiduyx.wdym.services.IAjaxDataResponse;
import com.weiduyx.wdym.services.UserService;
import com.weiduyx.wdym.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class FragmentServiceShopList extends MyBaseBannerPageFragment implements IAjaxDataResponse
{
	private static final String TAG = FragmentServiceShopList.class.getSimpleName();
	public static final int COL_SIZE = 3;
	private View headView, vBanner;
	private GridView gvAppList;
	protected List<FuncInfo> mFuncInfoList;
	protected GridFuncAdapter mGridFuncAdapter;
	private UserService mUserService;
	private ClientBannerService mClientBannerService;

	public FragmentServiceShopList()
	{
	}

	public static FragmentServiceShopList newInstance()
	{
		return new FragmentServiceShopList();
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
		View rootView = inflater.inflate(R.layout.fragment_service_shop_list, container, false);
		mUserService = new UserService(this.getContext());
		mUserService.addDataResponse(this);
		mClientBannerService = new ClientBannerService(this.getContext());
		mClientBannerService.addDataResponse(this);
		viewInit(rootView);
		if (!EventBus.getDefault().isRegistered(this))
		{
			EventBus.getDefault().register(this);
		}
		initData();
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
		xListView = (XListView) rootView.findViewById(R.id.xlist);
		headView = LayoutInflater.from(this.getContext()).inflate(R.layout.fragment_service_shop_list_head, null);
		xListView.setPullRefreshEnable(true);
		xListView.setPullLoadEnable(false);
		xListView.addHeaderView(headView);
		xListView.setAdapter(null);
		xListView.setXListViewListener(this);
		vBanner = rootView.findViewById(R.id.layout_banner_id);
		bannerViewPager = (ViewPager) rootView.findViewById(R.id.banner_viewpager);
		bannerViewPagerTab = (SmartTabLayout) rootView.findViewById(R.id.banner_viewpagertab);
		setBannerWidthAndHeight(DisplayTools.getWindowWidth(this.getContext()), DisplayTools.getWindowWidth(this.getContext()) / 2);
		initBannerView();
		gvAppList = (GridView) rootView.findViewById(R.id.gvAppList);
		gvAppList.setNumColumns(COL_SIZE);
		super.viewInit(rootView);
	}

	private void initData()
	{
		mFuncInfoList = new ArrayList<>();
		ClientBannerService clientBannerService = new ClientBannerService(this.getContext());
		clientBannerService.addDataResponse(new IAjaxDataResponse()
		{
			@Override
			public boolean onAjaxDataResponse(String url, String result, AjaxStatus status)
			{
				if (url.endsWith(ApiConfig.GET_CLIENT_BANNER_INFO_LIST))
				{
					TableData tableData = GsonTools.getObject(result, TableData.class);
					if (tableData != null)
					{
						final List<ClientBannerInfo> clientBannerInfoList = GsonTools.getObjects(GsonTools.toJson(tableData.getData()), new TypeToken<List<ClientBannerInfo>>()
						{
						}.getType());
						if (clientBannerInfoList != null && clientBannerInfoList.size() > 0)
						{
							mFuncInfoList.clear();
							for (ClientBannerInfo clientBannerInfo : clientBannerInfoList)
							{
								mFuncInfoList.add(new FuncInfo(ApiConfig.getServerPicUrl(clientBannerInfo.getPicId()), clientBannerInfo.getName()));
							}
							if (mGridFuncAdapter == null)
							{
								mGridFuncAdapter = new GridFuncAdapter(getAttachedActivity(), mFuncInfoList);
								gvAppList.setAdapter(mGridFuncAdapter);
								gvAppList.setOnItemClickListener(new AdapterView.OnItemClickListener()
								{
									@Override
									public void onItemClick(AdapterView<?> parent, View view, int position, long id)
									{
										FuncInfo funcInfo = mFuncInfoList.get(position);
										for (ClientBannerInfo clientBannerInfo : clientBannerInfoList)
										{
											if (funcInfo.getName().equals(clientBannerInfo.getName()))
											{
												String content = clientBannerInfo.getContent();
												ActivityWebView.startWebActivity(FragmentServiceShopList.this.getContext(), "", content);
											}
										}
									}
								});
							}
							else
							{
								mGridFuncAdapter.notifyDataSetChanged();
							}
							ViewHeightCalTools.setGridViewHeight(gvAppList, COL_SIZE, true);
						}
					}
				}
				return false;
			}
		});
		clientBannerService.getClientBannerInfoList(ClientPosition.PAGE_SERVICE_SHOP.getPosition(), ClientType.BUTTON.getType(), "", true);
	}

	//刷新数据
	@Override
	synchronized public void refreshData()
	{
		mClientBannerService.getClientBannerInfoList(ClientPosition.PAGE_SERVICE_SHOP.getPosition(), ClientType.BANNER.getType(), null, true);
		initData();
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
	synchronized public void onRefresh()
	{
		super.onRefresh();
	}

	@Override
	public boolean onAjaxDataResponse(String url, String result, AjaxStatus status)
	{
		if (url.endsWith(ApiConfig.GET_CLIENT_BANNER_INFO_LIST))
		{
			List<ClientBannerInfo> bannerInfoList = null;
			TableData tableData = GsonTools.getObject(result, TableData.class);
			if (tableData != null)
			{
				bannerInfoList = GsonTools.getObjects(GsonTools.toJson(tableData.getData()), new TypeToken<List<ClientBannerInfo>>()
				{
				}.getType());
			}
			if (bannerInfoList != null && bannerInfoList.size() > 0)
			{
				vBanner.setVisibility(View.VISIBLE);
			}
			else
			{
				vBanner.setVisibility(View.GONE);
			}
			setViewPagerAdapter(bannerInfoList, ImageView.ScaleType.FIT_XY.name());
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
