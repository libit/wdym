/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.android.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.callback.AjaxStatus;
import com.external.xlistview.XListView;
import com.google.gson.reflect.TypeToken;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.weiduyx.wdym.R;
import com.weiduyx.wdym.models.AdInfo;
import com.weiduyx.wdym.models.ClientBannerInfo;
import com.weiduyx.wdym.models.MovieInfo;
import com.weiduyx.wdym.models.TableData;
import com.weiduyx.wdym.services.ApiConfig;
import com.weiduyx.wdym.services.ClientBannerService;
import com.weiduyx.wdym.services.IAjaxDataResponse;
import com.weiduyx.wdym.services.MovieInfoService;
import com.weiduyx.wdym.services.UserRedpackService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.lrapps.android.ui.adapter.GridFuliFuncAdapter;
import cn.lrapps.android.ui.adapter.MovieAdapter;
import cn.lrapps.enums.ClientPosition;
import cn.lrapps.enums.ClientType;
import cn.lrapps.models.FuncInfo;
import cn.lrapps.utils.GsonTools;
import cn.lrapps.utils.PreferenceUtils;
import cn.lrapps.utils.StringTools;
import cn.lrapps.utils.viewtools.DisplayTools;
import cn.lrapps.utils.viewtools.ViewHeightCalTools;

public class FragmentMovieList extends MyBaseBannerPageFragment implements IAjaxDataResponse, View.OnClickListener
{
	private static final String TAG = FragmentMovieList.class.getSimpleName();
	public static final int COL_SIZE = 5;
	public static final int MOVIE_COL_SIZE = 3;
	private View headView, vBanner;
	private TextView tvTitle;
	private ImageView ivRight;
	private GridView gvAppList;
	private GridView lvMovie, lvMovie2;
	protected List<FuncInfo> mFuncInfoList;
	protected GridFuliFuncAdapter mGridFuncAdapter;
	private ClientBannerService mClientBannerService;
	private MovieInfoService mMovieInfoService;
	private List<MovieInfo> mMovieInfoList = new ArrayList<>();
	private List<MovieInfo> mMovieInfoList2 = new ArrayList<>();
	private MovieAdapter mMovieAdapter;
	private MovieAdapter mMovieAdapter2;
	private String mSort;
	private UserRedpackService mUserRedpackService;
	private List<AdInfo> mAdInfoList = new ArrayList<>();
	private int netAdIndex = 0;

	public FragmentMovieList()
	{
	}

	public static FragmentMovieList newInstance()
	{
		return new FragmentMovieList();
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
		mClientBannerService = new ClientBannerService(this.getContext());
		mClientBannerService.addDataResponse(this);
		mMovieInfoService = new MovieInfoService(this.getContext());
		mMovieInfoService.addDataResponse(this);
		mUserRedpackService = new UserRedpackService(this.getContext());
		mUserRedpackService.addDataResponse(this);
		viewInit(rootView);
		onRefresh();
		//		initData();
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
	//	@Override
	//	public void onResume()
	//	{
	//		super.onResume();
	//		onRefresh();
	//	}

	@Override
	protected void viewInit(View rootView)
	{
		tvTitle = (TextView) rootView.findViewById(R.id.tv_title);
		tvTitle.setText(getString(R.string.func_movie));
		ivRight = (ImageView) rootView.findViewById(R.id.iv_right);
		ivRight.setVisibility(View.VISIBLE);
		ivRight.setImageResource(R.drawable.ic_refresh);
		ivRight.setOnClickListener(this);
		xListView = (XListView) rootView.findViewById(R.id.xlist);
		headView = LayoutInflater.from(this.getContext()).inflate(R.layout.fragment_movie_head, null);
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
		lvMovie = (GridView) rootView.findViewById(R.id.lvMovie);
		lvMovie.setNumColumns(MOVIE_COL_SIZE);
		lvMovie2 = (GridView) rootView.findViewById(R.id.lvMovie2);
		lvMovie2.setNumColumns(MOVIE_COL_SIZE);
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
														ActivityWebView.startWebActivity(FragmentMovieList.this.getContext(), "", content);
													}
													else
													{
														mSort = content;
													}
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
		clientBannerService.getClientBannerInfoList(ClientPosition.PAGE_FANJUAN.getPosition(), ClientType.BUTTON.getType(), "", true);
	}

	//刷新数据
	@Override
	synchronized public void refreshData()
	{
		initData();
		mClientBannerService.getClientBannerInfoList(ClientPosition.PAGE_FANJUAN.getPosition(), ClientType.BANNER.getType(), null, true);
		mUserRedpackService.getNetAdList(0, 10, "", true);
	}

