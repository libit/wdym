/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.android.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.callback.AjaxStatus;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.weiduyx.wdym.R;
import com.weiduyx.wdym.models.ReturnInfo;
import com.weiduyx.wdym.models.UpdateInfo;
import com.weiduyx.wdym.services.ApiConfig;
import com.weiduyx.wdym.services.IAjaxDataResponse;
import com.weiduyx.wdym.services.UpdateService;
import com.weiduyx.wdym.services.UserService;
import com.weiduyx.wdym.services.WxService;

import net.sourceforge.simcpux.Constants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import cn.lrapps.android.ui.customer.ToastView;
import cn.lrapps.models.WxLoginEvent;
import cn.lrapps.utils.ConstValues;
import cn.lrapps.utils.GsonTools;
import cn.lrapps.utils.PreferenceUtils;
import cn.lrapps.utils.StringTools;
import cn.lrapps.utils.apptools.SystemToolsFactory;

public class ActivityLogin extends MyBaseActivity implements View.OnClickListener, IAjaxDataResponse
{
	private EditText etUsername, etPassword, etCode;
	private TextView tvTips;
	private ImageView ivCode;
	private UserService mUserService;
	private UpdateService mUpdateService;
	private WxService mWxService;
	// IWXAPI 是第三方app和微信通信的openapi接口
	private IWXAPI api;
	private String mState = "user_login";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		mUpdateService = new UpdateService(this);
		mUpdateService.addDataResponse(this);
		mUserService = new UserService(this);
		mUserService.addDataResponse(this);
		mWxService = new WxService(this);
		mWxService.addDataResponse(this);
		viewInit();
		// 通过WXAPIFactory工厂，获取IWXAPI的实例
		api = WXAPIFactory.createWXAPI(this, Constants.APP_ID, false);
		// 将该app注册到微信
		api.registerApp(Constants.APP_ID);
		if (!EventBus.getDefault().isRegistered(this))
		{
			EventBus.getDefault().register(this);
		}
		checkUpdate(false);
		//		mUserService.getAuthCode(null, true);
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

	@Subscribe
	public void onEventMainThread(final WxLoginEvent wxLoginEvent)
	{
		if (wxLoginEvent != null)
		{
			if (wxLoginEvent.getEvent().equalsIgnoreCase(WxLoginEvent.WX_REQUEST_LOGIN))
			{
				mWxService.appAuth(Constants.APP_ID, wxLoginEvent.getCode(), wxLoginEvent.getCode(), "正在登录，请稍后...", true);
			}
		}
	}

	private void checkUpdate(boolean b)
	{
		mUpdateService.checkUpdate(null, b);
	}

	@Override
	protected void viewInit()
	{
		super.viewInit();
		setBackButton();
		etUsername = (EditText) findViewById(R.id.et_username);
		etPassword = (EditText) findViewById(R.id.et_password);
		etCode = (EditText) findViewById(R.id.et_code);
		ivCode = (ImageView) findViewById(R.id.iv_code);
		tvTips = (TextView) findViewById(R.id.tv_tips);
		findViewById(R.id.btn_login).setOnClickListener(this);
		findViewById(R.id.btn_register).setOnClickListener(this);
		findViewById(R.id.btn_wx_login).setOnClickListener(this);
		findViewById(R.id.btn_qq_login).setOnClickListener(this);
		//		ivCode.setImageURI(Uri.parse("http://192.168.168.4:8080/LR/user/getAuthCode"));
		//		PicService.ajaxGetPic(ivCode, "http://192.168.168.4:8080/LR/user/getAuthCode", 0);
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		etUsername.setText(PreferenceUtils.getInstance().getUserId());
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.btn_wx_login:
			{
				final SendAuth.Req req = new SendAuth.Req();
				req.scope = "snsapi_userinfo";
				req.state = mState;
				api.sendReq(req);
				break;
			}
			case R.id.btn_qq_login:
			{
				Toast.makeText(this, "正在内测中...", Toast.LENGTH_LONG).show();
				break;
			}
			case R.id.btn_login:
			{
				String username = etUsername.getText().toString();
				String password = etPassword.getText().toString();
				String code = etCode.getText().toString();
				if (StringTools.isNull(username))
				{
					ToastView.showCenterToast(this, R.drawable.ic_do_fail, "账号不能为空！");
					etUsername.requestFocus();
					return;
				}
				if (username.length() < 5 || username.length() > 16)
				{
					ToastView.showCenterToast(this, R.drawable.ic_do_fail, "账号位数不正确，请输入5-16位的账号！");
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
				mUserService.login(username, password, code, "正在登录...", true);
				break;
			}
			case R.id.btn_register:
			{
				startActivityForResult(new Intent(this, ActivityRegister.class), ConstValues.REQUEST_REGISTER);
				break;
			}
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == ConstValues.REQUEST_REGISTER)
		{
			if (resultCode == ConstValues.RESULT_REGISTER_SUCCESS)
			{
				setResult(ConstValues.RESULT_LOGIN_SUCCESS);
				startActivity(new Intent(this, ActivityMain.class));
				finish();
			}
			else
			{
				setResult(ConstValues.RESULT_LOGIN_ERROR);
				//				finish();
			}
		}
	}

	@Override
	public boolean onAjaxDataResponse(String url, String result, AjaxStatus status)
	{
		if (url.endsWith(ApiConfig.LOGIN) || url.endsWith(ApiConfig.WX_APP_AUTH_CALLBACK))
		{
			ReturnInfo returnInfo = GsonTools.getReturnInfo(result);
			if (ReturnInfo.isSuccess(returnInfo))
			{
				ToastView.showCenterToast(this, R.drawable.ic_done, "登录成功");
				setResult(ConstValues.RESULT_LOGIN_SUCCESS);
				startActivity(new Intent(this, ActivityMain.class));
				finish();
			}
			else
			{
				setResult(ConstValues.RESULT_LOGIN_ERROR);
				String msg = result;
				if (returnInfo != null)
				{
					msg = returnInfo.getMsg();
				}
				ToastView.showCenterToast(this, R.drawable.ic_do_fail, msg);
			}
		}
		else if (url.endsWith(ApiConfig.CHECK_UPDATE))
		{
			UpdateInfo updateInfo = GsonTools.getReturnObject(result, UpdateInfo.class);
			if (updateInfo != null && SystemToolsFactory.getInstance().getVersionCode() < updateInfo.getVersionCode())
			{
				mUpdateService.showUpdataDialog(updateInfo);
			}
			return true;
		}
		return true;
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu)
//	{
//		getMenuInflater().inflate(R.menu.menu_activity_login, menu);
//		return true;
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item)
//	{
//		int id = item.getItemId();
//		if (id == R.id.action_refresh)
//		{
//			checkUpdate(true);
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}
}
