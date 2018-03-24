/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.android.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.androidquery.callback.AjaxStatus;
import cn.lrapps.android.ui.customer.ToastView;
import cn.lrapps.enums.BalanceType;
import cn.lrapps.enums.SmsCodeType;
import cn.lrapps.utils.GsonTools;
import cn.lrapps.utils.StringTools;
import com.weiduyx.wdym.models.ReturnInfo;
import com.weiduyx.wdym.services.ApiConfig;
import com.weiduyx.wdym.services.IAjaxDataResponse;
import com.weiduyx.wdym.services.UserService;
import com.weiduyx.wdym.services.UserWithdrawService;
import com.weiduyx.wdym.R;

public class ActivityUserWithdraw extends MyBaseActivity implements View.OnClickListener, IAjaxDataResponse
{
	private EditText etWithdrawMoney, etNumber, etCode;
	private TextView tvTips;
	private UserService mUserService;
	private UserWithdrawService mUserWithdrawService;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_withdraw);
		mUserService = new UserService(this);
		mUserService.addDataResponse(this);
		mUserWithdrawService = new UserWithdrawService(this);
		mUserWithdrawService.addDataResponse(this);
		viewInit();
		mUserWithdrawService.getWithdrawTips(null, true);
	}

	@Override
	protected void viewInit()
	{
		super.viewInit();
		setBackButton();
		etWithdrawMoney = (EditText) findViewById(R.id.et_withdraw_money);
		etNumber = (EditText) findViewById(R.id.et_number);
		etCode = (EditText) findViewById(R.id.et_code);
		tvTips = (TextView) findViewById(R.id.tv_tips);
		findViewById(R.id.btn_get_code).setOnClickListener(this);
		findViewById(R.id.btn_withdraw).setOnClickListener(this);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
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
				mUserService.getCode(number, SmsCodeType.NUMBER_AUTH.getType(), "正在获取验证码，请稍后...", true);
				break;
			}
			case R.id.btn_withdraw:
			{
				String money = etWithdrawMoney.getText().toString();
				String number = etNumber.getText().toString();
				String code = etCode.getText().toString();
				if (StringTools.isNull(money))
				{
					ToastView.showCenterToast(this, R.drawable.ic_do_fail, "提现金额不能为空！");
					etWithdrawMoney.requestFocus();
					return;
				}
				int withdrawMoney = 0;
				try
				{
					double d = Double.parseDouble(money);
					if (d <= 0)
					{
						ToastView.showCenterToast(this, R.drawable.ic_do_fail, "提现金额不能小于0！");
						etWithdrawMoney.requestFocus();
						return;
					}
					withdrawMoney = (int) (d * 100);
				}
				catch (Exception e)
				{
					ToastView.showCenterToast(this, R.drawable.ic_do_fail, "提现金额无效！");
					etWithdrawMoney.requestFocus();
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
					ToastView.showCenterToast(this, R.drawable.ic_do_fail, "号码不是有效的手机号码！");
					etNumber.requestFocus();
					return;
				}
				if (StringTools.isNull(code))
				{
					ToastView.showCenterToast(this, R.drawable.ic_do_fail, "验证码不能为空！");
					etNumber.requestFocus();
					return;
				}
				mUserWithdrawService.addWithdraw(withdrawMoney, BalanceType.COMMON.getType(), number, code, "正在提交...", true);
				break;
			}
		}
	}

	@Override
	public boolean onAjaxDataResponse(String url, String result, AjaxStatus status)
	{
		if (url.endsWith(ApiConfig.GET_CODE))
		{
			ReturnInfo returnInfo = GsonTools.getReturnInfo(result);
			String msg = result;
			if (returnInfo != null)
			{
				msg = returnInfo.getMsg();
			}
			ToastView.showCenterToast(this, R.drawable.ic_do_fail, msg);
		}
		else if (url.endsWith(ApiConfig.ADD_USER_WITHDRAW_INFO))
		{
			ReturnInfo returnInfo = GsonTools.getReturnInfo(result);
			if (ReturnInfo.isSuccess(returnInfo))
			{
				ToastView.showCenterToast(this, R.drawable.ic_done, "您的提现申请已提交，通过平台审核后会在24小时内发放到本微信帐号零钱。");
				finish();
			}
			else
			{
				String msg = result;
				if (returnInfo != null)
				{
					msg = returnInfo.getMsg();
				}
				ToastView.showCenterToast(this, R.drawable.ic_do_fail, msg);
			}
		}
		else if (url.endsWith(ApiConfig.GET_USER_WITHDRAW_TIPS))
		{
			ReturnInfo returnInfo = GsonTools.getReturnInfo(result);
			if (ReturnInfo.isSuccess(returnInfo))
			{
				String tips = returnInfo.getMsg();
//				tips = tips.replace("\n", "\\n");
				tvTips.setText(tips);
			}
		}
		return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.menu_activity_user_withdraw, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		int id = item.getItemId();
		if (id == R.id.action_log)
		{
			startActivity(new Intent(this, ActivityUserWithdrawLogs.class));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
