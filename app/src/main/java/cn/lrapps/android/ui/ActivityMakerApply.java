/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.android.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.androidquery.callback.AjaxStatus;
import com.google.gson.reflect.TypeToken;
import cn.lrapps.android.ui.customer.ToastView;
import cn.lrapps.enums.EventTypeLayoutSideMain;
import cn.lrapps.enums.SexType;
import cn.lrapps.enums.SmsCodeType;
import cn.lrapps.enums.UserEvent;
import cn.lrapps.utils.GsonTools;
import cn.lrapps.utils.PreferenceUtils;
import cn.lrapps.utils.StringTools;
import cn.lrapps.utils.apptools.SystemToolsFactory;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.squareup.picasso.Picasso;
import com.weiduyx.wdym.models.CityInfo;
import com.weiduyx.wdym.models.PicInfo;
import com.weiduyx.wdym.models.ProvinceInfo;
import com.weiduyx.wdym.models.ReturnInfo;
import com.weiduyx.wdym.models.TableData;
import com.weiduyx.wdym.models.UserAuthInfo;
import com.weiduyx.wdym.services.ApiConfig;
import com.weiduyx.wdym.services.AreaService;
import com.weiduyx.wdym.services.FileService;
import com.weiduyx.wdym.services.IAjaxDataResponse;
import com.weiduyx.wdym.services.MakerService;
import com.weiduyx.wdym.services.UserService;
import com.weiduyx.wdym.R;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.lrapps.utils.ConstValues.IMAGE_PICKER;