	//加载更多商品
	@Override
	synchronized public void loadMoreData()
	{
		mMovieInfoService.getMovieInfoList(mDataStart, getPageSize(), null, true);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.iv_right:
			{
				onRefresh();
				break;
			}
		}
	}

	synchronized private void refreshMovieInfoList(List<MovieInfo> movieInfoList)
	{
		if (movieInfoList == null || movieInfoList.size() < 1)
		{
			xListView.setPullLoadEnable(false);
			if (mMovieInfoList.size() < 1)
			{
			}
			return;
		}
		xListView.setPullLoadEnable(mMovieInfoList.size() >= getPageSize());
		int index = 1;
		for (MovieInfo movieInfo : movieInfoList)
		{
			int page = 0, selected = 0;
			while (page < 2 || page > 3)
			{
				page = Math.abs(new Random(System.currentTimeMillis() + index + selected).nextInt() % 4);
				selected++;
			}
			if (index % page == 0)//每3篇文章加个广告
			{
				if (mAdInfoList.size() > netAdIndex)
				{
					AdInfo adInfo = mAdInfoList.get(netAdIndex);
					MovieInfo movieInfo1 = new MovieInfo();
					movieInfo1.setPicUrl(adInfo.getPicUrl());
					movieInfo1.setName(adInfo.getTitle());
					movieInfo1.setUrl(adInfo.getUrl());
					movieInfo1.setMovieId(adInfo.getAdId());
					movieInfo1.setAddDateLong(0);
					mMovieInfoList.add(movieInfo1);
				}
				else
				{
					netAdIndex = 0;
				}
			}
			mMovieInfoList.add(movieInfo);
			index++;
		}
		if (mMovieAdapter == null)
		{
			final Context context = FragmentMovieList.this.getContext();
			MovieAdapter.IItemClick iItemClick = new MovieAdapter.IItemClick()
			{
				@Override
				public void onItemClicked(View v, final MovieInfo movieInfo)
				{
					if (movieInfo != null)
					{
						ActivityWebView.startWebActivity(context, movieInfo.getName(), movieInfo.getUrl());
					}
				}
			};
			mMovieAdapter = new MovieAdapter(context, mMovieInfoList, iItemClick);
			lvMovie.setAdapter(mMovieAdapter);
		}
		else
		{
			mMovieAdapter.notifyDataSetChanged();
		}
		ViewHeightCalTools.setGridViewHeight(lvMovie, MOVIE_COL_SIZE, true);
	}

	synchronized private void refreshMovieInfoList2(List<MovieInfo> movieInfoList)
	{
		if (movieInfoList == null || movieInfoList.size() < 1)
		{
			xListView.setPullLoadEnable(false);
			if (mMovieInfoList2.size() < 1)
			{
			}
			return;
		}
		xListView.setPullLoadEnable(movieInfoList.size() >= getPageSize());
		int index = 1;
		for (MovieInfo movieInfo : movieInfoList)
		{
			int page = 0, selected = 0;
			while (page < 2 || page > 3)
			{
				page = Math.abs(new Random(System.currentTimeMillis() + index + selected).nextInt() % 4);
				selected++;
			}
			if (index % page == 0)//每3篇文章加个广告
			{
				if (mAdInfoList.size() > netAdIndex)
				{
					AdInfo adInfo = mAdInfoList.get(netAdIndex);
					MovieInfo movieInfo1 = new MovieInfo();
					movieInfo1.setPicUrl(adInfo.getPicUrl());
					movieInfo1.setName(adInfo.getTitle());
					movieInfo1.setUrl(adInfo.getUrl());
					movieInfo1.setMovieId(adInfo.getAdId());
					movieInfo1.setAddDateLong(0);
					mMovieInfoList2.add(movieInfo1);
				}
				else
				{
					netAdIndex = 0;
				}
			}
			mMovieInfoList2.add(movieInfo);
			index++;
		}
		if (mMovieAdapter2 == null)
		{
			final Context context = FragmentMovieList.this.getContext();
			MovieAdapter.IItemClick iItemClick = new MovieAdapter.IItemClick()
			{
				@Override
				public void onItemClicked(View v, final MovieInfo movieInfo)
				{
					if (movieInfo != null)
					{
						ActivityWebView.startWebActivity(context, movieInfo.getName(), movieInfo.getUrl());
					}
				}
			};
			mMovieAdapter2 = new MovieAdapter(context, mMovieInfoList2, iItemClick);
			lvMovie2.setAdapter(mMovieAdapter2);
		}
		else
		{
			mMovieAdapter2.notifyDataSetChanged();
		}
		ViewHeightCalTools.setGridViewHeight(lvMovie2, MOVIE_COL_SIZE, true);
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
		else if (url.endsWith(ApiConfig.GET_MOVIE_INFO_LIST))
		{
			xListView.stopRefresh();
			xListView.stopLoadMore();
			List<MovieInfo> movieInfoList = null;
			TableData tableData = GsonTools.getObject(result, TableData.class);
			if (tableData != null)
			{
				movieInfoList = GsonTools.getObjects(GsonTools.toJson(tableData.getData()), new TypeToken<List<MovieInfo>>()
				{
				}.getType());
			}
			refreshMovieInfoList(movieInfoList);
			refreshMovieInfoList2(movieInfoList);
		}
		else if (url.endsWith(ApiConfig.GET_NET_AD_LIST))
		{
			List<AdInfo> adInfoList = null;
			TableData tableData = GsonTools.getObject(result, TableData.class);
			if (tableData != null)
			{
				adInfoList = GsonTools.getObjects(GsonTools.toJson(tableData.getData()), new TypeToken<List<AdInfo>>()
				{
				}.getType());
			}
			if (adInfoList != null && adInfoList.size() > 0)
			{
				mAdInfoList = adInfoList;
			}
			mMovieInfoList.clear();
			mMovieInfoList2.clear();
			netAdIndex = 0;
			if (mMovieAdapter != null)
			{
				mMovieAdapter.notifyDataSetChanged();
			}
			mMovieAdapter2 = null;
			if (mMovieAdapter2 != null)
			{
				mMovieAdapter2.notifyDataSetChanged();
			}
			mMovieAdapter2 = null;
			//获取更多
			loadMoreData();
		}
		return true;
	}
}
