/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.android.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.androidquery.callback.AjaxStatus;
import com.external.xlistview.XListView;
import com.google.gson.reflect.TypeToken;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.weiduyx.wdym.R;
import com.weiduyx.wdym.models.ClientBannerInfo;
import com.weiduyx.wdym.models.NewsInfo;
import com.weiduyx.wdym.models.ReturnInfo;
import com.weiduyx.wdym.models.TableData;
import com.weiduyx.wdym.models.UserInfo;
import com.weiduyx.wdym.services.ApiConfig;
import com.weiduyx.wdym.services.ClientBannerService;
import com.weiduyx.wdym.services.IAjaxDataResponse;
import com.weiduyx.wdym.services.NewsService;
import com.weiduyx.wdym.services.UserService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import cn.lrapps.android.ui.adapter.GridFuliFuncAdapter;
import cn.lrapps.enums.ClientPosition;
import cn.lrapps.enums.ClientType;
import cn.lrapps.enums.UserEvent;
import cn.lrapps.models.FuncInfo;
import cn.lrapps.utils.GsonTools;
import cn.lrapps.utils.PreferenceUtils;
import cn.lrapps.utils.StringTools;
import cn.lrapps.utils.viewtools.DisplayTools;
import cn.lrapps.utils.viewtools.ViewHeightCalTools;

public class FragmentFuncList extends MyBaseBannerPageFragment implements IAjaxDataResponse, View.OnClickListener
{
	private static final String TAG = FragmentFuncList.class.getSimpleName();
	public static final int COL_SIZE = 4;
	private View headView, vNews, vBanner;
	private TextView tvNews;
	private GridView gvAppList;
	private ListView lvArticle;
	protected List<FuncInfo> mFuncInfoList;
	protected GridFuliFuncAdapter mGridFuncAdapter;
	private UserService mUserService;
	private ClientBannerService mClientBannerService;
	private NewsService mNewsService;
	private UserInfo mUserInfo;

	public FragmentFuncList()
	{
	}

	public static FragmentFuncList newInstance()
	{
		return new FragmentFuncList();
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
		View rootView = inflater.inflate(R.layout.fragment_fuli_list, container, false);
		mUserService = new UserService(this.getContext());
		mUserService.addDataResponse(this);
		mClientBannerService = new ClientBannerService(this.getContext());
		mClientBannerService.addDataResponse(this);
		mNewsService = new NewsService(this.getContext());
		mNewsService.addDataResponse(this);
		viewInit(rootView);
		if (!EventBus.getDefault().isRegistered(this))
		{
			EventBus.getDefault().register(this);
		}
		//		initData();
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
		headView = LayoutInflater.from(this.getContext()).inflate(R.layout.fragment_func_list_head, null);
		xListView.setPullRefreshEnable(true);
		xListView.setPullLoadEnable(false);
		xListView.addHeaderView(headView);
		xListView.setAdapter(null);
		xListView.setXListViewListener(this);
		vBanner = rootView.findViewById(R.id.layout_banner_id);
		vNews = rootView.findViewById(R.id.v_news);
		tvNews = (TextView) rootView.findViewById(R.id.tv_news);
		bannerViewPager = (ViewPager) rootView.findViewById(R.id.banner_viewpager);
		bannerViewPagerTab = (SmartTabLayout) rootView.findViewById(R.id.banner_viewpagertab);
		setBannerWidthAndHeight(DisplayTools.getWindowWidth(this.getContext()), DisplayTools.getWindowWidth(this.getContext()) / 2);
		initBannerView();
		gvAppList = (GridView) rootView.findViewById(R.id.gvAppList);
		gvAppList.setNumColumns(COL_SIZE);
		vNews.setOnClickListener(this);
		lvArticle = (ListView) rootView.findViewById(R.id.lvArticle);
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
								mGridFuncAdapter = new GridFuliFuncAdapter(getAttachedActivity(), mFuncInfoList);
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
												if (!StringTools.isNull(content))
												{
													content = content.trim();
													if (content.startsWith("http"))
													{
														if (content.indexOf("?") > -1)
														{
															content = String.format("%s&userId=%s&sessionId=%s", content, PreferenceUtils.getInstance().getUserId(), PreferenceUtils.getInstance().getSessionId());
														}
														else
														{
															content = String.format("%s?userId=%s&sessionId=%s", content, PreferenceUtils.getInstance().getUserId(), PreferenceUtils.getInstance().getSessionId());
														}
													}
													ActivityWebView.startWebActivity(FragmentFuncList.this.getContext(), "", content);
												}
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
		clientBannerService.getClientBannerInfoList(ClientPosition.PAGE_BRAND_REDPACK.getPosition(), ClientType.BUTTON.getType(), "", true);
	}

	//刷新数据
	@Override
	synchronized public void refreshData()
	{
		mUserService.getMyUserInfo(null, true);
		initData();
		mClientBannerService.getClientBannerInfoList(ClientPosition.PAGE_BRAND_REDPACK.getPosition(), ClientType.BANNER.getType(), null, true);
		//		mNewsService.getNewsInfoList(0, 4, null, true);
		//获取更多
		loadMoreData();
	}

	//加载更多商品
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
		switch (v.getId())
		{
			case R.id.v_news:
			{
				startActivity(new Intent(FragmentFuncList.this.getContext(), ActivityNewsManage.class));
				break;
			}
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
		}
	}

	@Override
	public boolean onAjaxDataResponse(String url, String result, AjaxStatus status)
	{
		if (url.endsWith(ApiConfig.GET_MY_USER_INFO))
		{
			ReturnInfo returnInfo = GsonTools.getReturnInfo(result);
			if (ReturnInfo.isSuccess(returnInfo))
			{
				mUserInfo = GsonTools.getReturnObject(returnInfo, UserInfo.class);
				if (mUserInfo != null)
				{
					//					EventBus.getDefault().post(UserEvent.CHANGE_MAIN_TITLE.setData(mUserInfo.getNickname()));
				}
			}
		}
		else if (url.endsWith(ApiConfig.GET_CLIENT_BANNER_INFO_LIST))
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
		else if (url.endsWith(ApiConfig.GET_NEWS_LIST))
		{
			List<NewsInfo> newsInfoList = null;
			TableData tableData = GsonTools.getObject(result, TableData.class);
			if (tableData != null)
			{
				newsInfoList = GsonTools.getObjects(GsonTools.toJson(tableData.getData()), new TypeToken<List<NewsInfo>>()
				{
				}.getType());
			}
			if (newsInfoList != null && newsInfoList.size() > 0)
			{
				String news = "";
				for (NewsInfo newsInfo : newsInfoList)
				{
					news += newsInfo.getTitle() + "    ";
				}
				if (!StringTools.isNull(news))
				{
					vNews.setVisibility(View.VISIBLE);
					tvNews.setText(news);
				}
				else
				{
					vNews.setVisibility(View.GONE);
				}
			}
			else
			{
				vNews.setVisibility(View.GONE);
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