public class ActivityMakerApply extends MyBaseActivity implements View.OnClickListener, IAjaxDataResponse
{
	private ImageView ivPhoto;
	private EditText etUserId, etName, etNumber, etCode, etIdentifityNumber;
	private Spinner spinnerSex, spinnerProvince, spinnerCity;
	private ArrayAdapter spinnerSexAdapter, spinnerProvinceAdapter, spinnerCityAdapter;
	private Map<String, Byte> sexMap = new HashMap<>();
	private UserService mUserService;
	private FileService mFileService;
	private AreaService mAreaService;
	private UserAuthInfo mUserAuthInfo;
	private MakerService mMakerService;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_maker_apply);
		mUserService = new UserService(this);
		mUserService.addDataResponse(this);
		mFileService = new FileService(this);
		mFileService.addDataResponse(this);
		mAreaService = new AreaService(this);
		mAreaService.addDataResponse(this);
		mMakerService = new MakerService(this);
		mMakerService.addDataResponse(this);
		viewInit();
		mUserService.getUserAuthInfo("请稍后...", true);
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
		etNumber = (EditText) findViewById(R.id.et_number);
		etCode = (EditText) findViewById(R.id.et_code);
		etIdentifityNumber = (EditText) findViewById(R.id.et_identifity_number);
		spinnerSex = (Spinner) findViewById(R.id.spinner_sex);
		spinnerProvince = (Spinner) findViewById(R.id.spinner_province);
		spinnerCity = (Spinner) findViewById(R.id.spinner_city);
		findViewById(R.id.btn_get_code).setOnClickListener(this);
		findViewById(R.id.btn_submit).setOnClickListener(this);
		etUserId.setText(PreferenceUtils.getInstance().getUserId());
		etNumber.setText(SystemToolsFactory.getInstance().getNumber());
		final List<String> sexStringList = new ArrayList<>();
		sexStringList.add("男");
		sexStringList.add("女");
		sexMap.put("男", SexType.MALE.getSex());
		sexMap.put("女", SexType.FEMALE.getSex());
		spinnerSexAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, sexStringList);
		spinnerSexAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerSex.setAdapter(spinnerSexAdapter);
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
					startActivityForResult(new Intent(ActivityMakerApply.this, ImageGridActivity.class), IMAGE_PICKER);
				}
				break;
			}
			case R.id.btn_get_code:
			{
				String number = etNumber.getText().toString();
				if (StringTools.isNull(number))
				{
					ToastView.showCenterToast(this, R.drawable.ic_do_fail, "手机号码不能为空！");
					etNumber.requestFocus();
					return;
				}
				if (!StringTools.isChinaMobilePhoneNumber(number))
				{
					ToastView.showCenterToast(this, R.drawable.ic_do_fail, "号码不是有效的手机号码！");
					etNumber.requestFocus();
					return;
				}
				mUserService.getCode(number, SmsCodeType.USER_AUTH.getType(), "正在获取验证码，请稍后...", true);
				break;
			}
			case R.id.btn_submit:
			{
				String name = etName.getText().toString();
				String number = etNumber.getText().toString();
				String code = etCode.getText().toString();
				String identifityNumber = etIdentifityNumber.getText().toString();
				Byte sex = sexMap.get(spinnerSex.getSelectedItem());
				//				String province = (String) spinnerProvince.getTag();
				//				String city = (String) spinnerCity.getTag();
				String province = (String) spinnerProvince.getSelectedItem();
				String city = (String) spinnerCity.getSelectedItem();
				String picUrl = (String) ivPhoto.getTag();
				if (StringTools.isNull(name))
				{
					ToastView.showCenterToast(this, R.drawable.ic_do_fail, "姓名不能为空！");
					etName.requestFocus();
					return;
				}
				if (StringTools.isNull(number))
				{
					ToastView.showCenterToast(this, R.drawable.ic_do_fail, "手机号码不能为空！");
					etNumber.requestFocus();
					return;
				}
				if (StringTools.isNull(code))
				{
					ToastView.showCenterToast(this, R.drawable.ic_do_fail, "验证码不能为空！");
					etCode.requestFocus();
					return;
				}
				if (StringTools.isNull(identifityNumber))
				{
					ToastView.showCenterToast(this, R.drawable.ic_do_fail, "身份证不能为空！");
					etIdentifityNumber.requestFocus();
					return;
				}
				mMakerService.updateMakerApply(name, number, code, sex, identifityNumber, province, city, picUrl, "正在提交...", true);
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
		if (url.endsWith(ApiConfig.ADD_USER_MAKER_APPLY_INFO))
		{
			ReturnInfo returnInfo = GsonTools.getReturnInfo(result);
			if (ReturnInfo.isSuccess(returnInfo))
			{
				ToastView.showCenterToast(this, R.drawable.ic_done, "提交创客资料成功！");
				EventBus.getDefault().post(UserEvent.CHANGED_DATA);
				finish();
			}
			else
			{
				String msg = "提交创客资料失败。";
				if (returnInfo != null)
				{
					msg = returnInfo.getMsg();
				}
				ToastView.showCenterToast(this, R.drawable.ic_do_fail, msg);
			}
		}
		else if (url.endsWith(ApiConfig.GET_CODE))
		{
			ReturnInfo returnInfo = GsonTools.getReturnInfo(result);
			String msg = result;
			if (returnInfo != null)
			{
				msg = returnInfo.getMsg();
			}
			ToastView.showCenterToast(this, R.drawable.ic_do_fail, msg);
		}
		else if (url.endsWith(ApiConfig.GET_USER_AUTH_INFO))
		{
			ReturnInfo returnInfo = GsonTools.getReturnInfo(result);
			if (ReturnInfo.isSuccess(returnInfo))
			{
				mUserAuthInfo = GsonTools.getReturnObject(returnInfo, UserAuthInfo.class);
				if (mUserAuthInfo != null)
				{
					etUserId.setText(mUserAuthInfo.getUserId());
					etName.setText(mUserAuthInfo.getName());
					etNumber.setText(mUserAuthInfo.getNumber());
					etIdentifityNumber.setText(mUserAuthInfo.getIdentifityNumber());
					if (mUserAuthInfo.getSex() == SexType.FEMALE.getSex())
					{
						spinnerSex.setSelection(1);
					}
					else
					{
						spinnerSex.setSelection(0);
					}
					ivPhoto.setTag(mUserAuthInfo.getPicUrl());
					Picasso.with(this).load(Uri.parse(ApiConfig.getServerPicUrl(mUserAuthInfo.getPicUrl()))).placeholder(R.drawable.ic_header).error(R.drawable.ic_header).into(ivPhoto);
				}
			}
			mAreaService.getProvinceInfoList(0, -1, null, true);
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
		else if (url.endsWith(ApiConfig.GET_PROVINCE_LIST))
		{
			TableData tableData = GsonTools.getObject(result, TableData.class);
			if (tableData != null)
			{
				final List<ProvinceInfo> provinceInfoList = GsonTools.getObjects(GsonTools.toJson(tableData.getData()), new TypeToken<List<ProvinceInfo>>()
				{
				}.getType());
				if (provinceInfoList != null && provinceInfoList.size() > 0)
				{
					final List<String> stringList = new ArrayList<>();
					int selected = 0;
					int index = 0;
					for (ProvinceInfo provinceInfo : provinceInfoList)
					{
						if (mUserAuthInfo != null && provinceInfo.getName().equals(mUserAuthInfo.getProvince()))
						{
							selected = index;
						}
						index++;
						stringList.add(provinceInfo.getName());
					}
					spinnerProvinceAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, stringList);
					spinnerProvinceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					spinnerProvince.setAdapter(spinnerProvinceAdapter);
					spinnerProvince.setSelection(selected);
					mAreaService.getCityInfoList(provinceInfoList.get(selected).getProvinceId(), 0, -1, null, true);
					spinnerProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
					{
						@Override
						public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
						{
							String provinceId = provinceInfoList.get(position).getProvinceId();
							spinnerProvince.setTag(provinceId);
							mAreaService.getCityInfoList(provinceId, 0, -1, null, true);
						}

						@Override
						public void onNothingSelected(AdapterView<?> parent)
						{
						}
					});
				}
			}
			return true;
		}
		else if (url.endsWith(ApiConfig.GET_CITY_LIST))
		{
			TableData tableData = GsonTools.getObject(result, TableData.class);
			if (tableData != null)
			{
				final List<CityInfo> cityInfoList = GsonTools.getObjects(GsonTools.toJson(tableData.getData()), new TypeToken<List<CityInfo>>()
				{
				}.getType());
				if (cityInfoList != null && cityInfoList.size() > 0)
				{
					final List<String> stringList = new ArrayList<>();
					int selected = 0;
					int index = 0;
					for (CityInfo cityInfo : cityInfoList)
					{
						if (mUserAuthInfo != null && cityInfo.getName().equals(mUserAuthInfo.getCity()))
						{
							selected = index;
						}
						index++;
						stringList.add(cityInfo.getName());
					}
					spinnerCityAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, stringList);
					spinnerCityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					spinnerCity.setAdapter(spinnerCityAdapter);
					spinnerCity.setSelection(selected);
					spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
					{
						@Override
						public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
						{
							String cityId = cityInfoList.get(position).getCityId();
							spinnerCity.setTag(cityId);
						}

						@Override
						public void onNothingSelected(AdapterView<?> parent)
						{
						}
					});
				}
			}
			return true;
		}
		return true;
	}
}
