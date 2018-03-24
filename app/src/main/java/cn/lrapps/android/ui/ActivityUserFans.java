/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.android.ui;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.callback.AjaxStatus;
import com.external.xlistview.XListView;
import cn.lrapps.enums.SexType;
import cn.lrapps.utils.ConstValues;
import cn.lrapps.utils.GsonTools;
import com.squareup.picasso.Picasso;
import com.weiduyx.wdym.models.UserInfo;
import com.weiduyx.wdym.services.ApiConfig;
import com.weiduyx.wdym.services.FileService;
import com.weiduyx.wdym.services.IAjaxDataResponse;
import com.weiduyx.wdym.services.UserService;
import com.weiduyx.wdym.R;

public class ActivityUserFans extends MyBaseActivity implements XListView.IXListViewListener, IAjaxDataResponse
{
	private static final String TAG = ActivityUserFans.class.getSimpleName();
	private XListView xListView;
	private View headView;
	private ImageView ivPhoto;
	private TextView tvUserId, tvName, tvNickname, tvSex, tvArea;
	private UserService mUserService;
	private FileService mFileService;
	private String mUserId;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user);
		mUserId = getIntent().getStringExtra(ConstValues.DATA_USER_ID);
		mUserService = new UserService(this);
		mUserService.addDataResponse(this);
		mFileService = new FileService(this);
		mFileService.addDataResponse(this);
		viewInit();
		onRefresh();
	}

	@Override
	protected void viewInit()
	{
		super.viewInit();
		setBackButton();
		colorChange(R.drawable.bg_user);
		xListView = (XListView) findViewById(R.id.xlist);
		headView = LayoutInflater.from(this).inflate(R.layout.activity_user_fans_head, null);
		xListView.setPullRefreshEnable(true);
		xListView.setPullLoadEnable(false);
		xListView.addHeaderView(headView);
		xListView.setAdapter(null);
		xListView.setXListViewListener(this);
		ivPhoto = (ImageView) findViewById(R.id.iv_photo);
		tvUserId = (TextView) findViewById(R.id.tv_user_id);
		tvName = (TextView) findViewById(R.id.tv_name);
		tvNickname = (TextView) findViewById(R.id.tv_nickname);
		tvSex = (TextView) findViewById(R.id.tv_sex);
		tvArea = (TextView) findViewById(R.id.tv_area);
	}

	@Override
	public void onRefresh()
	{
		mUserService.getUserInfo(mUserId, null, true);
	}

	@Override
	public void onLoadMore()
	{
		new Handler().postDelayed(new Runnable()
		{
			@Override
			public void run()
			{
				xListView.stopLoadMore();
			}
		}, 2000);
	}

	@Override
	public boolean onAjaxDataResponse(String url, String result, AjaxStatus status)
	{
		xListView.stopRefresh();
		if (url.endsWith(ApiConfig.GET_USER_INFO))
		{
			UserInfo userInfo = GsonTools.getReturnObject(result, UserInfo.class);
			if (userInfo != null)
			{
				tvUserId.setText(userInfo.getUserId());
				tvName.setText(userInfo.getName());
				tvNickname.setText(userInfo.getNickname());
				tvSex.setText(SexType.getDesc(userInfo.getSex()));
				tvArea.setText(userInfo.getProvince() + userInfo.getCity() + userInfo.getAddress());
				Picasso.with(this).load(Uri.parse(ApiConfig.getServerPicUrl(userInfo.getPicId()))).placeholder(R.drawable.ic_header).error(R.drawable.ic_header).into(ivPhoto);
			}
			else
			{
				UserService.logout();
				finish();
			}
		}
		return true;
	}
}
