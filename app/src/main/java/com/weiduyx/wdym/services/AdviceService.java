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
public class AdviceService extends BaseService
{
	public AdviceService(Context context)
	{
		super(context);
	}

	/**
	 * 提交意见反馈
	 *
	 * @param adviceType         反馈类型
	 * @param name               姓名
	 * @param number             手机号码
	 * @param email              邮箱
	 * @param content            反馈内容
	 * @param tips               等待提示信息
	 * @param needServiceProcess 是否需要服务类处理
	 */
	public void submitAdvice(String adviceType, String name, String number, String email, String content, String tips, final boolean needServiceProcess)
	{
		Map<String, Object> params = new HashMap<>();
		params.put("adviceType", adviceType);
		params.put("name", name);
		params.put("number", number);
		params.put("email", email);
		params.put("content", content);
		ajaxStringCallback(ApiConfig.ADD_ADVICE_INFO, params, true, tips, needServiceProcess);
	}

	/**
	 * 获取意见反馈列表
	 *
	 * @param start              开始位置
	 * @param size               大小
	 * @param tips               等待提示信息
	 * @param needServiceProcess 是否需要服务类处理
	 */
	public void getAdviceInfoList(int start, int size, String tips, final boolean needServiceProcess)
	{
		Map<String, Object> params = new HashMap<>();
		params.put("start", start);
		params.put("length", size);
		ajaxStringCallback(ApiConfig.GET_ADVICE_INFO_LIST, params, true, tips, needServiceProcess);
	}

	/**
	 * 获取意见反馈信息
	 *
	 * @param adviceId           意见反馈ID
	 * @param tips               等待提示信息
	 * @param needServiceProcess 是否需要服务类处理
	 */
	public void getAdviceInfo(String adviceId, String tips, final boolean needServiceProcess)
	{
		Map<String, Object> params = new HashMap<>();
		params.put("adviceId", adviceId);
		ajaxStringCallback(ApiConfig.GET_ADVICE_INFO, params, true, tips, needServiceProcess);
	}

	/**
	 * 获取意见反馈回复列表
	 *
	 * @param adviceId           意见反馈ID
	 * @param start              开始位置
	 * @param size               大小
	 * @param tips               等待提示信息
	 * @param needServiceProcess 是否需要服务类处理
	 */
	public void getAdviceReplyInfoList(String adviceId, int start, int size, String tips, final boolean needServiceProcess)
	{
		Map<String, Object> params = new HashMap<>();
		params.put("adviceId", adviceId);
		params.put("start", start);
		params.put("length", size);
		ajaxStringCallback(ApiConfig.GET_ADVICE_REPLY_LIST, params, true, tips, needServiceProcess);
	}

	@Override
	public void parseData(String url, String result, AjaxStatus status)
	{
		if (url.endsWith(ApiConfig.ADD_ADVICE_INFO))
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
