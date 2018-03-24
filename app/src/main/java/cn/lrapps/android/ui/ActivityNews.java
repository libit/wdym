/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.android.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import cn.lrapps.android.ui.adapter.SectionsPagerAdapter;
import cn.lrapps.utils.ConstValues;
import com.weiduyx.wdym.services.ApiConfig;
import com.weiduyx.wdym.R;

import java.util.ArrayList;
import java.util.List;

public class ActivityNews extends MyBaseActivity
{
	private static final String TAG = ActivityNews.class.getSimpleName();
	private ViewPager viewPager;
	private String mNewsId;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);
		mNewsId = getIntent().getStringExtra(ConstValues.DATA_NEWS_ID);
		viewInit();
	}

	@Override
	protected void viewInit()
	{
		super.viewInit();
		setBackButton();
		viewPager = (ViewPager) findViewById(R.id.viewpager);
		viewPager.setOffscreenPageLimit(1);
		List<Fragment> fragmentList = new ArrayList<>();
		FragmentNewsWeb fragmentNewsWeb = new FragmentNewsWeb();
		fragmentNewsWeb.setArguments(getIntent().getExtras());
		fragmentList.add(fragmentNewsWeb);
		SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this.getSupportFragmentManager(), fragmentList, null);
		viewPager.setAdapter(sectionsPagerAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.menu_activity_news, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		int id = item.getItemId();
		if (id == R.id.action_open_in_browser)
		{
			ActivityWebView.openUrl(this, ApiConfig.getNewsUrl(mNewsId));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
