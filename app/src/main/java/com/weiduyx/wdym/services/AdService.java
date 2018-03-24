/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.services;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

/**
 * 广告服务类
 * Created by libit on 16/4/6.
 */
public class AdService extends BaseService
{
	public AdService(Context context)
	{
		super(context);
	}

	/**
	 * 获取广告列表
	 *
	 * @param start              开始位置
	 * @param size               大小
	 * @param tips               等待提示信息
	 * @param needServiceProcess 是否需要服务类处理
	 */
	public void getAdInfoList(String typeId, Byte status, int start, int size, String tips, final boolean needServiceProcess)
	{
		Map<String, Object> params = new HashMap<>();
		params.put("typeId", typeId);
		params.put("status", status);
		params.put("start", start);
		params.put("length", size);
		ajaxStringCallback(ApiConfig.GET_AD_INFO_LIST, params, true, tips, needServiceProcess);
	}

	/**
	 * 获取广告信息
	 *
	 * @param adId               广告ID
	 * @param tips               等待提示信息
	 * @param needServiceProcess 是否需要服务类处理
	 */
	public void getAdInfo(String adId, String tips, final boolean needServiceProcess)
	{
		Map<String, Object> params = new HashMap<>();
		params.put("adId", adId);
		ajaxStringCallback(ApiConfig.GET_AD_INFO, params, true, tips, needServiceProcess);
	}
}
