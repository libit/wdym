/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.android.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.weiduyx.wdym.R;

import java.util.ArrayList;
import java.util.List;

import cn.lrapps.android.ui.adapter.SectionsPagerAdapter;
import cn.lrapps.utils.ConstValues;

public class ActivityDownloadAdInfo extends MyBaseActivity
{
	private static final String TAG = ActivityDownloadAdInfo.class.getSimpleName();
	private ViewPager viewPager;
	private String mAdId;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_download_ad_info);
		mAdId = getIntent().getStringExtra(ConstValues.DATA_AD_ID);
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
		FragmentDownloadAdInfo fragmentDownloadAdInfo = new FragmentDownloadAdInfo();
		fragmentDownloadAdInfo.setArguments(getIntent().getExtras());
		fragmentList.add(fragmentDownloadAdInfo);
		SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this.getSupportFragmentManager(), fragmentList, null);
		viewPager.setAdapter(sectionsPagerAdapter);
	}
}
