/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.services;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

/**
 * 区域服务类
 * Created by libit on 16/4/6.
 */
public class AreaService extends BaseService
{
	public AreaService(Context context)
	{
		super(context);
	}

	/**
	 * 获取省列表
	 *
	 * @param start              开始位置
	 * @param size               大小
	 * @param tips               等待提示信息
	 * @param needServiceProcess 是否需要服务类处理
	 */
	public void getProvinceInfoList(int start, int size, String tips, final boolean needServiceProcess)
	{
		Map<String, Object> params = new HashMap<>();
		params.put("start", start);
		params.put("length", size);
		ajaxStringCallback(ApiConfig.GET_PROVINCE_LIST, params, true, tips, needServiceProcess);
	}

	/**
	 * 获取市列表
	 *
	 * @param provinceId         省ID
	 * @param start              开始位置
	 * @param size               大小
	 * @param tips               等待提示信息
	 * @param needServiceProcess 是否需要服务类处理
	 */
	public void getCityInfoList(String provinceId, int start, int size, String tips, final boolean needServiceProcess)
	{
		Map<String, Object> params = new HashMap<>();
		params.put("provinceId", provinceId);
		params.put("start", start);
		params.put("length", size);
		ajaxStringCallback(ApiConfig.GET_CITY_LIST, params, true, tips, needServiceProcess);
	}

	/**
	 * 获取区列表
	 *
	 * @param cityId             市ID
	 * @param start              开始位置
	 * @param size               大小
	 * @param tips               等待提示信息
	 * @param needServiceProcess 是否需要服务类处理
	 */
	public void getDistrictInfoList(String cityId, int start, int size, String tips, final boolean needServiceProcess)
	{
		Map<String, Object> params = new HashMap<>();
		params.put("cityId", cityId);
		params.put("start", start);
		params.put("length", size);
		ajaxStringCallback(ApiConfig.GET_DISTRICT_LIST, params, true, tips, needServiceProcess);
	}
}
