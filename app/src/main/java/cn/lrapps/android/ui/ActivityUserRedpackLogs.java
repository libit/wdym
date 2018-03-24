/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.android.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.callback.AjaxStatus;
import cn.lrapps.android.ui.adapter.SectionsPagerAdapter;
import cn.lrapps.utils.GsonTools;
import cn.lrapps.utils.StringTools;
import com.squareup.picasso.Picasso;
import com.weiduyx.wdym.models.ReturnInfo;
import com.weiduyx.wdym.models.UserInfo;
import com.weiduyx.wdym.services.ApiConfig;
import com.weiduyx.wdym.services.IAjaxDataResponse;
import com.weiduyx.wdym.services.UserRedpackService;
import com.weiduyx.wdym.services.UserService;
import com.weiduyx.wdym.R;

import java.util.ArrayList;
import java.util.List;

public class ActivityUserRedpackLogs extends MyBaseActivity implements IAjaxDataResponse
{
	private static final String TAG = ActivityUserRedpackLogs.class.getSimpleName();
	private ViewPager viewPager;
	private ImageView ivHead;
	private TextView tvCount;
	private UserRedpackService mUserRedpackService;
	private UserService mUserService;
	private UserInfo mUserInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_redpack_logs);
		mUserRedpackService = new UserRedpackService(this);
		mUserRedpackService.addDataResponse(this);
		mUserService = new UserService(this);
		mUserService.addDataResponse(this);
		viewInit();
		mUserRedpackService.getUserRedpackLogInfoListCount(null, true);
		mUserService.getMyUserInfo(null, true);
	}

	@Override
	protected void viewInit()
	{
		super.viewInit();
		setBackButton();
		ivHead = (ImageView) findViewById(R.id.iv_photo);
		tvCount = (TextView) findViewById(R.id.tv_count);
		viewPager = (ViewPager) findViewById(R.id.viewpager);
		viewPager.setOffscreenPageLimit(1);
		List<Fragment> fragmentList = new ArrayList<>();
		FragmentUserRedpackLogList fragmentUserRedpackLogList = new FragmentUserRedpackLogList();
		fragmentUserRedpackLogList.setArguments(getIntent().getExtras());
		fragmentList.add(fragmentUserRedpackLogList);
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

	@Override
	public boolean onAjaxDataResponse(String url, String result, AjaxStatus status)
	{
		if (url.endsWith(ApiConfig.GET_MY_USER_INFO))
		{
			ReturnInfo returnInfo = GsonTools.getReturnInfo(result);
			if (ReturnInfo.isSuccess(returnInfo))
			{
				mUserInfo = GsonTools.getReturnObject(returnInfo, UserInfo.class);
				Picasso.with(this).load(Uri.parse(ApiConfig.getServerPicUrl(mUserInfo.getPicId()))).placeholder(R.drawable.ic_header).error(R.drawable.ic_header).into(ivHead);
			}
		}
		else if (url.endsWith(ApiConfig.GET_USER_REDPACK_LOG_INFO_LIST_COUNT))
		{
			if (!StringTools.isNull(result))
			{
				tvCount.setText(String.format("红包个数：%s个。", result));
			}
		}
		return true;
	}
}
