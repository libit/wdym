/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.android.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.androidquery.callback.AjaxStatus;
import cn.lrapps.android.ui.customer.ToastView;
import cn.lrapps.android.ui.dialog.DialogCommon;
import cn.lrapps.enums.SexType;
import cn.lrapps.enums.SmsCodeType;
import cn.lrapps.utils.ConstValues;
import cn.lrapps.utils.GsonTools;
import cn.lrapps.utils.StringTools;
import cn.lrapps.utils.apptools.SystemToolsFactory;
import com.weiduyx.wdym.models.ReturnInfo;
import com.weiduyx.wdym.services.ApiConfig;
import com.weiduyx.wdym.services.IAjaxDataResponse;
import com.weiduyx.wdym.services.UserService;
import com.weiduyx.wdym.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityRegister extends MyBaseActivity implements View.OnClickListener, IAjaxDataResponse
{
	private EditText etUsername, etPassword, etNickname, etNumber, etCity, etAddress, etCode;
	//	private ImageView ivGetCode;
	private Spinner spinnerSex;
	private ArrayAdapter spinnerSexAdapter;
	private Map<String, Byte> sexMap = new HashMap<>();
	private UserService mUserService;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		mUserService = new UserService(this);
		mUserService.addDataResponse(this);
		viewInit();
	}

	@Override
	protected void viewInit()
	{
		super.viewInit();
		setBackButton();
		etUsername = (EditText) findViewById(R.id.et_username);
		etPassword = (EditText) findViewById(R.id.et_password);
		etNickname = (EditText) findViewById(R.id.et_nickname);
		etNumber = (EditText) findViewById(R.id.et_number);
		etCity = (EditText) findViewById(R.id.et_city);
		etAddress = (EditText) findViewById(R.id.et_address);
		etCode = (EditText) findViewById(R.id.et_code);
		//		ivGetCode = (ImageView) findViewById(R.id.btn_get_code);
		spinnerSex = (Spinner) findViewById(R.id.spinner_sex);
		findViewById(R.id.btn_get_code).setOnClickListener(this);
		findViewById(R.id.btn_register).setOnClickListener(this);
		final List<String> sexStringList = new ArrayList<>();
		sexStringList.add("男");
		sexStringList.add("女");
		sexMap.put("男", SexType.MALE.getSex());
		sexMap.put("女", SexType.FEMALE.getSex());
		spinnerSexAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, sexStringList);
		spinnerSexAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerSex.setAdapter(spinnerSexAdapter);
		etUsername.setText(SystemToolsFactory.getInstance().getNumber());
		//		Picasso.with(this).load(Uri.parse(ApiConfig.GET_AUTH_CODE)).into(ivGetCode);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.btn_get_code:
			{
				String number = etUsername.getText().toString();
				if (StringTools.isNull(number))
				{
					ToastView.showCenterToast(this, R.drawable.ic_do_fail, "手机号码不能为空！");
					etUsername.requestFocus();
					return;
				}
				if (!StringTools.isChinaMobilePhoneNumber(number))
				{
					ToastView.showCenterToast(this, R.drawable.ic_do_fail, "手机号码格式不正确！");
					etUsername.requestFocus();
					return;
				}
				mUserService.getCode(number, SmsCodeType.REGISTER.getType(), "正在获取验证码...", true);
				break;
			}
			case R.id.btn_register:
			{
				String username = etUsername.getText().toString();
				String password = etPassword.getText().toString();
				String nickname = etNickname.getText().toString();
				String number = username;//etNumber.getText().toString();
				String country = etCity.getText().toString();
				String province = etCity.getText().toString();
				String city = etCity.getText().toString();
				String address = etAddress.getText().toString();
				String code = etCode.getText().toString();
				Byte sex = sexMap.get(spinnerSex.getSelectedItem());
				String picUrl = null;
				Long birthday = null;
				String remark = null;
				if (StringTools.isNull(username))
				{
					ToastView.showCenterToast(this, R.drawable.ic_do_fail, "手机号码不能为空！");
					etUsername.requestFocus();
					return;
				}
				if (username.length() < 5 || username.length() > 16)
				{
					ToastView.showCenterToast(this, R.drawable.ic_do_fail, "手机号码位数不正确，请输入5-16位的手机号码！");
					etUsername.requestFocus();
					return;
				}
				if (StringTools.isNull(password))
				{
					ToastView.showCenterToast(this, R.drawable.ic_do_fail, "密码不能为空！");
					etPassword.requestFocus();
					return;
				}
				if (password.length() < 6 || password.length() > 16)
				{
					ToastView.showCenterToast(this, R.drawable.ic_do_fail, "密码位数不正确，请输入6-16位的密码！");
					etPassword.requestFocus();
					return;
				}
				if (StringTools.isNull(nickname))
				{
					ToastView.showCenterToast(this, R.drawable.ic_do_fail, "商户名称不能为空！");
					etNickname.requestFocus();
					return;
				}
				if (StringTools.isNull(number))
				{
					ToastView.showCenterToast(this, R.drawable.ic_do_fail, "手机号码不能为空！");
					etNumber.requestFocus();
					return;
				}
				if (!StringTools.isChinaMobilePhoneNumber(number))
				{
					ToastView.showCenterToast(this, R.drawable.ic_do_fail, "手机号码格式不正确！");
					etNumber.requestFocus();
					return;
				}
				if (StringTools.isNull(code))
				{
					ToastView.showCenterToast(this, R.drawable.ic_do_fail, "验证码不能为空！");
					etCode.requestFocus();
					return;
				}
				mUserService.register(username, password, nickname, number, country, province, city, address, sex, picUrl, birthday, remark, code, "正在注册...", true);
				break;
			}
		}
	}

	@Override
	public boolean onAjaxDataResponse(String url, String result, AjaxStatus status)
	{
		if (url.endsWith(ApiConfig.REGISTER))
		{
			ReturnInfo returnInfo = GsonTools.getReturnInfo(result);
			if (ReturnInfo.isSuccess(returnInfo))
			{
				ToastView.showCenterToast(this, R.drawable.ic_done, "恭喜您注册成功！");
				setResult(ConstValues.RESULT_REGISTER_SUCCESS);
				finish();
			}
			else
			{
				String msg = "注册失败。";
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
			if (ReturnInfo.isSuccess(returnInfo))
			{
				//				ToastView.showCenterToast(this, R.drawable.ic_done, returnInfo.getMsg());
				new DialogCommon(this, null, "提示", returnInfo.getMsg(), true, false, false).show();
			}
			else
			{
				String msg = "获取验证码失败。";
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
