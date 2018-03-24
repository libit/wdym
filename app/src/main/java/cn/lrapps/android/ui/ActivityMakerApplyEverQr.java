/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.android.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.androidquery.callback.AjaxStatus;
import cn.lrapps.android.ui.customer.ToastView;
import cn.lrapps.enums.ApplyStatus;
import cn.lrapps.utils.GsonTools;
import cn.lrapps.utils.StringTools;
import com.weiduyx.wdym.models.MakerApplyEverQrInfo;
import com.weiduyx.wdym.models.ReturnInfo;
import com.weiduyx.wdym.models.UserInfo;
import com.weiduyx.wdym.services.ApiConfig;
import com.weiduyx.wdym.services.IAjaxDataResponse;
import com.weiduyx.wdym.services.MakerService;
import com.weiduyx.wdym.services.UserService;
import com.weiduyx.wdym.R;

public class ActivityMakerApplyEverQr extends MyBaseActivity implements View.OnClickListener, IAjaxDataResponse
{
	private EditText etAgentId, etContent;
	private UserService mUserService;
	private MakerService mMakerService;
	private UserInfo mUserInfo;
	private MakerApplyEverQrInfo mMakerApplyEverQrInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_maker_apply_ever_qr);
		mUserService = new UserService(this);
		mUserService.addDataResponse(this);
		mMakerService = new MakerService(this);
		mMakerService.addDataResponse(this);
		viewInit();
		//mUserService.getMyUserInfo("请稍后...", true);
		mMakerService.getMakerApplyEverQrInfo("请稍后...", true);
	}

	@Override
	protected void viewInit()
	{
		super.viewInit();
		setBackButton();
		etAgentId = (EditText) findViewById(R.id.et_agent_id);
		etContent = (EditText) findViewById(R.id.et_content);
		findViewById(R.id.btn_submit).setOnClickListener(this);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.btn_submit:
			{
				if (mMakerApplyEverQrInfo.getStatus() == ApplyStatus.APPLY.getStatus())
				{
					ToastView.showCenterToast(this, R.drawable.ic_do_fail, "您已提交申请，请等待平台审核！");
					return;
				}
				String agentId = etAgentId.getText().toString();
				String content = etContent.getText().toString();
				if (StringTools.isNull(agentId))
				{
					ToastView.showCenterToast(this, R.drawable.ic_do_fail, "合伙人账号不能为空！");
					etContent.requestFocus();
					return;
				}
				if (StringTools.isNull(content))
				{
					ToastView.showCenterToast(this, R.drawable.ic_do_fail, "申请理由不能为空！");
					etContent.requestFocus();
					return;
				}
				mMakerService.addMakerApplyEverQrInfo(agentId, content, "正在提交...", true);
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
				}
			}
		}
		else if (url.endsWith(ApiConfig.ADD_MAKER_APPLY_EVER_QR_INFO))
		{
			ReturnInfo returnInfo = GsonTools.getReturnInfo(result);
			if (ReturnInfo.isSuccess(returnInfo))
			{
				ToastView.showCenterToast(this, R.drawable.ic_done, "申请提交成功！");
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
		else if (url.endsWith(ApiConfig.GET_MAKER_APPLY_EVER_QR_INFO))
		{
			ReturnInfo returnInfo = GsonTools.getReturnInfo(result);
			if (ReturnInfo.isSuccess(returnInfo))
			{
				String statusStr = "未申请";
				mMakerApplyEverQrInfo = GsonTools.getReturnObject(returnInfo, MakerApplyEverQrInfo.class);
				if (mMakerApplyEverQrInfo != null)
				{
					etAgentId.setText(mMakerApplyEverQrInfo.getAgentId());
					etContent.setText(mMakerApplyEverQrInfo.getContent());
				}
			}
		}
		return true;
	}
}
