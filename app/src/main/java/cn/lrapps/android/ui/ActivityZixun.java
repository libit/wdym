/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.android.ui;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidquery.callback.AjaxStatus;
import com.google.gson.reflect.TypeToken;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.weiduyx.wdym.R;
import com.weiduyx.wdym.models.ClientBannerInfo;
import com.weiduyx.wdym.models.TableData;
import com.weiduyx.wdym.services.ApiConfig;
import com.weiduyx.wdym.services.ClientBannerService;
import com.weiduyx.wdym.services.IAjaxDataResponse;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import cn.lrapps.enums.ClientPosition;
import cn.lrapps.enums.ClientType;
import cn.lrapps.enums.UserEvent;
import cn.lrapps.models.TabInfo;
import cn.lrapps.utils.ConstValues;
import cn.lrapps.utils.GsonTools;
import cn.lrapps.utils.StringTools;
import cn.lrapps.utils.viewtools.DisplayTools;

public class ActivityZixun extends MyBaseActivity
{
	private static final String TAG = ActivityZixun.class.getSimpleName();
	private final List<TabInfo> mTabInfoList = new ArrayList<>();
	private ViewPager viewPager;
	private String mTypeId;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_zixun);
		mTypeId = getIntent().getStringExtra(ConstValues.DATA_TYPE_ID);
		viewInit();
	}

	@Override
	protected void viewInit()
	{
		super.viewInit();
		setBackButton();
		//设置滑动返回区域
		getSwipeBackLayout().setEdgeSize(DisplayTools.getWindowWidth(this) / 20);
		initData();
	}

	private void initData()
	{
		ClientBannerService clientBannerService = new ClientBannerService(this);
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
							int index = 0;
							for (ClientBannerInfo clientBannerInfo : clientBannerInfoList)
							{
								mTabInfoList.add(new TabInfo(index, clientBannerInfo.getName(), FragmentThirdArticleList2.class));
								index++;
							}
						}
						ViewGroup tab = (ViewGroup) findViewById(R.id.tab);
						//加载tab布局
						tab.addView(LayoutInflater.from(ActivityZixun.this).inflate(R.layout.layout_tab_text, tab, false));
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
						FragmentPagerItems pages = new FragmentPagerItems(ActivityZixun.this);
						int selected = 0;
						for (int i = 0; i < mTabInfoList.size(); i++)
						{
							TabInfo tabInfo = mTabInfoList.get(i);
							Bundle bundle = new Bundle();
							bundle.putString(ConstValues.DATA_TYPE_ID, clientBannerInfoList.get(i).getContent());
							pages.add(FragmentPagerItem.of(tabInfo.getLabel(), tabInfo.getLoadClass(), bundle));
							if (!StringTools.isNull(clientBannerInfoList.get(i).getContent()) && clientBannerInfoList.get(i).getContent().equals(mTypeId))
							{
								selected = i;
							}
						}
						FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getSupportFragmentManager(), pages);
						viewPager.setAdapter(adapter);
						viewPagerTab.setViewPager(viewPager);
						viewPager.setCurrentItem(selected);
						mTabInfoList.get(selected).getTvLabel().setTextColor(getResources().getColor(R.color.tab_text_enabled));
					}
				}
				return false;
			}
		});
		clientBannerService.getClientBannerInfoList(ClientPosition.PAGE_ZIXUN.getPosition(), ClientType.BUTTON.getType(), "", true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.menu_refresh, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		int id = item.getItemId();
		if (id == R.id.action_refresh)
		{
			EventBus.getDefault().post(UserEvent.REFRESH);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
