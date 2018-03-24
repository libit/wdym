/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.services;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import cn.lrapps.enums.StatusType;

/**
 * 用户服务类，用于操作与用户相关的服务
 * Created by libit on 16/4/6.
 */
public class UserRedpackService extends BaseService
{
	public UserRedpackService(Context context)
	{
		super(context);
	}

	/**
	 * 红包记录列表
	 *
	 * @param start              开始位置
	 * @param size               大小
	 * @param tips               等待提示信息
	 * @param needServiceProcess 是否需要服务类处理
	 */
	public void getUserRedpackLogInfoList(int start, int size, String tips, final boolean needServiceProcess)
	{
		Map<String, Object> params = new HashMap<>();
		params.put("start", start);
		params.put("length", size);
		params.put("status", StatusType.ENABLE.getStatus());
		params.put("columns[0][data]", "addDateLong");
		params.put("order[0][column]", "0");
		params.put("order[0][dir]", "desc");
		ajaxStringCallback(ApiConfig.GET_USER_REDPACK_LOG_INFO_LIST, params, true, tips, needServiceProcess);
	}

	/**
	 * 红包记录数量
	 *
	 * @param tips               等待提示信息
	 * @param needServiceProcess 是否需要服务类处理
	 */
	public void getUserRedpackLogInfoListCount(String tips, final boolean needServiceProcess)
	{
		Map<String, Object> params = new HashMap<>();
		ajaxStringCallback(ApiConfig.GET_USER_REDPACK_LOG_INFO_LIST_COUNT, params, true, tips, needServiceProcess);
	}

	/**
	 * 获取品牌红包
	 *
	 * @param tips               等待提示信息
	 * @param needServiceProcess 是否需要服务类处理
	 */
	public void getBrandRedpack(String wxOpenId, String syncKey, String tips, final boolean needServiceProcess)
	{
		Map<String, Object> params = new HashMap<>();
		params.put("deviceId", "0");
		params.put("wxOpenId", wxOpenId);
		params.put("syncKey", syncKey);
		ajaxStringCallback(ApiConfig.GET_BRAND_REDPACK, params, true, tips, needServiceProcess);
	}

	/**
	 * 网盟广告列表
	 *
	 * @param start              开始位置
	 * @param size               大小
	 * @param tips               等待提示信息
	 * @param needServiceProcess 是否需要服务类处理
	 */
	public void getNetAdList(int start, int size, String tips, final boolean needServiceProcess)
	{
		Map<String, Object> params = new HashMap<>();
		params.put("start", start);
		params.put("length", size);
		ajaxStringCallback(ApiConfig.GET_NET_AD_LIST, params, true, tips, needServiceProcess);
	}

	/**
	 * 下载广告列表
	 *
	 * @param start              开始位置
	 * @param size               大小
	 * @param tips               等待提示信息
	 * @param needServiceProcess 是否需要服务类处理
	 */
	public void getDownloadAdList(int start, int size, String tips, final boolean needServiceProcess)
	{
		Map<String, Object> params = new HashMap<>();
		params.put("start", start);
		params.put("length", size);
		ajaxStringCallback(ApiConfig.GET_DOWNLOAD_AD_LIST, params, true, tips, needServiceProcess);
	}

	/**
	 * 发放红包
	 *
	 * @param tips               等待提示信息
	 * @param needServiceProcess 是否需要服务类处理
	 */
	public void sendRedpack(String userRedpackLogId, String syncKey, String tips, final boolean needServiceProcess)
	{
		Map<String, Object> params = new HashMap<>();
		params.put("userRedpackLogId", userRedpackLogId);
		params.put("syncKey", syncKey);
		ajaxStringCallback(ApiConfig.SEND_REDPACK, params, true, tips, needServiceProcess);
	}

	/**
	 * 发放网盟红包
	 *
	 * @param tips               等待提示信息
	 * @param needServiceProcess 是否需要服务类处理
	 */
	public void getNetUserRedpack(String adId, String syncKey, String tips, final boolean needServiceProcess)
	{
		Map<String, Object> params = new HashMap<>();
		params.put("adId", adId);
		params.put("syncKey", syncKey);
		ajaxStringCallback(ApiConfig.GET_NET_USER_REDPACK, params, true, tips, needServiceProcess);
	}
}
