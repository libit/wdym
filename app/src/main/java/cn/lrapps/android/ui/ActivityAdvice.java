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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.androidquery.callback.AjaxStatus;
import cn.lrapps.android.ui.customer.ToastView;
import cn.lrapps.utils.GsonTools;
import cn.lrapps.utils.StringTools;
import com.weiduyx.wdym.models.ReturnInfo;
import com.weiduyx.wdym.services.AdviceService;
import com.weiduyx.wdym.services.AdviceTypeService;
import com.weiduyx.wdym.services.ApiConfig;
import com.weiduyx.wdym.services.IAjaxDataResponse;
import com.weiduyx.wdym.R;

import java.util.ArrayList;
import java.util.List;

public class ActivityAdvice extends MyBaseActivity implements View.OnClickListener, IAjaxDataResponse
{
	private static final String CONTENT = "content";
	private EditText etName, etNumber, etEmail, etContent;
	private Spinner spinnerAdviceType;
	private ArrayAdapter spinnerAdviceTypeAdapter;
	private AdviceTypeService mAdviceTypeService;
	final List<String> adviceTypeStringList = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_advice);
		mAdviceTypeService = new AdviceTypeService(this);
		mAdviceTypeService.addDataResponse(this);
		viewInit();
		mAdviceTypeService.getAdviceTypeList(null, true);
	}

	@Override
	protected void viewInit()
	{
		super.viewInit();
		setBackButton();
		etName = (EditText) findViewById(R.id.et_name);
		etNumber = (EditText) findViewById(R.id.et_number);
		etEmail = (EditText) findViewById(R.id.et_email);
		etContent = (EditText) findViewById(R.id.et_content);
		spinnerAdviceType = (Spinner) findViewById(R.id.spinner_type);
		findViewById(R.id.btn_ok).setOnClickListener(this);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState)
	{
		outState.putCharSequence(CONTENT, etContent.getText().toString());
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState)
	{
		String content = savedInstanceState.getString(CONTENT);
		etContent.setText(content);
		super.onRestoreInstanceState(savedInstanceState);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.menu_activity_advice, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		int id = item.getItemId();
		if (id == R.id.action_list)
		{
			startActivity(new Intent(this, ActivityAdviceManage.class));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.btn_ok:
			{
				String adviceType = (String) spinnerAdviceType.getSelectedItem();
				String name = etName.getText().toString();
				String number = etNumber.getText().toString();
				String email = etEmail.getText().toString();
				String content = etContent.getText().toString();
				if (!StringTools.isNull(number) && !StringTools.isChinaMobilePhoneNumber(number))
				{
					ToastView.showCenterToast(this, R.drawable.ic_do_fail, "手机号码格式不正确，请重新输入！");
					etNumber.requestFocus();
					return;
				}
				if (!StringTools.isNull(email) && !StringTools.isEmail(email))
				{
					ToastView.showCenterToast(this, R.drawable.ic_do_fail, "邮箱格式不正确，请重新输入！");
					etEmail.requestFocus();
					return;
				}
				if (StringTools.isNull(content))
				{
					ToastView.showCenterToast(this, R.drawable.ic_do_fail, "请输入您的建议！");
					etContent.requestFocus();
					return;
				}
				AdviceService adviceService = new AdviceService(this);
				adviceService.addDataResponse(this);
				adviceService.submitAdvice(adviceType, name, number, email, content, "正在提交...", false);
				break;
			}
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
		//		else if (url.endsWith(ApiConfig.GET_ADVICE_TYPE_LIST))
		//		{
		//			final List<AdviceTypeInfo> userSendMsgInfoList = new ArrayList<>();
		//			TableData tableData = GsonTools.getObject(result, TableData.class);
		//			if (tableData != null)
		//			{
		//				List<AdviceTypeInfo> adviceTypeInfoList1 = GsonTools.getObjects(GsonTools.toJson(tableData.getData()), new TypeToken<List<AdviceTypeInfo>>()
		//				{
		//				}.getType());
		//				if (adviceTypeInfoList1 != null && adviceTypeInfoList1.size() > 0)
		//				{
		//					for (AdviceTypeInfo adviceTypeInfo : adviceTypeInfoList1)
		//					{
		//						userSendMsgInfoList.add(adviceTypeInfo);
		//					}
		//				}
		//			}
		//			if (userSendMsgInfoList.size() > 0)
		//			{
		//				adviceTypeStringList.clear();
		//				for (AdviceTypeInfo userSendMsgInfo : userSendMsgInfoList)
		//				{
		//					adviceTypeStringList.add(userSendMsgInfo.getName());
		//				}
		//				spinnerAdviceTypeAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, adviceTypeStringList);
		//				spinnerAdviceTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		//				spinnerAdviceType.setAdapter(spinnerAdviceTypeAdapter);
		//			}
		//			return true;
		//		}
		return true;
	}
}
