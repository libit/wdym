/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.db;

import cn.lrapps.models.AppInfo;

import java.util.List;

/**
 * Created by libit on 15/8/31.
 */
public interface DbAppService
{
	/**
	 * 添加App
	 *
	 * @param appInfo App对象
	 *
	 * @return 成功：true，失败：false
	 */
	boolean add(AppInfo appInfo);

	/**
	 * 更新App
	 *
	 * @param appInfo App对象
	 *
	 * @return 成功：true，失败：false
	 */
	boolean update(AppInfo appInfo);

	/**
	 * 添加或更新App
	 *
	 * @param appInfo App对象
	 *
	 * @return 成功：true，失败：false
	 */
	boolean addOrUpdate(AppInfo appInfo);

	/**
	 * 增加或更新App列表
	 *
	 * @param appInfoList App列表
	 *
	 * @return 增加成功的个数
	 */
	int addOrUpdateList(List<AppInfo> appInfoList);

	/**
	 * 删除App
	 *
	 * @param packageName App包名
	 *
	 * @return 成功：true，失败：false
	 */
	boolean delete(String packageName);

	/**
	 * 清空App列表
	 */
	int deleteAll();

	/**
	 * 获取指定的App
	 *
	 * @param packageName App包名
	 *
	 * @return AppInfo
	 */
	AppInfo getAppInfo(String packageName);

	/**
	 * 获取App列表<br>
	 * 获取的都是存在的app
	 *
	 * @param appType App类型
	 *
	 * @return
	 */
	List<AppInfo> getAppInfoList(Integer appType, String orderCol, String orderType);

	/**
	 * 获取App列表数量
	 *
	 * @param appType App类型
	 *
	 * @return
	 */
	int getAppInfoListCount(Integer appType);
}
