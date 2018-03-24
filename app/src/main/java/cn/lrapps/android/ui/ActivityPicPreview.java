/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.android.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.google.gson.reflect.TypeToken;
import cn.lrapps.android.ui.adapter.SectionsPagerAdapter;
import cn.lrapps.utils.ConstValues;
import cn.lrapps.utils.GsonTools;
import com.weiduyx.wdym.R;

import java.util.ArrayList;
import java.util.List;

public class ActivityPicPreview extends MyBaseActivity
{
	private static final String TAG = ActivityPicPreview.class.getSimpleName();
	private ViewPager viewPager;
	private String mImageUrl;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pic_preview);
		mImageUrl = getIntent().getStringExtra(ConstValues.DATA_PIC_URL_LIST);
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
		List<String> imageUrlList = GsonTools.getObjects(mImageUrl, new TypeToken<List<String>>()
		{
		}.getType());
		if (imageUrlList != null && imageUrlList.size() > 0)
		{
			for (String imageUrl : imageUrlList)
			{
				FragmentServerImage fragmentNewsWeb = FragmentServerImage.newInstance(imageUrl, null, ImageView.ScaleType.FIT_CENTER.name());
				fragmentList.add(fragmentNewsWeb);
			}
		}
		SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this.getSupportFragmentManager(), fragmentList, null);
		viewPager.setAdapter(sectionsPagerAdapter);
	}
}
