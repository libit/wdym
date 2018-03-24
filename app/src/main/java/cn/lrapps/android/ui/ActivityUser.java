/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.android.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.callback.AjaxStatus;
import com.external.xlistview.XListView;
import cn.lrapps.android.ui.customer.ToastView;
import cn.lrapps.enums.EventTypeLayoutSideMain;
import cn.lrapps.enums.SexType;
import cn.lrapps.enums.StatusType;
import cn.lrapps.enums.UserEvent;
import cn.lrapps.enums.UserType;
import cn.lrapps.utils.ConstValues;
import cn.lrapps.utils.GsonTools;
import cn.lrapps.utils.LogTools;
import cn.lrapps.utils.StringTools;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.squareup.picasso.Picasso;
import com.weiduyx.wdym.models.PicInfo;
import com.weiduyx.wdym.models.ReturnInfo;
import com.weiduyx.wdym.models.UserInfo;
import com.weiduyx.wdym.services.ApiConfig;
import com.weiduyx.wdym.services.FileService;
import com.weiduyx.wdym.services.IAjaxDataResponse;
import com.weiduyx.wdym.services.UserService;
import com.weiduyx.wdym.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import static cn.lrapps.utils.ConstValues.IMAGE_PICKER;

public class ActivityUser extends MyBaseActivity implements View.OnClickListener, XListView.IXListViewListener, IAjaxDataResponse
{
	private static final String TAG = ActivityUser.class.getSimpleName();
	private XListView xListView;
	private View headView;
	private ImageView ivPhoto;
	private TextView tvUserId, tvName, tvNickname, tvEmail, tvRegisterDate, tvUserType, tvSex, tvAuth;
	private UserService mUserService;
	private FileService mFileService;
	private UserInfo mUserInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user);
		mUserService = new UserService(this);
		mUserService.addDataResponse(this);
		mFileService = new FileService(this);
		mFileService.addDataResponse(this);
		viewInit();
		if (!EventBus.getDefault().isRegistered(this))
		{
			EventBus.getDefault().register(this);
		}
		onRefresh();
	}

	@Override
	protected void onDestroy()
	{
		if (EventBus.getDefault().isRegistered(this))
		{
			EventBus.getDefault().unregister(this);
		}
		super.onDestroy();
	}

	@Override
	protected void viewInit()
	{
		super.viewInit();
		setBackButton();
		colorChange(R.drawable.bg_user);
		xListView = (XListView) findViewById(R.id.xlist);
		headView = LayoutInflater.from(this).inflate(R.layout.activity_user_head, null);
		xListView.setPullRefreshEnable(true);
		xListView.setPullLoadEnable(false);
		xListView.addHeaderView(headView);
		xListView.setAdapter(null);
		xListView.setXListViewListener(this);
		findViewById(R.id.btn_edit).setOnClickListener(this);
		findViewById(R.id.btn_logout).setOnClickListener(this);
		ivPhoto = (ImageView) findViewById(R.id.iv_photo);
		ivPhoto.setOnClickListener(this);
		tvUserId = (TextView) findViewById(R.id.tv_user_id);
		tvName = (TextView) findViewById(R.id.tv_name);
		tvEmail = (TextView) findViewById(R.id.tv_email);
		tvRegisterDate = (TextView) findViewById(R.id.tv_register_date);
		tvNickname = (TextView) findViewById(R.id.tv_nickname);
		tvUserType = (TextView) findViewById(R.id.tv_user_type);
		tvSex = (TextView) findViewById(R.id.tv_sex);
		tvAuth = (TextView) findViewById(R.id.tv_auth);
		tvAuth.setOnClickListener(this);
	}

	@Override
	public void onRefresh()
	{
		mUserService.getMyUserInfo(null, true);
	}

	@Override
	public void onLoadMore()
	{
		LogTools.debug(TAG, "onLoadMore:加载更多");
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
		if (url.endsWith(ApiConfig.GET_MY_USER_INFO))
		{
			mUserInfo = GsonTools.getReturnObject(result, UserInfo.class);
			if (mUserInfo != null)
			{
				tvUserId.setText(mUserInfo.getUserId());
				tvName.setText(mUserInfo.getName());
				tvNickname.setText(mUserInfo.getNickname());
				tvEmail.setText(mUserInfo.getEmail());
				tvRegisterDate.setText(StringTools.getTimeDate(mUserInfo.getAddDateLong()));
				tvUserType.setText(UserType.getDesc(mUserInfo.getUserType()));
				if (mUserInfo.getSex() != null)
				{
					tvSex.setText(SexType.getDesc(mUserInfo.getSex()));
				}
				else
				{
					tvSex.setText("未知");
				}
				tvAuth.setText(mUserInfo.getAuthStatus() == StatusType.ENABLE.getStatus() ? "已实名" : "未实名(点击实名)");
				Picasso.with(this).load(Uri.parse(ApiConfig.getServerPicUrl(mUserInfo.getPicId()))).placeholder(R.drawable.ic_header).error(R.drawable.ic_header).into(ivPhoto);
			}
			else
			{
				UserService.logout();
				finish();
			}
		}
		else if (url.endsWith(ApiConfig.UPLOAD_PIC))
		{
			ReturnInfo returnInfo = GsonTools.getReturnInfo(result);
			if (ReturnInfo.isSuccess(returnInfo))
			{
				PicInfo picInfo = GsonTools.getReturnObject(returnInfo, PicInfo.class);
				if (picInfo != null)
				{
					mUserService.updateUserHeader(picInfo.getPicUrl(), "正在设置头像，请稍后...", true);
				}
			}
			else
			{
				String msg = "上传图片失败！";
				if (returnInfo != null)
				{
					msg = returnInfo.getMsg();
				}
				ToastView.showCenterToast(this, R.drawable.ic_do_fail, msg);
			}
		}
		else if (url.endsWith(ApiConfig.UPDATE_USER_HEADER))
		{
			ReturnInfo returnInfo = GsonTools.getReturnInfo(result);
			if (ReturnInfo.isSuccess(returnInfo))
			{
				ToastView.showCenterToast(this, R.drawable.ic_done, "设置头像成功！");
				mUserService.getMyUserInfo(null, true);
			}
			else
			{
				String msg = "设置头像失败！";
				if (returnInfo != null)
				{
					msg = returnInfo.getMsg();
				}
				ToastView.showCenterToast(this, R.drawable.ic_do_fail, msg);
			}
		}
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == ConstValues.REQUEST_LOGIN_USER)
		{
			if (resultCode == ConstValues.RESULT_LOGIN_SUCCESS)
			{
				mUserService.getMyUserInfo(null, true);
			}
			else
			{
				finish();
			}
		}
		else if (resultCode == ImagePicker.RESULT_CODE_ITEMS)
		{
			if (data != null && requestCode == IMAGE_PICKER)
			{
				ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
				if (images != null && images.size() > 0)
				{
					ImageItem imageItem = images.get(0);
					mFileService.uploadPic(imageItem.path, "header", "正在上传图片...", true);
				}
			}
			else
			{
				Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
			}
		}
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.iv_photo:
			{
				EventBus.getDefault().post(EventTypeLayoutSideMain.CLOSE_DRAWER);
				if (!UserService.isLogin())
				{
					UserService.logout();
				}
				else
				{
					ImagePicker imagePicker = ImagePicker.getInstance();
					//						imagePicker.setShowCamera(true);  //显示拍照按钮
					//						imagePicker.setCrop(true);        //允许裁剪（单选才有效）
					imagePicker.setSaveRectangle(false); //是否按矩形区域保存
					imagePicker.setStyle(CropImageView.Style.CIRCLE);  //裁剪框的形状
					imagePicker.setFocusWidth(512);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
					imagePicker.setFocusHeight(512);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
					imagePicker.setOutPutX(512);//保存文件的宽度。单位像素
					imagePicker.setOutPutY(512);//保存文件的高度。单位像素
					startActivityForResult(new Intent(ActivityUser.this, ImageGridActivity.class), IMAGE_PICKER);
				}
				break;
			}
			case R.id.btn_edit:
			{
				startActivity(new Intent(this, ActivityUserEdit.class));
				break;
			}
			case R.id.tv_auth:
			{
				if (mUserInfo.getAuthStatus() != StatusType.ENABLE.getStatus())
				{
					startActivity(new Intent(this, ActivityUserAuth.class));
				}
				break;
			}
			case R.id.btn_logout:
			{
				UserService.logout();
				finish();
				break;
			}
		}
	}

	@Subscribe
	public void onEventMainThread(final UserEvent userEvent)
	{
		runOnUiThread(new Thread()
		{
			@Override
			public void run()
			{
				super.run();
				if (userEvent != null)
				{
					if (userEvent.getEvent().equalsIgnoreCase(UserEvent.LOGOUT.getEvent()))
					{
						finish();
					}
					else if (userEvent.getEvent().equalsIgnoreCase(UserEvent.CHANGED_HEADER.getEvent()) || userEvent.getEvent().equalsIgnoreCase(UserEvent.CHANGED_DATA.getEvent()))
					{
						onRefresh();
					}
				}
			}
		});
	}
}
