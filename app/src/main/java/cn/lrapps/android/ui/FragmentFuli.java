/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.android.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.androidquery.callback.AjaxStatus;
import com.external.xlistview.XListView;
import com.google.gson.reflect.TypeToken;
import com.weiduyx.wdym.R;
import com.weiduyx.wdym.models.AdInfo;
import com.weiduyx.wdym.models.BookInfo;
import com.weiduyx.wdym.models.ReturnInfo;
import com.weiduyx.wdym.models.TableData;
import com.weiduyx.wdym.models.UserInfo;
import com.weiduyx.wdym.models.UserRedpackLogInfo;
import com.weiduyx.wdym.services.ApiConfig;
import com.weiduyx.wdym.services.BookInfoService;
import com.weiduyx.wdym.services.IAjaxDataResponse;
import com.weiduyx.wdym.services.UserRedpackService;
import com.weiduyx.wdym.services.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.lrapps.android.ui.adapter.BookAdapter;
import cn.lrapps.android.ui.customer.ToastView;
import cn.lrapps.android.ui.dialog.DialogCommon;
import cn.lrapps.enums.AdType;
import cn.lrapps.utils.CryptoTools;
import cn.lrapps.utils.GsonTools;
import cn.lrapps.utils.StringTools;
import cn.lrapps.utils.viewtools.DisplayTools;
import cn.lrapps.utils.viewtools.ViewHeightCalTools;

public class FragmentFuli extends MyBasePageFragment implements IAjaxDataResponse, View.OnClickListener
{
	private static final String TAG = FragmentFuli.class.getSimpleName();
	private View vTitle, headView, vChoujiang;
	private TextView tvTitle;
	private ImageView ivRight;
	private ImageView ivChoujiang, ivChoujiang2;
	private ListView lvBook;
	private BookInfoService mBookInfoService;
	private UserRedpackService mUserRedpackService;
	private UserService mUserService;
	private UserInfo mUserInfo;
	private List<BookInfo> mBookInfoList = new ArrayList<>();
	private BookAdapter mBookAdapter;
	private final Handler mHandler = new Handler();
	private long animationTime = 0;

	public FragmentFuli()
	{
	}

