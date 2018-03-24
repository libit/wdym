/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.services;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

/**
 * 电影服务类
 * Created by libit on 16/4/6.
 */
public class MovieInfoService extends BaseService
{
	public MovieInfoService(Context context)
	{
		super(context);
	}

	/**
	 * 获取电影信息
	 *
	 * @param movieId            电影ID
	 * @param tips               等待提示信息
	 * @param needServiceProcess 是否需要服务类处理
	 */
	public void getMovieInfo(String movieId, String tips, final boolean needServiceProcess)
	{
		Map<String, Object> params = new HashMap<>();
		params.put("movieId", movieId);
		ajaxStringCallback(ApiConfig.GET_MOVIE_INFO, params, true, tips, needServiceProcess);
	}

	/**
	 * 获取电影列表
	 *
	 * @param start              开始位置
	 * @param size               大小
	 * @param tips               等待提示信息
	 * @param needServiceProcess 是否需要服务类处理
	 */
	public void getMovieInfoList(int start, int size, String tips, final boolean needServiceProcess)
	{
		Map<String, Object> params = new HashMap<>();
		params.put("start", start);
		params.put("length", size);
		ajaxStringCallback(ApiConfig.GET_MOVIE_INFO_LIST, params, true, tips, needServiceProcess);
	}
}
