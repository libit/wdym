/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.android.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.androidquery.callback.AjaxStatus;
import cn.lrapps.android.ui.customer.ToastView;
import cn.lrapps.enums.EventTypeLayoutSideMain;
import cn.lrapps.enums.SexType;
import cn.lrapps.enums.UserEvent;
import cn.lrapps.utils.GsonTools;
import cn.lrapps.utils.StringTools;
import cn.lrapps.utils.apptools.SystemToolsFactory;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.lrapps.utils.ConstValues.IMAGE_PICKER;

public class ActivityUserEdit extends MyBaseActivity implements View.OnClickListener, IAjaxDataResponse
{
	private ImageView ivPhoto;
	private EditText etUserId, etName, etEmail;
	private Spinner spinnerSex;
	private ArrayAdapter spinnerSexAdapter;
	private Map<String, Byte> sexMap = new HashMap<>();
	private UserService mUserService;
	private FileService mFileService;
	private UserInfo mUserInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_edit);
		mUserService = new UserService(this);
		mUserService.addDataResponse(this);
		mFileService = new FileService(this);
		mFileService.addDataResponse(this);
		viewInit();
		mUserService.getMyUserInfo("请稍后...", true);
	}

	@Override
	protected void viewInit()
	{
		super.viewInit();
		setBackButton();
		ivPhoto = (ImageView) findViewById(R.id.iv_photo);
		ivPhoto.setOnClickListener(this);
		etUserId = (EditText) findViewById(R.id.et_user_id);
		etName = (EditText) findViewById(R.id.et_name);
		etEmail = (EditText) findViewById(R.id.et_email);
		spinnerSex = (Spinner) findViewById(R.id.spinner_sex);
		findViewById(R.id.btn_submit).setOnClickListener(this);
		etUserId.setText(SystemToolsFactory.getInstance().getNumber());
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
					startActivityForResult(new Intent(ActivityUserEdit.this, ImageGridActivity.class), IMAGE_PICKER);
				}
				break;
			}
			case R.id.btn_submit:
			{
				String name = etName.getText().toString();
				String email = etEmail.getText().toString();
				Byte sex = sexMap.get(spinnerSex.getSelectedItem());
				String picUrl = (String) ivPhoto.getTag();
				if (StringTools.isNull(name))
				{
					ToastView.showCenterToast(this, R.drawable.ic_do_fail, "姓名不能为空！");
					etName.requestFocus();
					return;
				}
				//				if (StringTools.isNull(email))
				//				{
				//					ToastView.showCenterToast(this, R.drawable.ic_do_fail, "邮箱不能为空！");
				//					etEmail.requestFocus();
				//					return;
				//				}
				mUserService.updateUserInfo(name, email, sex, picUrl, "正在提交...", true);
				break;
			}
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == ImagePicker.RESULT_CODE_ITEMS)
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
	public boolean onAjaxDataResponse(String url, String result, AjaxStatus status)
	{
		if (url.endsWith(ApiConfig.UPDATE_USER_INFO))
		{
			ReturnInfo returnInfo = GsonTools.getReturnInfo(result);
			if (ReturnInfo.isSuccess(returnInfo))
			{
				ToastView.showCenterToast(this, R.drawable.ic_done, "修改资料成功！");
				EventBus.getDefault().post(UserEvent.CHANGED_DATA);
				finish();
			}
			else
			{
				String msg = "修改资料失败。";
				if (returnInfo != null)
				{
					msg = returnInfo.getMsg();
				}
				ToastView.showCenterToast(this, R.drawable.ic_do_fail, msg);
			}
		}
		else if (url.endsWith(ApiConfig.GET_MY_USER_INFO))
		{
			ReturnInfo returnInfo = GsonTools.getReturnInfo(result);
			if (ReturnInfo.isSuccess(returnInfo))
			{
				mUserInfo = GsonTools.getReturnObject(returnInfo, UserInfo.class);
				if (mUserInfo != null)
				{
					etUserId.setText(mUserInfo.getUserId());
					etName.setText(mUserInfo.getName());
					etEmail.setText(mUserInfo.getEmail());
					final List<String> sexStringList = new ArrayList<>();
					sexStringList.add("男");
					sexStringList.add("女");
					sexMap.put("男", SexType.MALE.getSex());
					sexMap.put("女", SexType.FEMALE.getSex());
					spinnerSexAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, sexStringList);
					spinnerSexAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					spinnerSex.setAdapter(spinnerSexAdapter);
					if (mUserInfo.getSex() != null && mUserInfo.getSex() == SexType.FEMALE.getSex())
					{
						spinnerSex.setSelection(1);
					}
					else
					{
						spinnerSex.setSelection(0);
					}
					ivPhoto.setTag(mUserInfo.getPicId());
					Picasso.with(this).load(Uri.parse(ApiConfig.getServerPicUrl(mUserInfo.getPicId()))).placeholder(R.drawable.ic_header).error(R.drawable.ic_header).into(ivPhoto);
				}
			}
			else
			{
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
					ivPhoto.setTag(picInfo.getPicUrl());
					Picasso.with(this).load(Uri.parse(ApiConfig.getServerPicUrl(picInfo.getPicUrl()))).placeholder(R.drawable.ic_header).error(R.drawable.ic_header).into(ivPhoto);
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
		return true;
	}
}
