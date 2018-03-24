/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.android.ui;

import android.app.Activity;
import android.content.Context;
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
import com.weiduyx.wdym.models.AdInfo;
import com.weiduyx.wdym.models.ClientBannerInfo;
import com.weiduyx.wdym.models.TableData;
import com.weiduyx.wdym.models.ThirdArticleInfo;
import com.weiduyx.wdym.services.ApiConfig;
import com.weiduyx.wdym.services.ClientBannerService;
import com.weiduyx.wdym.services.IAjaxDataResponse;
import com.weiduyx.wdym.services.ThirdArticleInfoService;
import com.weiduyx.wdym.services.UserRedpackService;

import java.util.ArrayList;
import java.util.List;

import cn.lrapps.android.ui.adapter.GridFuliFuncAdapter;
import cn.lrapps.android.ui.adapter.ThirdArticleAdapter;
import cn.lrapps.enums.ClientPosition;
import cn.lrapps.enums.ClientType;
import cn.lrapps.models.FuncInfo;
import cn.lrapps.utils.ConstValues;
import cn.lrapps.utils.GsonTools;
import cn.lrapps.utils.PreferenceUtils;
import cn.lrapps.utils.StringTools;
import cn.lrapps.utils.viewtools.DisplayTools;
import cn.lrapps.utils.viewtools.ViewHeightCalTools;

public class FragmentThirdArticleList extends MyBaseBannerPageFragment implements IAjaxDataResponse, View.OnClickListener
{
	private static final String TAG = FragmentThirdArticleList.class.getSimpleName();
	public static final int COL_SIZE = 6;
	private View headView, vBanner;
	private TextView tvTitle;
	private ImageView ivRight;
	private GridView gvAppList;
	private ListView lvArticle;
	protected List<FuncInfo> mFuncInfoList;
	protected GridFuliFuncAdapter mGridFuncAdapter;
	private ClientBannerService mClientBannerService;
	private ThirdArticleInfoService mThirdArticleInfoService;
	private UserRedpackService mUserRedpackService;
	private List<ThirdArticleInfo> mThirdArticleInfoList = new ArrayList<>();
	private ThirdArticleAdapter mThirdArticleAdapter;
	private String mTypeId;
	private List<AdInfo> mAdInfoList = new ArrayList<>();
	private int netAdIndex = 0;

	public FragmentThirdArticleList()
	{
	}

	public static FragmentThirdArticleList newInstance()
	{
		return new FragmentThirdArticleList();
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
		mThirdArticleInfoService = new ThirdArticleInfoService(this.getContext());
		mThirdArticleInfoService.addDataResponse(this);
		mUserRedpackService = new UserRedpackService(this.getContext());
		mUserRedpackService.addDataResponse(this);
		viewInit(rootView);
		//		initData();
		onRefresh();
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
	//	public void onStart()
	//	{
	//		super.onStart();
	//		onRefresh();
	//	}
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
		tvTitle.setText(getString(R.string.func_article));
		ivRight = (ImageView) rootView.findViewById(R.id.iv_right);
		ivRight.setVisibility(View.VISIBLE);
		ivRight.setImageResource(R.drawable.ic_refresh);
		ivRight.setOnClickListener(this);
		xListView = (XListView) rootView.findViewById(R.id.xlist);
		headView = LayoutInflater.from(this.getContext()).inflate(R.layout.fragment_third_article_head, null);
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
														ActivityWebView.startWebActivity(FragmentThirdArticleList.this.getContext(), "", content);
													}
													else
													{
														mTypeId = content;
														//														onRefresh();
														Intent intent = new Intent(FragmentThirdArticleList.this.getContext(), ActivityZixun.class);
														intent.putExtra(ConstValues.DATA_TYPE_ID, mTypeId);
														FragmentThirdArticleList.this.getContext().startActivity(intent);
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
		clientBannerService.getClientBannerInfoList(ClientPosition.PAGE_ZIXUN.getPosition(), ClientType.BUTTON.getType(), "", true);
	}

