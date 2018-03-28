/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.android.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.callback.AjaxStatus;
import com.external.xlistview.XListView;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.squareup.picasso.Picasso;
import com.weiduyx.wdym.R;
import com.weiduyx.wdym.models.UserInfo;
import com.weiduyx.wdym.services.ApiConfig;
import com.weiduyx.wdym.services.IAjaxDataResponse;
import com.weiduyx.wdym.services.UserBalanceLogInfoService;
import com.weiduyx.wdym.services.UserPointExchangeService;
import com.weiduyx.wdym.services.UserService;
import com.weiduyx.wdym.services.UserWithdrawService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import cn.lrapps.enums.MoneyUnit;
import cn.lrapps.enums.UserBalanceLogType;
import cn.lrapps.enums.UserEvent;
import cn.lrapps.models.TabInfo;
import cn.lrapps.utils.GsonTools;
import cn.lrapps.utils.StringTools;

public class ActivityUserWallet extends MyBaseActivity implements View.OnClickListener, XListView.IXListViewListener, IAjaxDataResponse
{
	private static final String TAG = ActivityUserWallet.class.getSimpleName();
	private XListView xListView;
	private View headView;
	private ImageView ivPhoto;
	private TextView tvUserId, tvBalance, tvPoint, tvTodayProfitMoney, tvTodayProfitPoint, tvProfitMoney, tvProfitPoint, tvLeftBalance, tvLeftPoint, tvTotalWithdraw, tvTodayRedpackMoney, tvRedpackMoney, tvTodayRedpackPoint, tvRedpackPoint, tvTodayShareRedpackMoney, tvShareRedpackMoney, tvTodayShareRedpackPoint, tvShareRedpackPoint, tvTodayRedpackProfitMoney, tvRedpackProfitMoney, tvTodayRedpackProfitPoint, tvRedpackProfitPoint, tvTodayShareMoney, tvShareMoney, tvTodaySharePoint, tvSharePoint, tvTodayWithdrawMoney, tvWithdrawMoney, tvTodayRechargeMoney, tvRechargeMoney, tvTodayExchangePoint, tvExchangePoint, tvTodayExchangeMoney, tvExchangeMoney;
	private UserService mUserService;
	private UserBalanceLogInfoService mUserBalanceLogInfoService;
	private final List<TabInfo> mTabInfoList = new ArrayList<>();
	private ViewPager viewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_wallet);
		mUserService = new UserService(this);
		mUserService.addDataResponse(this);
		mUserBalanceLogInfoService = new UserBalanceLogInfoService(this);
		mUserBalanceLogInfoService.addDataResponse(this);
		viewInit();
		if (!EventBus.getDefault().isRegistered(this))
		{
			EventBus.getDefault().register(this);
		}
		onRefresh();
	}

	@Override
	protected void onDestroy()
	{
		if (EventBus.getDefault().isRegistered(this))
		{
			EventBus.getDefault().unregister(this);
		}
		super.onDestroy();
	}

	@Override
	protected void viewInit()
	{
		super.viewInit();
		setBackButton();
		xListView = (XListView) findViewById(R.id.xlist);
		headView = LayoutInflater.from(this).inflate(R.layout.activity_user_wallet_head, null);
		xListView.setPullRefreshEnable(true);
		xListView.setPullLoadEnable(false);
		xListView.addHeaderView(headView);
		xListView.setAdapter(null);
		xListView.setXListViewListener(this);
		ivPhoto = (ImageView) findViewById(R.id.iv_photo);
		ivPhoto.setOnClickListener(this);
		tvUserId = (TextView) findViewById(R.id.tv_user_id);
		tvBalance = (TextView) findViewById(R.id.tv_balance);
		tvPoint = (TextView) findViewById(R.id.tv_point);
		tvTodayProfitMoney = (TextView) findViewById(R.id.tv_today_profit_money);
		tvTodayProfitPoint = (TextView) findViewById(R.id.tv_today_profit_point);
		tvProfitMoney = (TextView) findViewById(R.id.tv_profit_money);
		tvProfitPoint = (TextView) findViewById(R.id.tv_profit_point);
		tvLeftBalance = (TextView) findViewById(R.id.tv_left_money);
		tvLeftPoint = (TextView) findViewById(R.id.tv_left_point);
		tvTotalWithdraw = (TextView) findViewById(R.id.tv_total_withdraw);
		tvTodayRedpackMoney = (TextView) findViewById(R.id.tv_today_redpack_money);
		tvRedpackMoney = (TextView) findViewById(R.id.tv_redpack_money);
		tvTodayRedpackPoint = (TextView) findViewById(R.id.tv_today_redpack_point);
		tvRedpackPoint = (TextView) findViewById(R.id.tv_redpack_point);
		tvTodayShareRedpackMoney = (TextView) findViewById(R.id.tv_today_share_redpack_money);
		tvShareRedpackMoney = (TextView) findViewById(R.id.tv_share_redpack_money);
		tvTodayShareRedpackPoint = (TextView) findViewById(R.id.tv_today_share_redpack_point);
		tvShareRedpackPoint = (TextView) findViewById(R.id.tv_share_redpack_point);
		tvTodayRedpackProfitMoney = (TextView) findViewById(R.id.tv_today_redpack_profit_money);
		tvRedpackProfitMoney = (TextView) findViewById(R.id.tv_redpack_profit_money);
		tvTodayRedpackProfitPoint = (TextView) findViewById(R.id.tv_today_redpack_profit_point);
		tvRedpackProfitPoint = (TextView) findViewById(R.id.tv_redpack_profit_point);
		tvTodayShareMoney = (TextView) findViewById(R.id.tv_today_share_money);
		tvShareMoney = (TextView) findViewById(R.id.tv_share_money);
		tvTodaySharePoint = (TextView) findViewById(R.id.tv_today_share_point);
		tvSharePoint = (TextView) findViewById(R.id.tv_today_point);
		tvTodayWithdrawMoney = (TextView) findViewById(R.id.tv_today_withdraw_money);
		tvWithdrawMoney = (TextView) findViewById(R.id.tv_withdraw_money);
		tvTodayRechargeMoney = (TextView) findViewById(R.id.tv_today_recharge_money);
		tvRechargeMoney = (TextView) findViewById(R.id.tv_recharge_money);
		tvTodayExchangePoint = (TextView) findViewById(R.id.tv_today_exchange_point);
		tvExchangePoint = (TextView) findViewById(R.id.tv_exchange_point);
		tvTodayExchangeMoney = (TextView) findViewById(R.id.tv_today_exchange_money);
		tvExchangeMoney = (TextView) findViewById(R.id.tv_exchange_money);
		findViewById(R.id.btn_withdraw).setOnClickListener(this);
		findViewById(R.id.btn_point_exchange).setOnClickListener(this);
		//初始化Tab
		mTabInfoList.add(new TabInfo(0, "红包收入", FragmentUserRedpackBalanceLogList.class));
		mTabInfoList.add(new TabInfo(1, "提现记录", FragmentUserWithdrawList.class));
		mTabInfoList.add(new TabInfo(2, "金币兑换记录", FragmentUserPointExchangeLogList.class));
		ViewGroup tab = (ViewGroup) findViewById(R.id.tab);
		//加载tab布局
		tab.addView(LayoutInflater.from(this).inflate(R.layout.layout_tab_text, tab, false));
		viewPager = (ViewPager) findViewById(R.id.viewpager);
		final SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
		viewPagerTab.setCustomTabView(new SmartTabLayout.TabProvider()
		{
			@Override
			public View createTabView(ViewGroup container, int position, PagerAdapter adapter)
			{
				TabInfo tabInfo = mTabInfoList.get(position);
				View view = LayoutInflater.from(viewPagerTab.getContext()).inflate(R.layout.item_tab_text, container, false);
				TextView textView = (TextView) view.findViewById(R.id.tab_label);
				textView.setText(tabInfo.getLabel());
				tabInfo.setTvLabel(textView);
				return view;
			}
		});
		viewPagerTab.setOnPageChangeListener(new ViewPager.OnPageChangeListener()
		{
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
			{
			}

			@Override
			public void onPageSelected(int position)
			{
				int size = mTabInfoList.size();
				for (int i = 0; i < size; i++)
				{
					TabInfo tabInfo = mTabInfoList.get(i);
					if (i == position)
					{
						tabInfo.getTvLabel().setTextColor(getResources().getColor(R.color.tab_text_enabled));
					}
					else
					{
						tabInfo.getTvLabel().setTextColor(getResources().getColor(R.color.tab_text_disabled));
					}
				}
			}

			@Override
			public void onPageScrollStateChanged(int state)
			{
			}
		});
		FragmentPagerItems pages = new FragmentPagerItems(this);
		for (TabInfo tabInfo : mTabInfoList)
		{
			pages.add(FragmentPagerItem.of(tabInfo.getLabel(), tabInfo.getLoadClass()));
		}
		FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getSupportFragmentManager(), pages);
		viewPager.setAdapter(adapter);
		viewPagerTab.setViewPager(viewPager);
		mTabInfoList.get(0).getTvLabel().setTextColor(getResources().getColor(R.color.tab_text_enabled));
	}

	@Override
	public void onRefresh()
	{
		mUserService.getMyUserInfo(null, true);
		getTotalBalance(tvTodayProfitMoney, MoneyUnit.RMB.getType(), true);
		getTotalBalance(tvProfitMoney, MoneyUnit.RMB.getType(), false);
		getTotalBalance(tvTodayProfitPoint, MoneyUnit.JINBI.getType(), true);
		getTotalBalance(tvProfitPoint, MoneyUnit.JINBI.getType(), false);
		getTotalWithdrawMoney(tvTotalWithdraw);
		//广告红包
		getBalance(tvTodayRedpackMoney, UserBalanceLogType.RECEIVE_REDPACK.getType(), MoneyUnit.RMB.getType(), true);
		getBalance(tvRedpackMoney, UserBalanceLogType.RECEIVE_REDPACK.getType(), MoneyUnit.RMB.getType(), false);
		getBalance(tvTodayRedpackPoint, UserBalanceLogType.RECEIVE_REDPACK.getType(), MoneyUnit.JINBI.getType(), true);
		getBalance(tvRedpackPoint, UserBalanceLogType.RECEIVE_REDPACK.getType(), MoneyUnit.JINBI.getType(), false);
		//分享广告红包
		getBalance(tvTodayShareRedpackMoney, UserBalanceLogType.RECEIVE_SHARE_REDPACK.getType(), MoneyUnit.RMB.getType(), true);
		getBalance(tvShareRedpackMoney, UserBalanceLogType.RECEIVE_SHARE_REDPACK.getType(), MoneyUnit.RMB.getType(), false);
		getBalance(tvTodayShareRedpackPoint, UserBalanceLogType.RECEIVE_SHARE_REDPACK.getType(), MoneyUnit.JINBI.getType(), true);
		getBalance(tvShareRedpackPoint, UserBalanceLogType.RECEIVE_SHARE_REDPACK.getType(), MoneyUnit.JINBI.getType(), false);
		//返利
		getBalance(tvTodayRedpackProfitMoney, UserBalanceLogType.AD_REDPACK_PROFIT.getType(), MoneyUnit.RMB.getType(), true);
		getBalance(tvRedpackProfitMoney, UserBalanceLogType.AD_REDPACK_PROFIT.getType(), MoneyUnit.RMB.getType(), false);
		getBalance(tvTodayRedpackProfitPoint, UserBalanceLogType.AD_REDPACK_PROFIT.getType(), MoneyUnit.JINBI.getType(), true);
		getBalance(tvRedpackProfitPoint, UserBalanceLogType.AD_REDPACK_PROFIT.getType(), MoneyUnit.JINBI.getType(), false);
		//分享朋友圈
		getBalance(tvTodaySharePoint, UserBalanceLogType.SHARE_PROFIT.getType(), MoneyUnit.JINBI.getType(), true);
		getBalance(tvSharePoint, UserBalanceLogType.SHARE_PROFIT.getType(), MoneyUnit.JINBI.getType(), false);
		//提现
		getBalance(tvTodayWithdrawMoney, UserBalanceLogType.WITHDRAW.getType(), MoneyUnit.RMB.getType(), true);
		getBalance(tvWithdrawMoney, UserBalanceLogType.WITHDRAW.getType(), MoneyUnit.RMB.getType(), false);
		//充值
		getBalance(tvTodayRechargeMoney, UserBalanceLogType.RECHARGE_BALANCE.getType(), MoneyUnit.RMB.getType(), true);
		getBalance(tvRechargeMoney, UserBalanceLogType.RECHARGE_BALANCE.getType(), MoneyUnit.RMB.getType(), false);
		//兑换
		getTotalExchangePoint(tvTodayExchangePoint, true);
		getTotalExchangePoint(tvExchangePoint, false);
		getTotalExchangeMoney(tvTodayExchangeMoney, true);
		getTotalExchangeMoney(tvExchangeMoney, false);
	}

	private void getBalance(final TextView tv, String logType, final byte moneyUnit, boolean isToday)
	{
		UserBalanceLogInfoService userBalanceLogInfoService = new UserBalanceLogInfoService(this);
		userBalanceLogInfoService.addDataResponse(new IAjaxDataResponse()
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
		userBalanceLogInfoService.getBalanceLogMoney(logType, moneyUnit, isToday, "", true);
	}

	private void getTotalBalance(final TextView tv, final byte moneyUnit, boolean isToday)
	{
		UserBalanceLogInfoService userBalanceLogInfoService = new UserBalanceLogInfoService(this);
		userBalanceLogInfoService.addDataResponse(new IAjaxDataResponse()
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
		userBalanceLogInfoService.getTotalBalanceLogMoney(moneyUnit, isToday, "", true);
	}

	private void getTotalWithdrawMoney(final TextView tv)
	{
		UserWithdrawService userWithdrawService = new UserWithdrawService(this);
		userWithdrawService.addDataResponse(new IAjaxDataResponse()
		{
			@Override
			public boolean onAjaxDataResponse(String url, String result, AjaxStatus status)
			{
				tv.setText(StringTools.convertToRmb(Integer.parseInt(result)) + "元");
				return false;
			}
		});
		userWithdrawService.getTotalWithdrawMoney("", true);
	}

	private void getTotalExchangePoint(final TextView tv, boolean isToday)
	{
		UserPointExchangeService userPointExchangeService = new UserPointExchangeService(this);
		userPointExchangeService.addDataResponse(new IAjaxDataResponse()
		{
			@Override
			public boolean onAjaxDataResponse(String url, String result, AjaxStatus status)
			{
				tv.setText(result + "金币");
				return false;
			}
		});
		userPointExchangeService.getTotalExchangePoint(isToday, "", true);
	}

	private void getTotalExchangeMoney(final TextView tv, boolean isToday)
	{
		UserPointExchangeService userPointExchangeService = new UserPointExchangeService(this);
		userPointExchangeService.addDataResponse(new IAjaxDataResponse()
		{
			@Override
			public boolean onAjaxDataResponse(String url, String result, AjaxStatus status)
			{
				tv.setText(StringTools.convertToRmb(Integer.parseInt(result)) + "元");
				return false;
			}
		});
		userPointExchangeService.getTotalExchangeMoney(isToday, "", true);
	}

	@Override
	public void onLoadMore()
	{
		new Handler().postDelayed(new Runnable()
		{
			@Override
			public void run()
			{
				xListView.stopLoadMore();
			}
		}, 2000);
	}

	@Override
	public boolean onAjaxDataResponse(String url, String result, AjaxStatus status)
	{
		xListView.stopRefresh();
		if (url.endsWith(ApiConfig.GET_MY_USER_INFO))
		{
			UserInfo userInfo = GsonTools.getReturnObject(result, UserInfo.class);
			if (userInfo != null)
			{
				String userId = userInfo.getUserId();
				if (!StringTools.isNull(userInfo.getName()))
				{
					userId = userInfo.getName();
				}
				if (!StringTools.isNull(userInfo.getNickname()))
				{
					userId = userInfo.getNickname();
				}
				tvUserId.setText(userId);
				tvBalance.setText(StringTools.convertToRmb(userInfo.getBalance()) + "元");
				tvPoint.setText(userInfo.getPoint() + "金币");
				tvLeftBalance.setText(StringTools.convertToRmb(userInfo.getBalance()) + "元");
				tvLeftPoint.setText(userInfo.getPoint() + "金币");
				Picasso.with(this).load(Uri.parse(ApiConfig.getServerPicUrl(userInfo.getPicId()))).placeholder(R.drawable.ic_header).error(R.drawable.ic_header).into(ivPhoto);
			}
			else
			{
				UserService.logout();
				finish();
			}
		}
		return true;
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.btn_withdraw:
			{
				startActivity(new Intent(this, ActivityUserWithdraw.class));
				break;
			}
			case R.id.btn_point_exchange:
			{
				startActivity(new Intent(this, ActivityUserPointExchange.class));
				break;
			}
		}
	}

	@Subscribe
	public void onEventMainThread(final UserEvent userEvent)
	{
		runOnUiThread(new Thread()
		{
			@Override
			public void run()
			{
				super.run();
				if (userEvent != null)
				{
					if (userEvent.getEvent().equalsIgnoreCase(UserEvent.LOGOUT.getEvent()))
					{
						finish();
					}
					else if (userEvent.getEvent().equalsIgnoreCase(UserEvent.CHANGED_HEADER.getEvent()) || userEvent.getEvent().equalsIgnoreCase(UserEvent.CHANGED_DATA.getEvent()))
					{
						onRefresh();
					}
				}
			}
		});
	}
}
