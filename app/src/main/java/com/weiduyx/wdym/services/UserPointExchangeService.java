/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.services;

import android.content.Context;

import cn.lrapps.utils.TimeTools;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户服务类，用于操作与用户相关的服务
 * Created by libit on 16/4/6.
 */
public class UserPointExchangeService extends BaseService
{
	public UserPointExchangeService(Context context)
	{
		super(context);
	}

	/**
	 * 查询兑换总金币
	 *
	 * @param isToday            是否只查询今日
	 * @param tips               等待提示信息
	 * @param needServiceProcess 是否需要服务类处理
	 */
	public void getTotalExchangePoint(boolean isToday, String tips, final boolean needServiceProcess)
	{
		Map<String, Object> params = new HashMap<>();
		if (isToday)
		{
			params.put("startDateLong", TimeTools.getTodayBeginDateTimeLong());
			params.put("endDateLong", TimeTools.getTodayEndDateTimeLong());
		}
		ajaxStringCallback(ApiConfig.GET_USER_POINT_EXCHANGE_TOTAL_POINT, params, true, tips, needServiceProcess);
	}

	/**
	 * 查询兑换总金额
	 *
	 * @param isToday            是否只查询今日
	 * @param tips               等待提示信息
	 * @param needServiceProcess 是否需要服务类处理
	 */
	public void getTotalExchangeMoney(boolean isToday, String tips, final boolean needServiceProcess)
	{
		Map<String, Object> params = new HashMap<>();
		if (isToday)
		{
			params.put("startDateLong", TimeTools.getTodayBeginDateTimeLong());
			params.put("endDateLong", TimeTools.getTodayEndDateTimeLong());
		}
		ajaxStringCallback(ApiConfig.GET_USER_POINT_EXCHANGE_TOTAL_MONEY, params, true, tips, needServiceProcess);
	}

	/**
	 * 兑换记录列表
	 *
	 * @param start              开始位置
	 * @param size               大小
	 * @param tips               等待提示信息
	 * @param needServiceProcess 是否需要服务类处理
	 */
	public void getUserPointExchangeInfoList(int start, int size, String tips, final boolean needServiceProcess)
	{
		Map<String, Object> params = new HashMap<>();
		params.put("start", start);
		params.put("length", size);
		ajaxStringCallback(ApiConfig.GET_USER_POINT_EXCHANGE_INFO_LIST, params, true, tips, needServiceProcess);
	}

	/**
	 * 用户金币兑换
	 *
	 * @param tips               等待提示信息
	 * @param needServiceProcess 是否需要服务类处理
	 */
	public void userPointToBalance(int point, String syncKey, String tips, final boolean needServiceProcess)
	{
		Map<String, Object> params = new HashMap<>();
		params.put("point", point);
		params.put("syncKey", syncKey);
		ajaxStringCallback(ApiConfig.POINT_TO_BALANCE, params, true, tips, needServiceProcess);
	}

	/**
	 * 用户金币兑换提示
	 *
	 * @param tips               等待提示信息
	 * @param needServiceProcess 是否需要服务类处理
	 */
	public void getPointExchangeTips(String tips, final boolean needServiceProcess)
	{
		Map<String, Object> params = new HashMap<>();
		ajaxStringCallback(ApiConfig.GET_USER_POINT_EXCHANGE_TIPS, params, true, tips, needServiceProcess);
	}
}
