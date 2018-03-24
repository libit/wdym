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
public class UserBalanceLogInfoService extends BaseService
{
	public UserBalanceLogInfoService(Context context)
	{
		super(context);
	}

	/**
	 * 查询总金额
	 *
	 * @param moneyUnit          人民币或金币
	 * @param isToday            是否只查询今日
	 * @param tips               等待提示信息
	 * @param needServiceProcess 是否需要服务类处理
	 */
	public void getTotalBalanceLogMoney(byte moneyUnit, boolean isToday, String tips, final boolean needServiceProcess)
	{
		Map<String, Object> params = new HashMap<>();
		params.put("moneyUnit", moneyUnit);
		if (isToday)
		{
			params.put("startDateLong", TimeTools.getTodayBeginDateTimeLong());
			params.put("endDateLong", TimeTools.getTodayEndDateTimeLong());
		}
		ajaxStringCallback(ApiConfig.GET_TOTAL_USER_BALANCE_LOG_MONEY, params, true, tips, needServiceProcess);
	}

	/**
	 * 查询金额
	 *
	 * @param logType            日志类型
	 * @param moneyUnit          人民币或金币
	 * @param isToday            是否只查询今日
	 * @param tips               等待提示信息
	 * @param needServiceProcess 是否需要服务类处理
	 */
	public void getBalanceLogMoney(String logType, byte moneyUnit, boolean isToday, String tips, final boolean needServiceProcess)
	{
		Map<String, Object> params = new HashMap<>();
		params.put("logType", logType);
		params.put("moneyUnit", moneyUnit);
		if (isToday)
		{
			params.put("startDateLong", TimeTools.getTodayBeginDateTimeLong());
			params.put("endDateLong", TimeTools.getTodayEndDateTimeLong());
		}
		ajaxStringCallback(ApiConfig.GET_USER_BALANCE_LOG_MONEY, params, true, tips, needServiceProcess);
	}

	/**
	 * 查询红包收益列表
	 *
	 * @param start              开始位置
	 * @param size               大小
	 * @param tips               等待提示信息
	 * @param needServiceProcess 是否需要服务类处理
	 */
	public void getUserRedpackBalanceLogInfoList(int start, int size, String tips, final boolean needServiceProcess)
	{
		Map<String, Object> params = new HashMap<>();
		params.put("start", start);
		params.put("length", size);
		params.put("columns[0][data]", "addDateLong");
		params.put("order[0][column]", "0");
		params.put("order[0][dir]", "desc");
		ajaxStringCallback(ApiConfig.GET_USER_REDPACK_BALANCE_LOG_INFO_LIST, params, true, tips, needServiceProcess);
	}

	/**
	 * 查询创客粉丝收益列表
	 *
	 * @param start              开始位置
	 * @param size               大小
	 * @param tips               等待提示信息
	 * @param needServiceProcess 是否需要服务类处理
	 */
	public void getMakerFansBalanceLogInfoList(int start, int size, String tips, final boolean needServiceProcess)
	{
		Map<String, Object> params = new HashMap<>();
		params.put("start", start);
		params.put("length", size);
		params.put("columns[0][data]", "addDateLong");
		params.put("order[0][column]", "0");
		params.put("order[0][dir]", "desc");
		ajaxStringCallback(ApiConfig.GET_MAKER_FANS_BALANCE_LOG_INFO_LIST, params, true, tips, needServiceProcess);
	}

	/**
	 * 查询合伙人收益列表
	 *
	 * @param start              开始位置
	 * @param size               大小
	 * @param tips               等待提示信息
	 * @param needServiceProcess 是否需要服务类处理
	 */
	public void getAgentProfitBalanceLogInfoList(int start, int size, String tips, final boolean needServiceProcess)
	{
		Map<String, Object> params = new HashMap<>();
		params.put("start", start);
		params.put("length", size);
		params.put("columns[0][data]", "addDateLong");
		params.put("order[0][column]", "0");
		params.put("order[0][dir]", "desc");
		ajaxStringCallback(ApiConfig.GET_AGENT_PROFIT_BALANCE_LOG_INFO_LIST, params, true, tips, needServiceProcess);
	}

	/**
	 * 查询合伙人粉丝收益列表
	 *
	 * @param start              开始位置
	 * @param size               大小
	 * @param tips               等待提示信息
	 * @param needServiceProcess 是否需要服务类处理
	 */
	public void getAgentUserFansBalanceLogInfoList(int start, int size, String tips, final boolean needServiceProcess)
	{
		Map<String, Object> params = new HashMap<>();
		params.put("start", start);
		params.put("length", size);
		params.put("columns[0][data]", "addDateLong");
		params.put("order[0][column]", "0");
		params.put("order[0][dir]", "desc");
		ajaxStringCallback(ApiConfig.GET_AGENT_USER_FANS_BALANCE_LOG_INFO_LIST, params, true, tips, needServiceProcess);
	}

	/**
	 * 查询合伙人推荐收益列表
	 *
	 * @param start              开始位置
	 * @param size               大小
	 * @param tips               等待提示信息
	 * @param needServiceProcess 是否需要服务类处理
	 */
	public void getAgentReferrerBalanceLogInfoList(int start, int size, String tips, final boolean needServiceProcess)
	{
		Map<String, Object> params = new HashMap<>();
		params.put("start", start);
		params.put("length", size);
		params.put("columns[0][data]", "addDateLong");
		params.put("order[0][column]", "0");
		params.put("order[0][dir]", "desc");
		ajaxStringCallback(ApiConfig.GET_AGENT_REFERRER_BALANCE_LOG_INFO_LIST, params, true, tips, needServiceProcess);
	}
}
