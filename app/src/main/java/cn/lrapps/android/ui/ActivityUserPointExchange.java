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
import cn.lrapps.utils.GsonTools;
import cn.lrapps.utils.StringTools;
import com.weiduyx.wdym.models.ReturnInfo;
import com.weiduyx.wdym.models.UserInfo;
import com.weiduyx.wdym.services.ApiConfig;
import com.weiduyx.wdym.services.IAjaxDataResponse;
import com.weiduyx.wdym.services.UserPointExchangeService;
import com.weiduyx.wdym.services.UserService;
import com.weiduyx.wdym.R;

public class ActivityUserPointExchange extends MyBaseActivity implements View.OnClickListener, IAjaxDataResponse
{
	private EditText etExchangePoint;
	private TextView tvPoint, tvBalance, tvTips;
	private UserService mUserService;
	private UserPointExchangeService mUserPointExchangeService;
	private UserInfo mUserInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_point_exchange);
		mUserService = new UserService(this);
		mUserService.addDataResponse(this);
		mUserPointExchangeService = new UserPointExchangeService(this);
		mUserPointExchangeService.addDataResponse(this);
		viewInit();
		mUserService.getMyUserInfo("请稍后...", true);
		mUserPointExchangeService.getPointExchangeTips(null, true);
	}

	@Override
	protected void viewInit()
	{
		super.viewInit();
		setBackButton();
		etExchangePoint = (EditText) findViewById(R.id.et_point);
		tvPoint = (TextView) findViewById(R.id.tv_point);
		tvBalance = (TextView) findViewById(R.id.tv_balance);
		tvTips = (TextView) findViewById(R.id.tv_tips);
		findViewById(R.id.btn_submit).setOnClickListener(this);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.btn_submit:
			{
				String point = etExchangePoint.getText().toString();
				if (StringTools.isNull(point))
				{
					ToastView.showCenterToast(this, R.drawable.ic_do_fail, "兑换金币不能为空！");
					etExchangePoint.requestFocus();
					return;
				}
				int exchangePoint = 0;
				try
				{
					double d = Double.parseDouble(point);
					if (d <= 0)
					{
						ToastView.showCenterToast(this, R.drawable.ic_do_fail, "兑换金币不能小于0！");
						etExchangePoint.requestFocus();
						return;
					}
					exchangePoint = (int) d;
				}
				catch (Exception e)
				{
					ToastView.showCenterToast(this, R.drawable.ic_do_fail, "兑换金币无效！");
					etExchangePoint.requestFocus();
					return;
				}
				mUserPointExchangeService.userPointToBalance(exchangePoint, "", "正在提交...", true);
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
				if (mUserInfo != null)
				{
					tvPoint.setText(mUserInfo.getPoint() + "个");
					tvBalance.setText(StringTools.convertToRmb(mUserInfo.getBalance()) + "元");
				}
			}
		}
		else if (url.endsWith(ApiConfig.POINT_TO_BALANCE))
		{
			ReturnInfo returnInfo = GsonTools.getReturnInfo(result);
			if (ReturnInfo.isSuccess(returnInfo))
			{
				ToastView.showCenterToast(this, R.drawable.ic_done, "兑换成功！");
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
		else if (url.endsWith(ApiConfig.GET_USER_POINT_EXCHANGE_TIPS))
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
			startActivity(new Intent(this, ActivityUserPointExchangeLogs.class));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
