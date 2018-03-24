/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.services;

import android.content.Context;

import com.androidquery.callback.AjaxStatus;
import cn.lrapps.utils.GsonTools;
import cn.lrapps.utils.PreferenceUtils;
import com.weiduyx.wdym.models.ReturnInfo;
import com.weiduyx.wdym.models.UserInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信服务类
 * Created by libit on 16/4/6.
 */
public class WxService extends BaseService
{
	public WxService(Context context)
	{
		super(context);
	}

	/**
	 * 登录认证
	 *
	 * @param appId              微信授权APPID
	 * @param code               授权code
	 * @param state              自定义
	 * @param tips               等待提示信息
	 * @param needServiceProcess 是否需要服务类处理
	 */
	public void appAuth(String appId, String code, String state, String tips, final boolean needServiceProcess)
	{
		Map<String, Object> params = new HashMap<>();
		params.put("appId", appId);
		params.put("code", code);
		params.put("state", state);
		ajaxStringCallback(ApiConfig.WX_APP_AUTH_CALLBACK, params, true, tips, needServiceProcess);
	}

	@Override
	public void parseData(String url, String result, AjaxStatus status)
	{
		if (url.endsWith(ApiConfig.WX_APP_AUTH_CALLBACK))
		{
			ReturnInfo returnInfo = GsonTools.getReturnInfo(result);
			if (ReturnInfo.isSuccess(returnInfo))
			{
				//登录成功，保存账号和SessionId
				UserInfo userInfo = GsonTools.getReturnObject(returnInfo, UserInfo.class);
				PreferenceUtils.getInstance().setUserId(userInfo.getUserId());
				PreferenceUtils.getInstance().setSessionId(userInfo.getSessionId());
			}
			else
			{
				// 登录失败，清空SessionId
				PreferenceUtils.getInstance().setSessionId("");
			}
		}
	}
}
