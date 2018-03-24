/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.android.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.androidquery.callback.AjaxStatus;
import com.google.gson.reflect.TypeToken;
import cn.lrapps.android.ui.customer.ToastView;
import cn.lrapps.utils.ConstValues;
import cn.lrapps.utils.GsonTools;
import cn.lrapps.utils.StringTools;
import com.weiduyx.wdym.models.AdviceInfo;
import com.weiduyx.wdym.models.AdviceReplyInfo;
import com.weiduyx.wdym.models.ReturnInfo;
import com.weiduyx.wdym.models.TableData;
import com.weiduyx.wdym.services.AdviceService;
import com.weiduyx.wdym.services.ApiConfig;
import com.weiduyx.wdym.services.IAjaxDataResponse;
import com.weiduyx.wdym.R;

import java.util.List;

public class ActivityAdviceInfo extends MyBaseActivity implements View.OnClickListener, IAjaxDataResponse
{
	private EditText etName, etNumber, etAdvcieType, etContent, etReplyContent;
	private AdviceService mAdviceService;
	private String mAdviceId;
	private AdviceInfo mAdviceInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_advice_info);
		mAdviceId = getIntent().getStringExtra(ConstValues.DATA_ADVICE_ID);
		if (StringTools.isNull(mAdviceId))
		{
			ToastView.showCenterToast(this, R.drawable.ic_do_fail, "ID不能为空！");
			finish();
		}
		mAdviceService = new AdviceService(this);
		mAdviceService.addDataResponse(this);
		viewInit();
		mAdviceService.getAdviceInfo(mAdviceId, null, true);
		mAdviceService.getAdviceReplyInfoList(mAdviceId, 0, 1, null, true);
	}

	@Override
	protected void viewInit()
	{
		super.viewInit();
		setBackButton();
		etName = (EditText) findViewById(R.id.et_name);
		etNumber = (EditText) findViewById(R.id.et_number);
		etAdvcieType = (EditText) findViewById(R.id.et_advice_type);
		etContent = (EditText) findViewById(R.id.et_content);
		etReplyContent = (EditText) findViewById(R.id.et_reply_content);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		}
	}

	@Override
	public boolean onAjaxDataResponse(String url, String result, AjaxStatus status)
	{
		if (url.endsWith(ApiConfig.ADD_ADVICE_INFO))
		{
			ReturnInfo returnInfo = GsonTools.getReturnInfo(result);
			if (ReturnInfo.isSuccess(returnInfo))
			{
				ToastView.showCenterToast(this, R.drawable.ic_done, "意见反馈已提交！");
				etContent.setText("");
			}
			else
			{
				String msg = "意见反馈提交失败：" + result;
				if (returnInfo != null)
				{
					msg = "意见反馈提交失败：" + returnInfo.getMsg();
				}
				ToastView.showCenterToast(this, R.drawable.ic_do_fail, msg);
			}
		}
		else if (url.endsWith(ApiConfig.GET_ADVICE_INFO))
		{
			ReturnInfo returnInfo = GsonTools.getReturnInfo(result);
			if (ReturnInfo.isSuccess(returnInfo))
			{
				mAdviceInfo = GsonTools.getReturnObject(returnInfo, AdviceInfo.class);
				if (mAdviceInfo != null)
				{
					//					etName.setText(mAdviceInfo.getName());
					etNumber.setText(mAdviceInfo.getNumber());
					//					etAdvcieType.setText(mAdviceInfo.getAdviceType());
					etContent.setText(mAdviceInfo.getContent());
					etName.setEnabled(false);
					etNumber.setEnabled(false);
					etAdvcieType.setEnabled(false);
					etContent.setEnabled(false);
				}
				else
				{
					ToastView.showCenterToast(this, R.drawable.ic_do_fail, "ID不存在！");
					finish();
				}
			}
			else
			{
				ToastView.showCenterToast(this, R.drawable.ic_do_fail, "ID不存在！");
				finish();
			}
		}
		else if (url.endsWith(ApiConfig.GET_ADVICE_REPLY_LIST))
		{
			List<AdviceReplyInfo> adviceReplyInfoList = null;
			TableData tableData = GsonTools.getObject(result, TableData.class);
			if (tableData != null)
			{
				adviceReplyInfoList = GsonTools.getObjects(GsonTools.toJson(tableData.getData()), new TypeToken<List<AdviceReplyInfo>>()
				{
				}.getType());
			}
			if (adviceReplyInfoList != null && adviceReplyInfoList.size() > 0)
			{
				AdviceReplyInfo adviceReplyInfo = adviceReplyInfoList.get(0);
				etReplyContent.setText(adviceReplyInfo.getContent());
			}
			else
			{
				etReplyContent.setText("暂无回复。");
			}
			return true;
		}
		return true;
	}
}
