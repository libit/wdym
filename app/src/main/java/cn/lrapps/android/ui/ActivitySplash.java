/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.android.ui;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidquery.callback.AjaxStatus;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;
import com.weiduyx.wdym.R;
import com.weiduyx.wdym.models.ClientBannerInfo;
import com.weiduyx.wdym.models.ReturnInfo;
import com.weiduyx.wdym.models.TableData;
import com.weiduyx.wdym.services.ApiConfig;
import com.weiduyx.wdym.services.ClientBannerService;
import com.weiduyx.wdym.services.IAjaxDataResponse;
import com.weiduyx.wdym.services.UserService;

import java.util.List;

import cn.lrapps.enums.ClientPosition;
import cn.lrapps.enums.ClientType;
import cn.lrapps.utils.GsonTools;
import cn.lrapps.utils.PreferenceUtils;
import cn.lrapps.utils.StringTools;
import cn.lrapps.utils.apptools.AppFactory;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.PermissionUtils;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class ActivitySplash extends Activity implements IAjaxDataResponse
{
	private final int INIT_RESULT = 1001;
	private View rootView;
	private ImageView ivPic;
	private boolean isLogined = false;
	private boolean isGetUserInfo = false;
	private Boolean isInit = false;
	private UserService mUserService = new UserService(this);
	private ClientBannerService mClientBannerService = new ClientBannerService(this);
	private final Handler mHandler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			super.handleMessage(msg);
			switch (msg.what)
			{
				case INIT_RESULT:
				{
					synchronized (isInit)
					{
						isInit = true;
						if (isGetUserInfo)
						{
							if (!isLogined)
							{
								startActivity(new Intent(ActivitySplash.this, ActivityLogin.class));
							}
							else
							{
								startActivity(new Intent(ActivitySplash.this, ActivityMain.class));
							}
							finish();
						}
					}
					break;
				}
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		rootView = View.inflate(this, R.layout.activity_splash, null);
		setContentView(rootView);
		ivPic = (ImageView) findViewById(R.id.iv_pic);
		mUserService.addDataResponse(this);
		mClientBannerService.addDataResponse(this);
		String picUrl = PreferenceUtils.getInstance().getStringValue(PreferenceUtils.START_PIC_URL);
		if (!StringTools.isNull(picUrl))
		{
			Picasso.with(this).load(Uri.parse(picUrl)).error(R.drawable.welcome).into(ivPic);
		}
		else
		{
			ivPic.setImageResource(R.drawable.welcome);
		}
		mUserService.getMyUserInfo(null, true);
		mClientBannerService.getClientBannerInfoList(ClientPosition.PAGE_START.getPosition(), ClientType.BANNER.getType(), null, true);
		if (AppFactory.isCompatible(23))
		{
			ActivitySplashPermissionsDispatcher.initViewWithPermissionCheck(this);
		}
		else
		{
			initView2();
		}
	}

	@NeedsPermission({Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA})
	protected void initView()
	{
		initView2();
	}

	@OnPermissionDenied({Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA})
	protected void initViewDenied()
	{
		Toast.makeText(this, "您拒绝了应用所需的权限，应用将不能工作！", Toast.LENGTH_LONG).show();
		finish();
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
	{
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		// NOTE: delegate the permission handling to generated method
		ActivitySplashPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
		if (PermissionUtils.verifyPermissions(grantResults))
		{
			mHandler.sendEmptyMessage(INIT_RESULT);
		}
		else
		{
			initViewDenied();
		}
	}

	//	@NeedsPermission({Manifest.permission.SYSTEM_ALERT_WINDOW})
	//Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.INSTALL_PACKAGES, Manifest.permission.DELETE_PACKAGES, Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS
	protected void initView2()
	{
		AlphaAnimation aa = new AlphaAnimation(0.9f, 1.0f);
		aa.setDuration(2000);
		rootView.setAnimation(aa);
		aa.setAnimationListener(new Animation.AnimationListener()
		{
			@Override
			public void onAnimationStart(Animation animation)
			{
			}

			@Override
			public void onAnimationRepeat(Animation animation)
			{
			}

			@Override
			public void onAnimationEnd(Animation animation)
			{
				mHandler.sendEmptyMessage(INIT_RESULT);
			}
		});
	}

	@Override
	public boolean onAjaxDataResponse(String url, String result, AjaxStatus status)
	{
		if (url.endsWith(ApiConfig.GET_MY_USER_INFO))
		{
			ReturnInfo returnInfo = GsonTools.getReturnInfo(result);
			synchronized (isInit)
			{
				isGetUserInfo = true;
				isLogined = ReturnInfo.isSuccess(returnInfo);
				if (isInit)
				{
					if (!isLogined)
					{
						startActivity(new Intent(ActivitySplash.this, ActivityLogin.class));
					}
					else
					{
						startActivity(new Intent(ActivitySplash.this, ActivityMain.class));
					}
					finish();
				}
			}
		}
		else if (url.endsWith(ApiConfig.GET_CLIENT_BANNER_INFO_LIST))
		{
			List<ClientBannerInfo> clientBannerInfoList = null;
			TableData tableData = GsonTools.getObject(result, TableData.class);
			if (tableData != null)
			{
				clientBannerInfoList = GsonTools.getObjects(GsonTools.toJson(tableData.getData()), new TypeToken<List<ClientBannerInfo>>()
				{
				}.getType());
			}
			if (clientBannerInfoList != null && clientBannerInfoList.size() > 0)
			{
				final ClientBannerInfo clientBannerInfo = clientBannerInfoList.get(0);
				String picUrl = ApiConfig.getServerPicUrl(clientBannerInfo.getPicId());
				PreferenceUtils.getInstance().setStringValue(PreferenceUtils.START_PIC_URL, picUrl);
				Picasso.with(this).load(Uri.parse(picUrl)).into(ivPic);
				if (!StringTools.isNull(clientBannerInfo.getContent()))
				{
					if (clientBannerInfo.getContent().startsWith("http"))
					{
						ivPic.setOnClickListener(new View.OnClickListener()
						{
							@Override
							public void onClick(View v)
							{
								ActivityWebView.openUrl(ActivitySplash.this, clientBannerInfo.getContent());
							}
						});
					}
				}
			}
			else
			{
				PreferenceUtils.getInstance().setStringValue(PreferenceUtils.START_PIC_URL, "");
			}
		}
		return false;
	}
}
