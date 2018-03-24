/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.services;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

/**
 * 小说服务类
 * Created by libit on 16/4/6.
 */
public class BookInfoService extends BaseService
{
	public BookInfoService(Context context)
	{
		super(context);
	}

	/**
	 * 获取小说信息
	 *
	 * @param bookId             小说ID
	 * @param tips               等待提示信息
	 * @param needServiceProcess 是否需要服务类处理
	 */
	public void getBookInfo(String bookId, String tips, final boolean needServiceProcess)
	{
		Map<String, Object> params = new HashMap<>();
		params.put("bookId", bookId);
		ajaxStringCallback(ApiConfig.GET_BOOK_INFO, params, true, tips, needServiceProcess);
	}

	/**
	 * 获取小说列表
	 *
	 * @param start              开始位置
	 * @param size               大小
	 * @param tips               等待提示信息
	 * @param needServiceProcess 是否需要服务类处理
	 */
	public void getBookInfoList(int start, int size, String tips, final boolean needServiceProcess)
	{
		Map<String, Object> params = new HashMap<>();
		params.put("start", start);
		params.put("length", size);
		ajaxStringCallback(ApiConfig.GET_BOOK_INFO_LIST, params, true, tips, needServiceProcess);
	}
}