	public static FragmentFuli newInstance()
	{
		return new FragmentFuli();
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
		mBookInfoService = new BookInfoService(this.getContext());
		mBookInfoService.addDataResponse(this);
		mUserRedpackService = new UserRedpackService(this.getContext());
		mUserRedpackService.addDataResponse(this);
		mUserService = new UserService(this.getContext());
		mUserService.addDataResponse(this);
		viewInit(rootView);
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

	@Override
	public void onResume()
	{
		super.onResume();
		onRefresh();
	}

	@Override
	protected void viewInit(View rootView)
	{
		vTitle = rootView.findViewById(R.id.view_title);
		vTitle.setVisibility(View.GONE);
		tvTitle = (TextView) rootView.findViewById(R.id.tv_title);
		tvTitle.setText(getString(R.string.func_fuli));
		ivRight = (ImageView) rootView.findViewById(R.id.iv_right);
		//		ivRight.setVisibility(View.VISIBLE);
		//		ivRight.setImageResource(R.drawable.ic_refresh);
		//		ivRight.setOnClickListener(this);
		xListView = (XListView) rootView.findViewById(R.id.xlist);
		headView = LayoutInflater.from(this.getContext()).inflate(R.layout.fragment_fuli_list_head, null);
		xListView.setPullRefreshEnable(true);
		xListView.setPullLoadEnable(false);
		xListView.addHeaderView(headView);
		xListView.setAdapter(null);
		xListView.setXListViewListener(this);
		vChoujiang = rootView.findViewById(R.id.view_choujiang);
		ivChoujiang = (ImageView) rootView.findViewById(R.id.iv_choujiang);
		ivChoujiang2 = (ImageView) rootView.findViewById(R.id.iv_choujiang2);
		lvBook = (ListView) rootView.findViewById(R.id.lvBook);
		rootView.findViewById(R.id.iv_choujiang2).setOnClickListener(this);
		rootView.findViewById(R.id.tv_account).setOnClickListener(this);
		rootView.findViewById(R.id.tv_rule).setOnClickListener(this);
		rootView.findViewById(R.id.tv_redpack).setOnClickListener(this);
		//设置图片的长宽，这里便于制作图片
		//		int width = DisplayTools.getWindowWidth(this.getContext());
		//		ViewGroup.LayoutParams layoutParams = ivChoujiang.getLayoutParams();
		//		layoutParams.width = width;
		//		layoutParams.height = width;
		//		ivChoujiang.setLayoutParams(layoutParams);
		int width = DisplayTools.getWindowWidth(this.getContext()) - DisplayTools.dip2px(this.getContext(), 40);
		{
			ViewGroup.LayoutParams layoutParams = vChoujiang.getLayoutParams();
			layoutParams.width = width;
			layoutParams.height = width;
			vChoujiang.setLayoutParams(layoutParams);
		}
		//		{
		//			ViewGroup.LayoutParams layoutParams = ivChoujiang2.getLayoutParams();
		//			layoutParams.width = width * 4 / 9;
		//			layoutParams.height = width * 4 / 9;
		//			ivChoujiang2.setLayoutParams(layoutParams);
		//		}
		setChoujiangAnimation();
		super.viewInit(rootView);
	}

	private void setChoujiangAnimation()
	{
		//动画
		Animation animation = AnimationUtils.loadAnimation(this.getContext(), R.anim.img_rotate);
		LinearInterpolator lin = new LinearInterpolator();//设置动画匀速运动
		animation.setInterpolator(lin);
		animation.setDuration(15000);
		ivChoujiang.startAnimation(animation);
	}

	private void clearChoujiangAnimation()
	{
		ivChoujiang.clearAnimation();
	}
	//	Animation animation;

	private void setChoujiangAnimation2()
	{
		//动画
		Animation animation = AnimationUtils.loadAnimation(this.getContext(), R.anim.img_rotate);
		LinearInterpolator lin = new LinearInterpolator();//设置动画匀速运动
		animation.setInterpolator(lin);
		animation.setDuration(499);
		ivChoujiang2.startAnimation(animation);
	}

	private void clearChoujiangAnimation2()
	{
		float degrees = 0;
		//		Animation animation = ivChoujiang2.getAnimation();
		//		if (animation != null)
		//		{
		//			animation.cancel();
		//			animation.setRepeatCount(1);
		//		}
		ivChoujiang2.clearAnimation();
		degrees = (animationTime % 499) * 360 / 499;
		//		ivChoujiang2.setScaleType(ImageView.ScaleType.MATRIX);
		Matrix matrix = new Matrix();
		matrix.setRotate(degrees, ivChoujiang2.getWidth() / 2, ivChoujiang2.getHeight() / 2);
		//		matrix.postTranslate(ivChoujiang2.getWidth(), ivChoujiang2.getHeight());
		ivChoujiang2.setImageMatrix(matrix);
	}

	//刷新数据
	@Override
	synchronized public void refreshData()
	{
		//		mUserService.getMyUserInfo(null, true);
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
		switch (v.getId())
		{
			case R.id.iv_choujiang2:
			{
				if (!UserService.isLogin())
				{
					UserService.logout();
				}
				else
				{
					if (ActivityMain.getInstance().mUserInfo == null)
					{
						ToastView.showCenterToast(this.getContext(), R.drawable.ic_do_fail, "登录信息已过期，请重新登录！");
						UserService.logout();
						return;
					}
					clearChoujiangAnimation();
					setChoujiangAnimation2();
					animationTime = Math.abs(new Random(System.currentTimeMillis()).nextInt() % 1000) + 4000;
					mHandler.postDelayed(new Thread("getBrandRedpack")
					{
						@Override
						public void run()
						{
							super.run();
							//							clearChoujiangAnimation2();
							mUserRedpackService.getBrandRedpack(ActivityMain.getInstance().mUserInfo.getWxOpenId(), CryptoTools.getMD5Str(ActivityMain.getInstance().mUserInfo.getWxOpenId() + System.currentTimeMillis()), "", true);
						}
					}, animationTime);
				}
				break;
			}
			case R.id.tv_account:
			{
				if (!UserService.isLogin())
				{
					UserService.logout();
				}
				else
				{
					ActivityMain.getInstance().setCurrentItem(ActivityMain.USER);
				}
				break;
			}
			case R.id.tv_rule:
			{
				if (!UserService.isLogin())
				{
					UserService.logout();
				}
				else
				{
					DialogCommon dialogCommon = new DialogCommon(this.getContext(), null, "抽奖规则", getString(R.string.brand_redpack_rule), true, false, false);
					dialogCommon.show();
				}
				break;
			}
			case R.id.tv_redpack:
			{
				if (!UserService.isLogin())
				{
					UserService.logout();
				}
				else
				{
					startActivity(new Intent(this.getContext(), ActivityUserRedpackLogs.class));
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
			final Context context = FragmentFuli.this.getContext();
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
		else if (url.endsWith(ApiConfig.GET_BRAND_REDPACK))
		{
			clearChoujiangAnimation2();
			ReturnInfo returnInfo = GsonTools.getReturnInfo(result);
			if (ReturnInfo.isSuccess(returnInfo))
			{
				final UserRedpackLogInfo userRedpackLogInfo = GsonTools.getReturnObject(returnInfo, UserRedpackLogInfo.class);
				if (userRedpackLogInfo != null)
				{
					final AdInfo adInfo = GsonTools.getObject(userRedpackLogInfo.getRemark(), AdInfo.class);
					String content = "";
					if (adInfo != null)
					{
						content = adInfo.getName() + "送您一个现金红包，金额：" + StringTools.convertToRmb(userRedpackLogInfo.getMoney()) + "元，请打开内容后等待30秒领取。";
					}
					DialogCommon dialogCommon = new DialogCommon(this.getContext(), new DialogCommon.LibitDialogListener()
					{
						@Override
						public void onOkClick()
						{
							ActivityAdWebView.startWebActivity(FragmentFuli.this.getContext(), userRedpackLogInfo.getId() + "", AdType.COMMON.getType(), adInfo.getTitle(), adInfo.getUrl());
							setChoujiangAnimation();
						}

						@Override
						public void onCancelClick()
						{
							setChoujiangAnimation();
						}
					}, "恭喜", content, true, false, false);
					dialogCommon.show();
				}
			}
			else
			{
				setChoujiangAnimation();
				String msg = "获取红包失败。";
				if (returnInfo != null)
				{
					msg = returnInfo.getMsg();
				}
				ToastView.showCenterToast(this.getContext(), R.drawable.ic_do_fail, msg);
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
		return true;
	}
}
