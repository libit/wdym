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
 * 合伙人服务类
 * Created by libit on 16/4/6.
 */
public class AgentService extends BaseService
{
	public AgentService(Context context)
	{
		super(context);
	}

	/**
	 * 查询金额
	 *
	 * @param moneyUnit          人民币或金币
	 * @param isToday            是否只查询今日
	 * @param tips               等待提示信息
	 * @param needServiceProcess 是否需要服务类处理
	 */
	public void getAgentBalanceLogMoney(byte moneyUnit, boolean isToday, String tips, final boolean needServiceProcess)
	{
		Map<String, Object> params = new HashMap<>();
		params.put("moneyUnit", moneyUnit);
		if (isToday)
		{
			params.put("startDateLong", TimeTools.getTodayBeginDateTimeLong());
			params.put("endDateLong", TimeTools.getTodayEndDateTimeLong());
		}
		ajaxStringCallback(ApiConfig.GET_AGENT_BALANCE_LOG_MONEY, params, true, tips, needServiceProcess);
	}

	/**
	 * 查询金额
	 *
	 * @param moneyUnit          人民币或金币
	 * @param isToday            是否只查询今日
	 * @param tips               等待提示信息
	 * @param needServiceProcess 是否需要服务类处理
	 */
	public void getAgentFansBalanceLogMoney(byte moneyUnit, boolean isToday, String tips, final boolean needServiceProcess)
	{
		Map<String, Object> params = new HashMap<>();
		params.put("moneyUnit", moneyUnit);
		if (isToday)
		{
			params.put("startDateLong", TimeTools.getTodayBeginDateTimeLong());
			params.put("endDateLong", TimeTools.getTodayEndDateTimeLong());
		}
		ajaxStringCallback(ApiConfig.GET_AGENT_FANS_BALANCE_LOG_MONEY, params, true, tips, needServiceProcess);
	}

	/**
	 * 查询金额
	 *
	 * @param moneyUnit          人民币或金币
	 * @param isToday            是否只查询今日
	 * @param tips               等待提示信息
	 * @param needServiceProcess 是否需要服务类处理
	 */
	public void getAgentReferrerBalanceLogMoney(byte moneyUnit, boolean isToday, String tips, final boolean needServiceProcess)
	{
		Map<String, Object> params = new HashMap<>();
		params.put("moneyUnit", moneyUnit);
		if (isToday)
		{
			params.put("startDateLong", TimeTools.getTodayBeginDateTimeLong());
			params.put("endDateLong", TimeTools.getTodayEndDateTimeLong());
		}
		ajaxStringCallback(ApiConfig.GET_AGENT_REFERRER_BALANCE_LOG_MONEY, params, true, tips, needServiceProcess);
	}
}
