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
import com.squareup.picasso.Picasso;
import com.weiduyx.wdym.R;
import com.weiduyx.wdym.models.AdInfo;
import com.weiduyx.wdym.models.BookInfo;
import com.weiduyx.wdym.models.ClientBannerInfo;
import com.weiduyx.wdym.models.NewsInfo;
import com.weiduyx.wdym.models.ReturnInfo;
import com.weiduyx.wdym.models.TableData;
import com.weiduyx.wdym.models.UserInfo;
import com.weiduyx.wdym.services.ApiConfig;
import com.weiduyx.wdym.services.BookInfoService;
import com.weiduyx.wdym.services.ClientBannerService;
import com.weiduyx.wdym.services.IAjaxDataResponse;
import com.weiduyx.wdym.services.NewsService;
import com.weiduyx.wdym.services.UserRedpackService;
import com.weiduyx.wdym.services.UserService;

import java.util.ArrayList;
import java.util.List;

import cn.lrapps.android.ui.adapter.BookAdapter;
import cn.lrapps.android.ui.adapter.GridFuliFuncAdapter;
import cn.lrapps.enums.AdType;
import cn.lrapps.enums.ClientPosition;
import cn.lrapps.enums.ClientType;
import cn.lrapps.models.FuncInfo;
import cn.lrapps.models.IndexImgInfo;
import cn.lrapps.utils.GsonTools;
import cn.lrapps.utils.PreferenceUtils;
import cn.lrapps.utils.StringTools;
import cn.lrapps.utils.viewtools.DisplayTools;
import cn.lrapps.utils.viewtools.ViewHeightCalTools;

public class FragmentIndex extends MyBaseBannerPageFragment implements IAjaxDataResponse, View.OnClickListener
{
	private static final String TAG = FragmentIndex.class.getSimpleName();
	public static final int COL_SIZE = 4;
	private View headView, vNews, vBanner;
	private ImageView ivImg1, ivImg2, ivImg3, ivImg4;
	private TextView tvTitle, tvNews;
	private GridView gvAppList;
	private ListView lvBook;
	protected List<FuncInfo> mFuncInfoList;
	protected GridFuliFuncAdapter mGridFuncAdapter;
	private UserService mUserService;
	private ClientBannerService mClientBannerService;
	private NewsService mNewsService;
	private BookInfoService mBookInfoService;
	private UserRedpackService mUserRedpackService;
	private UserInfo mUserInfo;
	private List<BookInfo> mBookInfoList = new ArrayList<>();
	private BookAdapter mBookAdapter;

	public FragmentIndex()
	{
	}

