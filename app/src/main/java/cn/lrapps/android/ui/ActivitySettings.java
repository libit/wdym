/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.android.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.lrapps.android.ui.dialog.DialogSettingBugLevel;
import cn.lrapps.utils.PreferenceUtils;
import cn.lrapps.utils.apptools.SystemToolsFactory;
import com.weiduyx.wdym.services.UpdateService;
import com.weiduyx.wdym.services.UserService;
import com.weiduyx.wdym.R;

public class ActivitySettings extends MyBaseActivity implements View.OnClickListener
{
	private static final String TAG = ActivitySettings.class.getSimpleName();
	private TextView tvCurrentVersion;
	private ImageView ivBootStart;
	private SystemToolsFactory systemTools = SystemToolsFactory.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		viewInit();
	}

	@Override
	public void viewInit()
	{
		super.viewInit();
		setBackButton();
		tvCurrentVersion = (TextView) findViewById(R.id.app_setting_current_version);
		ivBootStart = (ImageView) findViewById(R.id.app_setting_boot_start_value);
		findViewById(R.id.layout_setting_ui).setOnClickListener(this);
		findViewById(R.id.layout_boot_start).setOnClickListener(this);
		findViewById(R.id.layout_about).setOnClickListener(this);
		findViewById(R.id.layout_bug_level).setOnClickListener(this);
		findViewById(R.id.layout_update).setOnClickListener(this);
		findViewById(R.id.layout_share).setOnClickListener(this);
		tvCurrentVersion.setText(systemTools.getVersionName());
		if (PreferenceUtils.getInstance().getBooleanValue(PreferenceUtils.IS_BOOT_START))
		{
			ivBootStart.setImageResource(R.drawable.btn_checked);
		}
		else
		{
			ivBootStart.setImageResource(R.drawable.btn_nocheck);
		}
		ivBootStart.setOnClickListener(this);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.layout_setting_ui:
			{
				startActivity(new Intent(this, ActivitySettingsUi.class));
				break;
			}
			case R.id.layout_boot_start:
			case R.id.app_setting_boot_start_value:
			{
				if (PreferenceUtils.getInstance().getBooleanValue(PreferenceUtils.IS_BOOT_START))
				{
					PreferenceUtils.getInstance().setBooleanValue(PreferenceUtils.IS_BOOT_START, false);
					ivBootStart.setImageResource(R.drawable.btn_nocheck);
				}
				else
				{
					PreferenceUtils.getInstance().setBooleanValue(PreferenceUtils.IS_BOOT_START, true);
					ivBootStart.setImageResource(R.drawable.btn_checked);
				}
				break;
			}
			case R.id.layout_about:
			{
				startActivity(new Intent(this, ActivityAbout.class));
				break;
			}
			case R.id.layout_bug_level:
			{
				new DialogSettingBugLevel(this, null).show();
				break;
			}
			case R.id.layout_update:
			{
				UpdateService updateService = new UpdateService(this);
				updateService.checkUpdate("正在检查更新", true);
				break;
			}
			case R.id.layout_share:
			{
				new UserService(this).share("请稍后...", true);
				break;
			}
		}
	}
}
