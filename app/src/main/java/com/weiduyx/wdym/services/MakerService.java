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
 * 创客服务类
 * Created by libit on 16/4/6.
 */
public class MakerService extends BaseService
{
	public MakerService(Context context)
	{
		super(context);
	}

	/**
	 * 更新用户申请创客资料
	 *
	 * @param name               姓名
	 * @param number             手机号码
	 * @param code               验证码
	 * @param sex                性别
	 * @param identifityNumber   身份证
	 * @param province           省
	 * @param city               市
	 * @param picUrl             头像地址
	 * @param tips               等待提示信息
	 * @param needServiceProcess 是否需要服务类处理
	 */
	public void updateMakerApply(String name, String number, String code, Byte sex, String identifityNumber, String province, String city, String picUrl, String tips, final boolean needServiceProcess)
	{
		Map<String, Object> params = new HashMap<>();
		params.put("name", name);
		params.put("number", number);
		params.put("code", code);
		params.put("sex", sex);
		params.put("identifityNumber", identifityNumber);
		params.put("province", province);
		params.put("city", city);
		params.put("picUrl", picUrl);
		ajaxStringCallback(ApiConfig.ADD_USER_MAKER_APPLY_INFO, params, true, tips, needServiceProcess);
	}

	/**
	 * 查询金额
	 *
	 * @param moneyUnit          人民币或金币
	 * @param isToday            是否只查询今日
	 * @param tips               等待提示信息
	 * @param needServiceProcess 是否需要服务类处理
	 */
	public void getMakerFansBalanceLogMoney(byte moneyUnit, boolean isToday, String tips, final boolean needServiceProcess)
	{
		Map<String, Object> params = new HashMap<>();
		params.put("moneyUnit", moneyUnit);
		if (isToday)
		{
			params.put("startDateLong", TimeTools.getTodayBeginDateTimeLong());
			params.put("endDateLong", TimeTools.getTodayEndDateTimeLong());
		}
		ajaxStringCallback(ApiConfig.GET_MAKER_FANS_BALANCE_LOG_MONEY, params, true, tips, needServiceProcess);
	}

	/**
	 * 申请永久二维码
	 *
	 * @param agentId            合伙人账号
	 * @param content            申请理由
	 * @param tips               等待提示信息
	 * @param needServiceProcess 是否需要服务类处理
	 */
	public void addMakerApplyEverQrInfo(String agentId, String content, String tips, final boolean needServiceProcess)
	{
		Map<String, Object> params = new HashMap<>();
		params.put("agentId", agentId);
		params.put("content", content);
		ajaxStringCallback(ApiConfig.ADD_MAKER_APPLY_EVER_QR_INFO, params, true, tips, needServiceProcess);
	}

	/**
	 * 获取申请永久二维码信息
	 *
	 * @param tips               等待提示信息
	 * @param needServiceProcess 是否需要服务类处理
	 */
	public void getMakerApplyEverQrInfo(String tips, final boolean needServiceProcess)
	{
		Map<String, Object> params = new HashMap<>();
		ajaxStringCallback(ApiConfig.GET_MAKER_APPLY_EVER_QR_INFO, params, true, tips, needServiceProcess);
	}
}
