/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.android.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.callback.AjaxStatus;
import cn.lrapps.utils.GsonTools;
import cn.lrapps.utils.apptools.SystemToolsFactory;
import cn.lrapps.utils.viewtools.DisplayTools;
import com.squareup.picasso.Picasso;
import com.weiduyx.wdym.models.ReturnInfo;
import com.weiduyx.wdym.services.ApiConfig;
import com.weiduyx.wdym.services.IAjaxDataResponse;
import com.weiduyx.wdym.services.UpdateService;
import com.weiduyx.wdym.services.UserService;
import com.weiduyx.wdym.R;

public class ActivityAbout extends MyBaseActivity implements View.OnClickListener, IAjaxDataResponse
{
	private TextView tvVersion;
	private ImageView ivDownloadQr;
	private UpdateService mUpdateService;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		mUpdateService = new UpdateService(this);
		mUpdateService.addDataResponse(this);
		viewInit();
		mUpdateService.getDownloadQr(null, true);
	}

	@Override
	protected void viewInit()
	{
		super.viewInit();
		setBackButton();
		tvVersion = (TextView) findViewById(R.id.tv_version);
		ivDownloadQr = (ImageView) findViewById(R.id.iv_download_qr);
		findViewById(R.id.layout_advice).setOnClickListener(this);
		findViewById(R.id.tv_browser_download).setOnClickListener(this);
		ivDownloadQr.setOnClickListener(this);
		tvVersion.setText(getString(R.string.app_name) + " V" + SystemToolsFactory.getInstance().getVersionName() + "_" + SystemToolsFactory.getInstance().getVersionCode());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.menu_activity_about, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		int id = item.getItemId();
		if (id == R.id.action_share)
		{
			new UserService(this).share("请稍后...", true);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.layout_advice:
			{
				startActivity(new Intent(this, ActivityAdvice.class));
				break;
			}
			case R.id.iv_download_qr:
			{
				ActivityWebView.openUrl(this, ApiConfig.getDownloadUrl());
				break;
			}
			case R.id.tv_browser_download:
			{
				ActivityWebView.openUrl(this, ApiConfig.getDownloadUrl());
				break;
			}
		}
	}

	@Override
	public boolean onAjaxDataResponse(String url, String result, AjaxStatus status)
	{
		if (url.endsWith(ApiConfig.GET_LAST_DOWNLOAD_QR))
		{
			ReturnInfo returnInfo = GsonTools.getReturnInfo(result);
			if (ReturnInfo.isSuccess(returnInfo))
			{
				int width = DisplayTools.getWindowWidth(ActivityAbout.this);
				ivDownloadQr.setImageResource(R.drawable.loading);
				ViewGroup.LayoutParams layoutParams = ivDownloadQr.getLayoutParams();
				layoutParams.width = width;
				layoutParams.height = width * 2 / 5;
				ivDownloadQr.setLayoutParams(layoutParams);
				ivDownloadQr.setPadding(DisplayTools.getWindowWidth(ActivityAbout.this) * 3 / 10, 0, DisplayTools.getWindowWidth(ActivityAbout.this) * 3 / 10, 0);
				Picasso.with(this).load(Uri.parse(ApiConfig.getServerPicUrl(returnInfo.getMsg()))).into(ivDownloadQr);
			}
		}
		return true;
	}
}