	public static FragmentIndex newInstance()
	{
		return new FragmentIndex();
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
		mClientBannerService = new ClientBannerService(this.getContext());
		mClientBannerService.addDataResponse(this);
		mNewsService = new NewsService(this.getContext());
		mNewsService.addDataResponse(this);
		mBookInfoService = new BookInfoService(this.getContext());
		mBookInfoService.addDataResponse(this);
		mUserRedpackService = new UserRedpackService(this.getContext());
		mUserRedpackService.addDataResponse(this);
		viewInit(rootView);
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

	@Override
	protected void viewInit(View rootView)
	{
		tvTitle = (TextView) rootView.findViewById(R.id.tv_title);
		tvTitle.setText(getString(R.string.func_index));
		xListView = (XListView) rootView.findViewById(R.id.xlist);
		headView = LayoutInflater.from(this.getContext()).inflate(R.layout.fragment_index_head, null);
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
		lvBook = (ListView) rootView.findViewById(R.id.lvBook);
		ivImg1 = (ImageView) rootView.findViewById(R.id.iv_img1);
		ivImg2 = (ImageView) rootView.findViewById(R.id.iv_img2);
		ivImg3 = (ImageView) rootView.findViewById(R.id.iv_img3);
		ivImg4 = (ImageView) rootView.findViewById(R.id.iv_img4);
		ivImg1.setOnClickListener(this);
		ivImg2.setOnClickListener(this);
		ivImg3.setOnClickListener(this);
		ivImg4.setOnClickListener(this);
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
														ActivityWebView.startWebActivity(FragmentIndex.this.getContext(), "", content);
													}
													else
													{
														if (content.equals("zixun"))
														{
															//															ActivityMain.getInstance().setCurrentItem(ActivityMain.ARTICLE);
															FragmentIndex.this.getContext().startActivity(new Intent(FragmentIndex.this.getContext(), ActivityZixun.class));
														}
														else if (content.equals("movie"))
														{
															ActivityMain.getInstance().setCurrentItem(ActivityMain.JUAN);
														}
														else if (content.equals("fuli"))
														{
															ActivityMain.getInstance().setCurrentItem(ActivityMain.PROFIT);
														}
														else if (content.equals("zhuanxianjin"))
														{
															FragmentIndex.this.getContext().startActivity(new Intent(FragmentIndex.this.getContext(), ActivityDownloadAdList.class));
														}
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
		clientBannerService.getClientBannerInfoList(ClientPosition.PAGE_INDEX.getPosition(), ClientType.BUTTON.getType(), "", true);
	}

	//刷新数据
	@Override
	synchronized public void refreshData()
	{
		//		mUserService.getMyUserInfo(null, true);
		initData();
		mClientBannerService.getClientBannerInfoList(ClientPosition.PAGE_INDEX.getPosition(), ClientType.BANNER.getType(), null, true);
		//		mNewsService.getNewsInfoList(0, 4, null, true);
		mUserRedpackService.getNetAdList(0, 10, "", true);
		mBookInfoList.clear();
		if (mBookAdapter != null)
		{
			mBookAdapter.notifyDataSetChanged();
		}
		mBookAdapter = null;
		//获取更多
		loadMoreData();
	}

	//加载更多商品
	@Override
	synchronized public void loadMoreData()
	{
		mBookInfoService.getBookInfoList(mDataStart, getPageSize(), null, true);
	}

	@Override
	public void onClick(View v)
	{
		Context context = this.getContext();
		switch (v.getId())
		{
			case R.id.v_news:
			{
				startActivity(new Intent(FragmentIndex.this.getContext(), ActivityNewsManage.class));
				break;
			}
			case R.id.iv_img1:
			{
				if (!UserService.isLogin())
				{
					UserService.logout();
				}
				else
				{
					IndexImgInfo indexImgInfo = (IndexImgInfo) ivImg1.getTag();
					if (indexImgInfo != null)
					{
						String adId = indexImgInfo.getAdId();
						String title = indexImgInfo.getTitle();
						String url = indexImgInfo.getUrl();
						ActivityAdWebView.startWebActivity(context, adId, AdType.NET.getType(), title, url);
					}
				}
				break;
			}
			case R.id.iv_img2:
			{
				if (!UserService.isLogin())
				{
					UserService.logout();
				}
				else
				{
					IndexImgInfo indexImgInfo = (IndexImgInfo) ivImg2.getTag();
					if (indexImgInfo != null)
					{
						String adId = indexImgInfo.getAdId();
						String title = indexImgInfo.getTitle();
						String url = indexImgInfo.getUrl();
						ActivityAdWebView.startWebActivity(context, adId, AdType.NET.getType(), title, url);
					}
				}
				break;
			}
			case R.id.iv_img3:
			{
				if (!UserService.isLogin())
				{
					UserService.logout();
				}
				else
				{
					IndexImgInfo indexImgInfo = (IndexImgInfo) ivImg3.getTag();
					if (indexImgInfo != null)
					{
						String adId = indexImgInfo.getAdId();
						String title = indexImgInfo.getTitle();
						String url = indexImgInfo.getUrl();
						ActivityAdWebView.startWebActivity(context, adId, AdType.NET.getType(), title, url);
					}
				}
				break;
			}
			case R.id.iv_img4:
			{
				if (!UserService.isLogin())
				{
					UserService.logout();
				}
				else
				{
					IndexImgInfo indexImgInfo = (IndexImgInfo) ivImg4.getTag();
					if (indexImgInfo != null)
					{
						String adId = indexImgInfo.getAdId();
						String title = indexImgInfo.getTitle();
						String url = indexImgInfo.getUrl();
						ActivityAdWebView.startWebActivity(context, adId, AdType.NET.getType(), title, url);
					}
				}
				break;
			}
		}
	}

	synchronized private void refreshBookInfoList(List<BookInfo> bookInfoList)
	{
		if (bookInfoList == null || bookInfoList.size() < 1)
		{
			xListView.setPullLoadEnable(false);
			if (mBookInfoList.size() < 1)
			{
			}
			return;
		}
		xListView.setPullLoadEnable(bookInfoList.size() >= getPageSize());
		for (BookInfo bookInfo : bookInfoList)
		{
			mBookInfoList.add(bookInfo);
		}
		if (mBookAdapter == null)
		{
			final Context context = FragmentIndex.this.getContext();
			BookAdapter.IItemClick iItemClick = new BookAdapter.IItemClick()
			{
				@Override
				public void onItemClicked(View v, final BookInfo bookInfo)
				{
					if (bookInfo != null)
					{
						if (!StringTools.isNull(bookInfo.getUrl()))
						{
							ActivityWebView.startWebActivity(context, bookInfo.getName(), bookInfo.getUrl());
						}
					}
				}
			};
			mBookAdapter = new BookAdapter(context, mBookInfoList, iItemClick);
			lvBook.setAdapter(mBookAdapter);
		}
		else
		{
			mBookAdapter.notifyDataSetChanged();
		}
		ViewHeightCalTools.setListViewHeight(lvBook, true);
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
		else if (url.endsWith(ApiConfig.GET_BOOK_INFO_LIST))
		{
			xListView.stopRefresh();
			xListView.stopLoadMore();
			List<BookInfo> bookInfoList = null;
			TableData tableData = GsonTools.getObject(result, TableData.class);
			if (tableData != null)
			{
				bookInfoList = GsonTools.getObjects(GsonTools.toJson(tableData.getData()), new TypeToken<List<BookInfo>>()
				{
				}.getType());
			}
			refreshBookInfoList(bookInfoList);
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
				AdInfo adInfo1 = adInfoList.get(0), adInfo2 = null, adInfo3 = null, adInfo4 = null;
				if (adInfoList.size() > 1)
				{
					adInfo2 = adInfoList.get(1);
				}
				else
				{
					adInfo2 = adInfoList.get(adInfoList.size() - 1);
				}
				if (adInfoList.size() > 2)
				{
					adInfo3 = adInfoList.get(2);
				}
				else
				{
					adInfo3 = adInfoList.get(adInfoList.size() - 1);
				}
				if (adInfoList.size() > 3)
				{
					adInfo4 = adInfoList.get(3);
				}
				else
				{
					adInfo4 = adInfoList.get(adInfoList.size() - 1);
				}
				ivImg1.setTag(new IndexImgInfo(adInfo1.getAdId(), adInfo1.getTitle(), adInfo1.getUrl()));
				Picasso.with(this.getContext()).load(Uri.parse(ApiConfig.getServerPicUrl(adInfo1.getPicUrl()))).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(ivImg1);
				ivImg2.setTag(new IndexImgInfo(adInfo2.getAdId(), adInfo2.getTitle(), adInfo2.getUrl()));
				Picasso.with(this.getContext()).load(Uri.parse(ApiConfig.getServerPicUrl(adInfo2.getPicUrl()))).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(ivImg2);
				ivImg3.setTag(new IndexImgInfo(adInfo3.getAdId(), adInfo3.getTitle(), adInfo3.getUrl()));
				Picasso.with(this.getContext()).load(Uri.parse(ApiConfig.getServerPicUrl(adInfo3.getPicUrl()))).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(ivImg3);
				ivImg4.setTag(new IndexImgInfo(adInfo4.getAdId(), adInfo4.getTitle(), adInfo4.getUrl()));
				Picasso.with(this.getContext()).load(Uri.parse(ApiConfig.getServerPicUrl(adInfo4.getPicUrl()))).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(ivImg4);
			}
		}
		return true;
	}
}
