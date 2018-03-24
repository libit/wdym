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
import com.weiduyx.wdym.R;

import java.util.ArrayList;
import java.util.List;

public class ActivityUserPointExchangeLogs extends MyBaseActivity
{
	private static final String TAG = ActivityUserPointExchangeLogs.class.getSimpleName();
	private ViewPager viewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragments);
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
		FragmentUserPointExchangeLogList fragmentUserPointExchangeLogList = new FragmentUserPointExchangeLogList();
		fragmentUserPointExchangeLogList.setArguments(getIntent().getExtras());
		fragmentList.add(fragmentUserPointExchangeLogList);
		SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this.getSupportFragmentManager(), fragmentList, null);
		viewPager.setAdapter(sectionsPagerAdapter);
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
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