	//刷新数据
	@Override
	synchronized public void refreshData()
	{
		initData();
		mUserRedpackService.getNetAdList(0, 10, "", true);
		mClientBannerService.getClientBannerInfoList(ClientPosition.PAGE_ZIXUN.getPosition(), ClientType.BANNER.getType(), null, true);
		mThirdArticleInfoList.clear();
		netAdIndex = 0;
		if (mThirdArticleAdapter != null)
		{
			mThirdArticleAdapter.notifyDataSetChanged();
		}
		mThirdArticleAdapter = null;
		//获取更多
		loadMoreData();
	}

	//加载更多商品
	@Override
	synchronized public void loadMoreData()
	{
		mThirdArticleInfoService.getThirdArticleInfoList(mTypeId, null, mDataStart, getPageSize(), null, true);
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

	synchronized private void refreshThirdArticleInfoList(List<ThirdArticleInfo> thirdArticleInfoList)
	{
		if (thirdArticleInfoList == null || thirdArticleInfoList.size() < 1)
		{
			xListView.setPullLoadEnable(false);
			if (mThirdArticleInfoList.size() < 1)
			{
			}
			return;
		}
		xListView.setPullLoadEnable(thirdArticleInfoList.size() >= getPageSize());
		int index = 1;
		for (ThirdArticleInfo thirdArticleInfo : thirdArticleInfoList)
		{
			if (index % 3 == 0)//每3篇文章加个广告
			{
				if (mAdInfoList.size() > netAdIndex)
				{
					AdInfo adInfo = mAdInfoList.get(netAdIndex);
					ThirdArticleInfo articleInfo = new ThirdArticleInfo();
					//					articleInfo.setLitpic(ApiConfig.getServerPicUrl(adInfo.getPicUrl()));
					articleInfo.setLitpic(adInfo.getPicUrl());
					articleInfo.setTitle(adInfo.getTitle());
					articleInfo.setSource(adInfo.getUrl());
					articleInfo.setFlag(adInfo.getAdId());
					articleInfo.setAddDateLong(0);
					mThirdArticleInfoList.add(articleInfo);
				}
				else
				{
					netAdIndex = 0;
				}
			}
			mThirdArticleInfoList.add(thirdArticleInfo);
			index++;
		}
		if (mThirdArticleAdapter == null)
		{
			final Context context = FragmentThirdArticleList.this.getContext();
			ThirdArticleAdapter.IItemClick iItemClick = new ThirdArticleAdapter.IItemClick()
			{
				@Override
				public void onItemClicked(View v, final ThirdArticleInfo thirdArticleInfo)
				{
					if (thirdArticleInfo != null)
					{
						ActivityWebView.startWebActivity(context, thirdArticleInfo.getTitle(), ApiConfig.getThirdArticleUrl(thirdArticleInfo.getId()));
					}
				}
			};
			mThirdArticleAdapter = new ThirdArticleAdapter(context, mThirdArticleInfoList, iItemClick);
			lvArticle.setAdapter(mThirdArticleAdapter);
		}
		else
		{
			mThirdArticleAdapter.notifyDataSetChanged();
		}
		ViewHeightCalTools.setListViewHeight(lvArticle, true);
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
		else if (url.endsWith(ApiConfig.GET_THIRD_ARTICLE_INFO_LIST))
		{
			xListView.stopRefresh();
			xListView.stopLoadMore();
			List<ThirdArticleInfo> thirdArticleInfoList = null;
			TableData tableData = GsonTools.getObject(result, TableData.class);
			if (tableData != null)
			{
				thirdArticleInfoList = GsonTools.getObjects(GsonTools.toJson(tableData.getData()), new TypeToken<List<ThirdArticleInfo>>()
				{
				}.getType());
			}
			refreshThirdArticleInfoList(thirdArticleInfoList);
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
		}
		return true;
	}
}
