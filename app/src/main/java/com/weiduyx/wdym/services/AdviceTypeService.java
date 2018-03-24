/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.services;

import android.content.Context;

import com.androidquery.callback.AjaxStatus;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 意见反馈服务类
 * Created by libit on 16/4/6.
 */
public class AdviceTypeService extends BaseService
{
	public AdviceTypeService(Context context)
	{
		super(context);
	}

	/**
	 * 提交意见反馈
	 *
	 * @param tips               等待提示信息
	 * @param needServiceProcess 是否需要服务类处理
	 */
	public void getAdviceTypeList(String tips, final boolean needServiceProcess)
	{
		Map<String, Object> params = new HashMap<>();
		ajaxStringCallback(ApiConfig.GET_ADVICE_TYPE_LIST, params, true, tips, needServiceProcess);
	}

	@Override
	public void parseData(String url, String result, AjaxStatus status)
	{
		if (url.endsWith(ApiConfig.GET_ADVICE_TYPE_LIST))
		{
			//			ReturnInfo returnInfo = GsonTools.getReturnInfo(result);
			//			if (ReturnInfo.isSuccess(returnInfo))
			//			{
			//				ToastView.showCenterToast(context, R.drawable.ic_done, "意见反馈已提交！");
			//			}
			//			else
			//			{
			//				String msg = result;
			//				if (returnInfo != null)
			//				{
			//					msg = returnInfo.getMsg();
			//				}
			//				ToastView.showCenterToast(context, R.drawable.ic_do_fail, msg);
			//			}
		}
	}

	@Override
	protected void parseData(String url, File file, AjaxStatus status)
	{
		super.parseData(url, file, status);
		if (url.endsWith("jpg"))
		{
			if (file == null)
			{
			}
		}
	}
}
