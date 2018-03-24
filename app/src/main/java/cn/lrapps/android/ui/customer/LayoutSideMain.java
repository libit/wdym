/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.android.ui.customer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidquery.callback.AjaxStatus;
import com.squareup.picasso.Picasso;
import com.weiduyx.wdym.R;
import com.weiduyx.wdym.models.ReturnInfo;
import com.weiduyx.wdym.models.UserInfo;
import com.weiduyx.wdym.services.ApiConfig;
import com.weiduyx.wdym.services.IAjaxDataResponse;
import com.weiduyx.wdym.services.UserService;

import org.greenrobot.eventbus.EventBus;

import cn.lrapps.android.ui.ActivityChangePwd;
import cn.lrapps.android.ui.ActivityNewsManage;
import cn.lrapps.android.ui.ActivityUser;
import cn.lrapps.enums.EventTypeLayoutSideMain;
import cn.lrapps.enums.UserEvent;
import cn.lrapps.utils.GsonTools;
import cn.lrapps.utils.PreferenceUtils;

/**
 * Created by libit on 16/6/29.
 */
public class LayoutSideMain extends LinearLayout implements View.OnClickListener, IAjaxDataResponse
{
	private final Context mContext;
	private View rootView, vUser, vAccountInfo, vFuncs;
	private TextView tvName, tvCountValidateDate;
	private ImageView ivPhoto;
	private UserService mUserService;
	private UserInfo mUserInfo;

	public LayoutSideMain(Activity context)
	{
		super(context);
		this.mContext = context;
		viewInit();
	}

	public LayoutSideMain(Activity context, AttributeSet attrs)
	{
		super(context, attrs);
		this.mContext = context;
		viewInit();
	}

	private void viewInit()
	{
		mUserService = new UserService(mContext);
		mUserService.addDataResponse(this);
		rootView = LayoutInflater.from(mContext).inflate(R.layout.layout_side_main, null);
		vUser = rootView.findViewById(R.id.layout_user);
		vAccountInfo = rootView.findViewById(R.id.layout_account_info);
		vFuncs = rootView.findViewById(R.id.layout_funcs);
		tvName = (TextView) rootView.findViewById(R.id.tv_name);
		tvCountValidateDate = (TextView) rootView.findViewById(R.id.tv_count_validate_date);
		ivPhoto = (ImageView) rootView.findViewById(R.id.iv_photo);
		vUser.setOnClickListener(this);
		ivPhoto.setOnClickListener(this);
		rootView.findViewById(R.id.layout_user_info).setOnClickListener(this);
		rootView.findViewById(R.id.layout_news).setOnClickListener(this);
		rootView.findViewById(R.id.layout_change_password).setOnClickListener(this);
		rootView.findViewById(R.id.layout_logout).setOnClickListener(this);
		this.addView(rootView);
		refresh();
	}

	public void refresh()
	{
		if (UserService.isLogin())
		{
			tvName.setText(PreferenceUtils.getInstance().getUserId());
			//			vAccountInfo.setVisibility(VISIBLE);
			vFuncs.setVisibility(VISIBLE);
			mUserService.getMyUserInfo(null, true);
		}
		else
		{
			tvName.setText("访客");
			vFuncs.setVisibility(INVISIBLE);
			//			vAccountInfo.setVisibility(INVISIBLE);
		}
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.layout_user:
			case R.id.layout_user_info:
			{
				EventBus.getDefault().post(EventTypeLayoutSideMain.CLOSE_DRAWER);
				if (!UserService.isLogin())
				{
					UserService.logout();
				}
				else
				{
					mContext.startActivity(new Intent(mContext, ActivityUser.class));
				}
				break;
			}
			case R.id.iv_photo:
			{
				EventBus.getDefault().post(EventTypeLayoutSideMain.CLOSE_DRAWER);
				if (!UserService.isLogin())
				{
					UserService.logout();
				}
				else
				{
					EventBus.getDefault().post(UserEvent.CHANGE_HEADER);
				}
				break;
			}
			case R.id.layout_news:
			{
				EventBus.getDefault().post(EventTypeLayoutSideMain.CLOSE_DRAWER);
				if (!UserService.isLogin())
				{
					UserService.logout();
				}
				else
				{
					mContext.startActivity(new Intent(mContext, ActivityNewsManage.class));
				}
				break;
			}
			case R.id.layout_change_password:
			{
				EventBus.getDefault().post(EventTypeLayoutSideMain.CLOSE_DRAWER);
				if (!UserService.isLogin())
				{
					UserService.logout();
				}
				else
				{
					mContext.startActivity(new Intent(mContext, ActivityChangePwd.class));
				}
				break;
			}
			case R.id.layout_logout:
			{
				UserService.logout();
				break;
			}
		}
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
				Picasso.with(this.getContext()).load(Uri.parse(ApiConfig.getServerPicUrl(mUserInfo.getPicId()))).placeholder(R.drawable.ic_header).error(R.drawable.ic_header).into(ivPhoto);
			}
			else
			{
				tvCountValidateDate.setText("有效期至：获取失败。");
			}
		}
		return true;
	}
}
