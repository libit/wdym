/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.services;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户服务类，用于操作与用户相关的服务
 * Created by libit on 16/4/6.
 */
public class UserWithdrawService extends BaseService
{
	public UserWithdrawService(Context context)
	{
		super(context);
	}

	/**
	 * 查询提现总金额
	 *
	 * @param tips               等待提示信息
	 * @param needServiceProcess 是否需要服务类处理
	 */
	public void getTotalWithdrawMoney(String tips, final boolean needServiceProcess)
	{
		Map<String, Object> params = new HashMap<>();
		ajaxStringCallback(ApiConfig.GET_USER_WITHDRAW_TOTAL_MONEY, params, true, tips, needServiceProcess);
	}

	/**
	 * 提现记录列表
	 *
	 * @param start              开始位置
	 * @param size               大小
	 * @param tips               等待提示信息
	 * @param needServiceProcess 是否需要服务类处理
	 */
	public void getUserWithdrawInfoList(int start, int size, String tips, final boolean needServiceProcess)
	{
		Map<String, Object> params = new HashMap<>();
		params.put("start", start);
		params.put("length", size);
		ajaxStringCallback(ApiConfig.GET_USER_WITHDRAW_INFO_LIST, params, true, tips, needServiceProcess);
	}

	/**
	 * 用户提现
	 *
	 * @param money              提现金额
	 * @param moneyType          提现金额类型
	 * @param number             手机号码
	 * @param code               验证码
	 * @param tips               等待提示信息
	 * @param needServiceProcess 是否需要服务类处理
	 */
	public void addWithdraw(int money, byte moneyType, String number, String code, String tips, final boolean needServiceProcess)
	{
		Map<String, Object> params = new HashMap<>();
		params.put("money", money);
		params.put("moneyType", moneyType);
		params.put("number", number);
		params.put("code", code);
		ajaxStringCallback(ApiConfig.ADD_USER_WITHDRAW_INFO, params, true, tips, needServiceProcess);
	}

	/**
	 * 用户提现提示
	 *
	 * @param tips               等待提示信息
	 * @param needServiceProcess 是否需要服务类处理
	 */
	public void getWithdrawTips(String tips, final boolean needServiceProcess)
	{
		Map<String, Object> params = new HashMap<>();
		ajaxStringCallback(ApiConfig.GET_USER_WITHDRAW_TIPS, params, true, tips, needServiceProcess);
	}
}
