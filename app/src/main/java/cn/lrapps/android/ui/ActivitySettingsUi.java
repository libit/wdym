/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.android.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import cn.lrapps.utils.PreferenceUtils;
import com.weiduyx.wdym.R;

public class ActivitySettingsUi extends MyBaseActivity implements View.OnClickListener
{
	private static final String TAG = ActivitySettingsUi.class.getSimpleName();
	private ImageView ivShowNotification;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings_ui);
		viewInit();
	}

	@Override
	public void viewInit()
	{
		super.viewInit();
		setBackButton();
		ivShowNotification = (ImageView) findViewById(R.id.app_setting_show_notification_value);
		findViewById(R.id.layout_show_notification).setOnClickListener(this);
		if (PreferenceUtils.getInstance().getBooleanValue(PreferenceUtils.IS_SHOW_NOTIFICATION))
		{
			ivShowNotification.setImageResource(R.drawable.btn_checked);
		}
		else
		{
			ivShowNotification.setImageResource(R.drawable.btn_nocheck);
		}
		ivShowNotification.setOnClickListener(this);
	}

	private void initData()
	{
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		initData();
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			default:
			{
				break;
			}
		}
	}
}
