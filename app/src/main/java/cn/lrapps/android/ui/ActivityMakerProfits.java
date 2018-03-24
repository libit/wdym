/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.android.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import cn.lrapps.android.ui.adapter.SectionsPagerAdapter;
import com.weiduyx.wdym.R;

import java.util.ArrayList;
import java.util.List;

public class ActivityMakerProfits extends MyBaseActivity
{
	private static final String TAG = ActivityMakerProfits.class.getSimpleName();
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
		FragmentMakerProfitList fragmentMakerProfitList = new FragmentMakerProfitList();
		fragmentMakerProfitList.setArguments(getIntent().getExtras());
		fragmentList.add(fragmentMakerProfitList);
		SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this.getSupportFragmentManager(), fragmentList, null);
		viewPager.setAdapter(sectionsPagerAdapter);
	}
}
