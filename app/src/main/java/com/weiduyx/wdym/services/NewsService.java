/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.services;

import android.content.Context;

import com.androidquery.callback.AjaxStatus;
import cn.lrapps.utils.GsonTools;
import com.weiduyx.wdym.models.ReturnInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息公告服务类
 * Created by libit on 16/4/6.
 */
public class NewsService extends BaseService
{
	public NewsService(Context context)
	{
		super(context);
	}

	/**
	 * 获取用户群发消息日志
	 *
	 * @param newsId             消息ID
	 * @param tips               等待提示信息
	 * @param needServiceProcess 是否需要服务类处理
	 */
	public void getNewsInfo(String newsId, String tips, final boolean needServiceProcess)
	{
		Map<String, Object> params = new HashMap<>();
		params.put("newsId", newsId);
		ajaxStringCallback(ApiConfig.GET_NEWS_INFO, params, true, tips, needServiceProcess);
	}

	/**
	 * 获取用户群发消息日志列表
	 *
	 * @param start              开始位置
	 * @param size               大小
	 * @param tips               等待提示信息
	 * @param needServiceProcess 是否需要服务类处理
	 */
	public void getNewsInfoList(int start, int size, String tips, final boolean needServiceProcess)
	{
		Map<String, Object> params = new HashMap<>();
		params.put("start", start);
		params.put("length", size);
		ajaxStringCallback(ApiConfig.GET_NEWS_LIST, params, true, tips, needServiceProcess);
	}

	@Override
	public void parseData(String url, String result, AjaxStatus status)
	{
		if (url.endsWith(ApiConfig.GET_NEWS_INFO))
		{
			ReturnInfo returnInfo = GsonTools.getReturnInfo(result);
			if (ReturnInfo.isSuccess(returnInfo))
			{
				//				ToastView.showCenterToast(context, R.drawable.ic_done, "意见反馈已提交！");
			}
			else
			{
				//				String msg = result;
				//				if (returnInfo != null)
				//				{
				//					msg = returnInfo.getMsg();
				//				}
				//				ToastView.showCenterToast(context, R.drawable.ic_do_fail, msg);
			}
		}
	}
}
