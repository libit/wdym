/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.services;

import android.content.Context;

import com.androidquery.callback.AjaxStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户群发消息日志服务类
 * Created by libit on 16/4/6.
 */
public class ClientBannerService extends BaseService
{
	public ClientBannerService(Context context)
	{
		super(context);
	}

	/**
	 * 获取客户端展示图列表
	 *
	 * @param bannerPosition     显示位置
	 * @param tips               等待提示信息
	 * @param needServiceProcess 是否需要服务类处理
	 */
	public void getClientBannerInfoList(String bannerPosition, String bannerType, String tips, final boolean needServiceProcess)
	{
		Map<String, Object> params = new HashMap<>();
		params.put("bannerPosition", bannerPosition);
		params.put("bannerType", bannerType);
		ajaxStringCallback(ApiConfig.GET_CLIENT_BANNER_INFO_LIST, params, true, tips, needServiceProcess);
	}

	@Override
	public void parseData(String url, String result, AjaxStatus status)
	{
		if (url.endsWith(ApiConfig.GET_CLIENT_BANNER_INFO_LIST))
		{
		}
	}
}
