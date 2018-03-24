/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.services;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

/**
 * 第三方资讯服务类
 * Created by libit on 16/4/6.
 */
public class ThirdArticleInfoService extends BaseService
{
	public ThirdArticleInfoService(Context context)
	{
		super(context);
	}

	/**
	 * 获取资讯信息
	 *
	 * @param id                 资讯ID
	 * @param tips               等待提示信息
	 * @param needServiceProcess 是否需要服务类处理
	 */
	public void getThirdArticleInfo(String id, String tips, final boolean needServiceProcess)
	{
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		ajaxStringCallback(ApiConfig.GET_THIRD_ARTICLE_INFO, params, true, tips, needServiceProcess);
	}

	/**
	 * 获取资讯列表
	 *
	 * @param typeid             分类1
	 * @param typeid2            分类2
	 * @param start              开始位置
	 * @param size               大小
	 * @param tips               等待提示信息
	 * @param needServiceProcess 是否需要服务类处理
	 */
	public void getThirdArticleInfoList(String typeid, String typeid2, int start, int size, String tips, final boolean needServiceProcess)
	{
		Map<String, Object> params = new HashMap<>();
		params.put("typeid", typeid);
		params.put("typeid2", typeid2);
		params.put("start", start);
		params.put("length", size);
		ajaxStringCallback(ApiConfig.GET_THIRD_ARTICLE_INFO_LIST, params, true, tips, needServiceProcess);
	}
}
