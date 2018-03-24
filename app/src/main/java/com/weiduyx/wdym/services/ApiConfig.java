/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package com.weiduyx.wdym.services;

import cn.lrapps.utils.PreferenceUtils;

/**
 * Created by libit on 16/4/6.
 */
public class ApiConfig
{
	public static final int API_VERSION = 1;
	public static final String DEBUG_URL = "http://192.168.168.9:8080/yyy-wdlm/";
	public static final String RELEASE_URL = "http://yao.weiduyx.com/";
	public static final String SUBMIT_BUG = getServerUrl() + "user/ajaxAddClientBugInfo";//BUG日志提交
	public static final String CHECK_UPDATE = getServerUrl() + "user/ajaxGetLastAndroidClientInfo";//检查更新
	public static final String UPLOAD_BUG_FILE = getServerUrl() + "user/ajaxUploadAndroidBugFile";//上传文件
	public static final String GET_CODE = getServerUrl() + "ajaxGetSmsCode";//获取验证码
	public static final String USER_SHARE = getServerUrl() + "user/ajaxUserShareApp";//用户分享App
	//意见反馈类型
	public static final String GET_ADVICE_TYPE_LIST = getServerUrl() + "user/ajaxGetAdviceTypeInfoList";//获取意见反馈类型列表
	public static final String GET_ADVICE_REPLY_LIST = getServerUrl() + "user/ajaxGetAdviceReplyInfoList";//获取意见反馈回复列表
	// 下载二维码
	public static final String GET_LAST_DOWNLOAD_QR = getServerUrl() + "user/ajaxGetLastClientDownloadQr";//下载二维码
	//接口
	//AdAction
	public static final String GET_AD_INFO = getServerUrl() + "user/ajaxGetAdInfo";
	public static final String GET_AD_INFO_LIST = getServerUrl() + "user/ajaxGetAdInfoList";
	//AdCooperationAction
	public static final String ADD_AD_COOPERATION_INFO = getServerUrl() + "user/ajaxAddAdCooperationInfo";
	public static final String GET_AD_COOPERATION_INFO = getServerUrl() + "user/ajaxGetAdCooperationInfo";
	public static final String GET_AD_COOPERATION_INFO_LIST = getServerUrl() + "user/ajaxGetAdCooperationInfoList";
	//AdDisplayLogAction
	public static final String UPDATE_AD_VISIT = getServerUrl() + "user/ajaxUpdateAdVisit";
	public static final String UPDATE_AD_CLICKED = getServerUrl() + "user/ajaxUpdateAdClicked";
	public static final String GET_AD_DISPLAY_LOG_INFO_LIST = getServerUrl() + "user/ajaxGetAdDisplayLogInfoList";
	public static final String GET_AD_DISPLAY_LOG_INFO_LIST_COUNT = getServerUrl() + "user/ajaxGetAdDisplayLogInfoListCount";
	public static final String GET_USER_AD_DISPLAY_LOG_INFO_LIST = getServerUrl() + "user/ajaxGetUserAdDisplayLogInfoList";
	public static final String GET_USER_AD_DISPLAY_LOG_INFO_LIST_COUNT = getServerUrl() + "user/ajaxGetUserAdDisplayLogInfoListCount";
	public static final String GET_DEVICE_AD_DISPLAY_LOG_INFO_LIST = getServerUrl() + "user/ajaxGetDeviceAdDisplayLogInfoList";
	public static final String GET_DEVICE_AD_DISPLAY_LOG_INFO_LIST_COUNT = getServerUrl() + "user/ajaxGetDeviceAdDisplayLogInfoListCount";
	//AdviceAction
	public static final String ADD_ADVICE_INFO = getServerUrl() + "user/ajaxAddAdviceInfo";
	public static final String GET_ADVICE_INFO = getServerUrl() + "user/ajaxGetAdviceInfo";
	public static final String GET_ADVICE_INFO_LIST = getServerUrl() + "user/ajaxGetAdviceInfoList";
	//AreaAction
	public static final String GET_PROVINCE_LIST = getServerUrl() + "user/ajaxGetProvinceList";
	public static final String GET_CITY_LIST = getServerUrl() + "user/ajaxGetCityList";
	public static final String GET_DISTRICT_LIST = getServerUrl() + "user/ajaxGetDistrictList";
	public static final String GET_COUNTRY_LIST = getServerUrl() + "user/ajaxGetCountryList";
	public static final String GET_PROVINCE_INFO = getServerUrl() + "user/ajaxGetProvinceInfo";
	public static final String GET_CITY_INFO = getServerUrl() + "user/ajaxGetCityInfo";
	public static final String GET_DISTRICT_INFO = getServerUrl() + "user/ajaxGetDistrictInfo";
	public static final String GET_COUNTRY_INFO = getServerUrl() + "user/ajaxGetCountryInfo";
	//ArticleReadLogAction
	public static final String ADD_ARTICLE_READ_LOG_INFO = getServerUrl() + "user/ajaxAddArticleReadLogInfo";
	//ArticleSortAction
	public static final String GET_ARTICLE_SORT_INFO = getServerUrl() + "user/ajaxGetArticleSortInfo";
	public static final String GET_ARTICLE_SORT_INFO_LIST = getServerUrl() + "user/ajaxGetArticleSortInfoList";
	//ArticleStarAction
	public static final String ADD_ARTICLE_STAR_INFO = getServerUrl() + "user/ajaxAddArticleStarInfo";
	public static final String GET_ARTICLE_STAR_INFO = getServerUrl() + "user/ajaxGetArticleStarInfo";
	public static final String DELETE_ARTICLE_STAR_INFO = getServerUrl() + "user/ajaxDeleteArticleStarInfo";
	public static final String GET_ARTICLE_STAR_INFO_LIST = getServerUrl() + "user/ajaxGetArticleStarInfoList";
	//ArticleThumbUpLogAction
	public static final String ADD_ARTICLE_THUMB_UP_LOG_INFO = getServerUrl() + "user/ajaxAddArticleThumbUpLogInfo";
	//ClientAction
	public static final String GET_CLIENT_BANNER_INFO_LIST = getServerUrl() + "user/ajaxGetClientBannerInfoList";
	//FileAction
	public static final String UPLOAD_PIC = getServerUrl() + "user/ajaxUploadPic";
	public static final String UPLOAD_BASE64_PIC = getServerUrl() + "user/ajaxUploadBase64Pic";
	public static final String UPLOAD_FILE = getServerUrl() + "user/ajaxUploadFile";
	//IndustryAction
	public static final String GET_INDUSTRY1_INFO = getServerUrl() + "user/ajaxGetIndustry1Info";
	public static final String GET_INDUSTRY2_INFO = getServerUrl() + "user/ajaxGetIndustry2Info";
	public static final String GET_INDUSTRY3_INFO = getServerUrl() + "user/ajaxGetIndustry3Info";
	public static final String GET_INDUSTRY1_INFO_LIST = getServerUrl() + "user/ajaxGetIndustry1InfoList";
	public static final String GET_INDUSTRY2_INFO_LIST = getServerUrl() + "user/ajaxGetIndustry2InfoList";
	public static final String GET_INDUSTRY3_INFO_LIST = getServerUrl() + "user/ajaxGetIndustry3InfoList";
	//MakerApplyEverQrInfoAction
	public static final String ADD_MAKER_APPLY_EVER_QR_INFO = getServerUrl() + "user/ajaxAddMakerApplyEverQrInfo";
	public static final String GET_MAKER_APPLY_EVER_QR_INFO = getServerUrl() + "user/ajaxGetMakerApplyEverQrInfo";
	//MakerStatisticAction
	public static final String GET_REFERRER_USER_INFO_LIST = getServerUrl() + "user/ajaxGetReferrerUserInfoList";
	public static final String GET_REFERRER_USER_INFO_LIST_COUNT = getServerUrl() + "user/ajaxGetReferrerUserInfoListCount";
	public static final String GET_SUB_REFERRER_USER_INFO_LIST = getServerUrl() + "user/ajaxGetSubReferrerUserInfoList";
	public static final String GET_SUB_REFERRER_USER_INFO_LIST_COUNT = getServerUrl() + "user/ajaxGetSubReferrerUserInfoListCount";
	public static final String GET_USER_AD_ACHIEVEMENTS_LIST = getServerUrl() + "user/ajaxGetUserAdAchievementsList";
	public static final String GET_USER_WIFI_AD_ACHIEVEMENTS_LIST = getServerUrl() + "user/ajaxGetUserWifiAdAchievementsList";
	//NewsAction
	public static final String GET_NEWS_INFO = getServerUrl() + "user/ajaxGetNewsInfo";
	public static final String GET_NEWS_LIST = getServerUrl() + "user/ajaxGetNewsList";
	public static final String GET_NEWS_LIST_COUNT = getServerUrl() + "user/ajaxGetNewsListCount";
	public static final String GET_UN_READ_NEWS_LIST_COUNT = getServerUrl() + "user/ajaxGetUnReadNewsListCount";
	//NewsSortAction
	public static final String GET_NEWS_SORT_LIST = getServerUrl() + "user/ajaxGetNewsSortList";
	//PromotionRangeAction
	public static final String GET_PROMOTION_RANGE_INFO = getServerUrl() + "user/ajaxGetPromotionRangeInfo";
	public static final String GET_PROMOTION_RANGE_INFO_LIST = getServerUrl() + "user/ajaxGetPromotionRangeInfoList";
	//SendRedpackAction
	public static final String GET_BRAND_REDPACK = getServerUrl() + "user/ajaxGetBrandRedpack";
	public static final String GET_QR_REDPACK = getServerUrl() + "user/ajaxGetQrRedpack";
	public static final String GET_REG_REDPACK = getServerUrl() + "user/ajaxGetRegRedpack";
	public static final String GET_DOWNLOAD_REDPACK = getServerUrl() + "user/ajaxGetDownloadRedpack";
	public static final String GET_SURVEY_REDPACK = getServerUrl() + "user/ajaxGetSurveyRedpack";
	public static final String GET_NET_AD_LIST = getServerUrl() + "user/ajaxGetNetAdList";
	public static final String SEND_REDPACK = getServerUrl() + "user/ajaxSendRedpack";
	public static final String REG_REDPACK_CALLBACK = getServerUrl() + "user/ajaxRegRedpackCallback";
	public static final String GET_MEEDUO_PROJECT_LIST = getServerUrl() + "user/ajaxGetMeeduoProjectList";
	public static final String MEEDUO_REDPACK_CALLBACK = getServerUrl() + "user/ajaxMeeduoRedpackCallback";
	public static final String NET_AD1_CALLBACK = getServerUrl() + "user/ajaxNetAd1Callback";
	public static final String GET_NET_USER_REDPACK = getServerUrl() + "user/ajaxGetNetUserRedpack";
	public static final String GET_BRAND_REDPACK_COUNT = getServerUrl() + "user/ajaxGetBrandRedpackCount";
	public static final String GET_REG_REDPACK_COUNT = getServerUrl() + "user/ajaxGetRegRedpackCount";
	public static final String GET_DOWNLOAD_AD_LIST = getServerUrl() + "user/ajaxGetDownloadAdList";
	//StatisticalAction
	public static final String GET_THIS_STAT_USER_MONTH_INFO = getServerUrl() + "user/ajaxGetThisStatUserMonthInfo";
	public static final String GET_LAST_STAT_USER_MONTH_INFO = getServerUrl() + "user/ajaxGetLastStatUserMonthInfo";
	public static final String GET_STAT_USER_MONTH_INFO = getServerUrl() + "user/ajaxGetStatUserMonthInfo";
	public static final String GET_USER_RECEIVE_PROFIT_TOTAL_MONEY = getServerUrl() + "user/ajaxGetUserReceiveProfitTotalMoney";
	public static final String GET_USER_RECEIVE_PROFIT_TOTAL_POINT = getServerUrl() + "user/ajaxGetUserReceiveProfitTotalPoint";
	//StatUserDayAction
	public static final String UPDATE_STAT_USER_DAY_INFO = getServerUrl() + "user/ajaxUpdateStatUserDayInfo";
	public static final String GET_STAT_USER_DAY_INFO = getServerUrl() + "user/ajaxGetStatUserDayInfo";
	public static final String GET_STAT_USER_DAY_INFO_LIST = getServerUrl() + "user/ajaxGetStatUserDayInfoList";
	public static final String GET_RANKING_STAT_USER_DAY_INFO_LIST = getServerUrl() + "user/ajaxGetRankingStatUserDayInfoList";
	//TxMapConfigAction
	public static final String GET_TX_MAP_CONFIG_INFO = getServerUrl() + "user/ajaxGetTxMapConfigInfo";
	public static final String GET_AVAILABLE_TX_MAP_CONFIG_INFO = getServerUrl() + "user/ajaxGetAvailableTxMapConfigInfo";
	//UserAction
	public static final String REGISTER = getServerUrl() + "user/ajaxRegister";
	public static final String LOGIN = getServerUrl() + "user/ajaxLogin";
	public static final String CHANGE_PWD = getServerUrl() + "user/ajaxChangePwd";
	public static final String UPDATE_USER_INFO = getServerUrl() + "user/ajaxUpdateUserInfo";
	public static final String UPDATE_USER_LOCATION = getServerUrl() + "user/ajaxUpdateUserLocation";
	public static final String UPDATE_USER_REFERRER = getServerUrl() + "user/ajaxUpdateUserReferrer";
	public static final String RESET_PWD = getServerUrl() + "user/ajaxResetPwd";
	public static final String SHARE_WX_GROUP_SUCCESS = getServerUrl() + "user/ajaxShareWxGroupSuccess";
	public static final String GET_WECHART_QR_URL = getServerUrl() + "user/ajaxGetWechartQrUrl";
	public static final String GET_THIRD_WECHART_QR_URL = getServerUrl() + "user/ajaxGetThirdWechartQrUrl";
	public static final String GET_USER_INFO = getServerUrl() + "user/ajaxGetUserInfo";
	public static final String GET_MY_USER_INFO = getServerUrl() + "user/ajaxGetMyUserInfo";
	public static final String GET_AGENT_OR_MAKER_USER_INFO_LIST = getServerUrl() + "user/ajaxGetAgentOrMakerUserInfoList";
	public static final String CHECK_AND_UPDATE_USER_INFO = getServerUrl() + "user/ajaxCheckAndUpdateUserInfo";
	public static final String GET_WX_USER_INFO = getServerUrl() + "user/ajaxGetWxUserInfo";
	public static final String UPDATE_USER_HEADER = getServerUrl() + "user/ajaxUpdateUserHeader";//上传用户头像
	//UserAddressAction
	public static final String ADD_USER_ADDRESS_INFO = getServerUrl() + "user/ajaxAddUserAddressInfo";
	public static final String UPDATE_USER_ADDRESS_INFO = getServerUrl() + "user/ajaxUpdateUserAddressInfo";
	public static final String DELETE_USER_ADDRESS_INFO = getServerUrl() + "user/ajaxDeleteUserAddressInfo";
	public static final String GET_USER_ADDRESS_INFO = getServerUrl() + "user/ajaxGetUserAddressInfo";
	public static final String GET_USER_ADDRESS_INFO_LIST = getServerUrl() + "user/ajaxGetUserAddressInfoList";
	public static final String GET_USER_ADDRESS_INFO_LIST_COUNT = getServerUrl() + "user/ajaxGetUserAddressInfoListCount";
	//UserAgentAction
	public static final String GET_USER_SALESMAN_LIST = getServerUrl() + "user/ajaxGetUserSalesmanList";
	public static final String GET_USER_SALESMAN_LIST_COUNT = getServerUrl() + "user/ajaxGetUserSalesmanListCount";
	public static final String SET_SALESMAN_REBATE = getServerUrl() + "user/ajaxSetSalesmanRebate";
	public static final String GET_USER_AGENT_INFO = getServerUrl() + "user/ajaxGetUserAgentInfo";
	public static final String GET_AGENT_USER_INFO_LIST = getServerUrl() + "user/ajaxGetAgentUserInfoList";
	//UserAuthAction
	public static final String UPDATE_USER_AUTH_INFO = getServerUrl() + "user/ajaxUpdateUserAuthInfo";
	public static final String UPDATE_USER_NUMBER_AUTH = getServerUrl() + "user/ajaxUpdateUserNumberAuth";
	public static final String GET_USER_AUTH_INFO = getServerUrl() + "user/ajaxGetUserAuthInfo";
	//UserBalanceLogAction
	public static final String GET_USER_BALANCE_LOG_INFO_LIST = getServerUrl() + "user/ajaxGetUserBalanceLogInfoList";
	public static final String GET_USER_REDPACK_BALANCE_LOG_INFO_LIST = getServerUrl() + "user/ajaxGetUserRedpackBalanceLogInfoList";
	public static final String GET_USER_PROFIT_BALANCE_LOG_INFO_LIST = getServerUrl() + "user/ajaxGetUserProfitBalanceLogInfoList";
	public static final String GET_USER_WIFI_DEVICE_PROFIT_TOTAL_MONEY = getServerUrl() + "user/ajaxGetUserWifiDeviceProfitTotalMoney";
	public static final String GET_USER_YYY_DEVICE_PROFIT_TOTAL_MONEY = getServerUrl() + "user/ajaxGetUserYyyDeviceProfitTotalMoney";
	public static final String GET_AGENT_PROFIT_BALANCE_LOG_INFO_LIST = getServerUrl() + "user/ajaxGetAgentProfitBalanceLogInfoList";
	public static final String GET_AGENT_USER_FANS_BALANCE_LOG_INFO_LIST = getServerUrl() + "user/ajaxGetAgentUserFansBalanceLogInfoList";
	public static final String GET_AGENT_USER_FANS_BALANCE_LOG_INFO_LIST_COUNT = getServerUrl() + "user/ajaxGetAgentUserFansBalanceLogInfoListCount";
	public static final String GET_AGENT_USER_MAKERS_BALANCE_LOG_INFO_LIST = getServerUrl() + "user/ajaxGetAgentUserMakersBalanceLogInfoList";
	public static final String GET_MAKER_FANS_BALANCE_LOG_INFO_LIST = getServerUrl() + "user/ajaxGetMakerFansBalanceLogInfoList";
	public static final String GET_AGENT_REFERRER_BALANCE_LOG_INFO_LIST = getServerUrl() + "user/ajaxGetAgentReferrerBalanceLogInfoList";
	public static final String GET_AGENT_REFERRER_BALANCE_LOG_INFO_LIST_COUNT = getServerUrl() + "user/ajaxGetAgentReferrerBalanceLogInfoListCount";
	public static final String GET_SHOP_PROFIT_BALANCE_LOG_INFO_LIST = getServerUrl() + "user/ajaxGetShopProfitBalanceLogInfoList";
	public static final String GET_AGENT_BALANCE_LOG_MONEY = getServerUrl() + "user/ajaxGetAgentBalanceLogMoney";
	public static final String GET_AGENT_FANS_BALANCE_LOG_MONEY = getServerUrl() + "user/ajaxGetAgentFansBalanceLogMoney";
	public static final String GET_AGENT_MAKERS_BALANCE_LOG_MONEY = getServerUrl() + "user/ajaxGetAgentMakersBalanceLogMoney";
	public static final String GET_MAKER_FANS_BALANCE_LOG_MONEY = getServerUrl() + "user/ajaxGetMakerFansBalanceLogMoney";
	public static final String GET_AGENT_REFERRER_BALANCE_LOG_MONEY = getServerUrl() + "user/ajaxGetAgentReferrerBalanceLogMoney";
	public static final String GET_SHOP_BALANCE_LOG_MONEY = getServerUrl() + "user/ajaxGetShopBalanceLogMoney";
	public static final String GET_USER_BALANCE_LOG_MONEY = getServerUrl() + "user/ajaxGetUserBalanceLogMoney";
	public static final String GET_TOTAL_USER_BALANCE_LOG_MONEY = getServerUrl() + "user/ajaxGetTotalUserBalanceLogMoney";
	//UserMakerAction
	public static final String GET_MAKER_USER_INFO_LIST = getServerUrl() + "user/ajaxGetMakerUserInfoList";
	//UserMakerApplyAction
	public static final String ADD_USER_MAKER_APPLY_INFO = getServerUrl() + "user/ajaxAddUserMakerApplyInfo";
	public static final String GET_USER_MAKER_APPLY_INFO = getServerUrl() + "user/ajaxGetUserMakerApplyInfo";
	public static final String GET_RECENT_USER_MAKER_APPLY_INFO = getServerUrl() + "user/ajaxGetRecentUserMakerApplyInfo";
	//UserNewsStatusAction
	public static final String ADD_USER_NEWS_STATUS_INFO = getServerUrl() + "user/ajaxAddUserNewsStatusInfo";
	public static final String GET_USER_NEWS_STATUS_INFO = getServerUrl() + "user/ajaxGetUserNewsStatusInfo";
	public static final String GET_USER_NEWS_STATUS_INFO_LIST = getServerUrl() + "user/ajaxGetUserNewsStatusInfoList";
	public static final String GET_USER_NEWS_STATUS_INFO_LIST_COUNT = getServerUrl() + "user/ajaxGetUserNewsStatusInfoListCount";
	//UserPointExchangeInfoAction
	public static final String POINT_TO_BALANCE = getServerUrl() + "user/ajaxPointToBalance";
	public static final String GET_USER_POINT_EXCHANGE_INFO = getServerUrl() + "user/ajaxGetUserPointExchangeInfo";
	public static final String GET_USER_POINT_EXCHANGE_INFO_LIST = getServerUrl() + "user/ajaxGetUserPointExchangeInfoList";
	public static final String GET_USER_POINT_EXCHANGE_TOTAL_POINT = getServerUrl() + "user/ajaxGetUserPointExchangeTotalPoint";
	public static final String GET_USER_POINT_EXCHANGE_TOTAL_MONEY = getServerUrl() + "user/ajaxGetUserPointExchangeTotalMoney";
	public static final String GET_USER_POINT_EXCHANGE_TIPS = getServerUrl() + "user/ajaxGetUserPointExchangeTips";
	//UserRedpackLogAction
	public static final String GET_USER_REDPACK_LOG_INFO_LIST = getServerUrl() + "user/ajaxGetUserRedpackLogInfoList";
	public static final String GET_USER_REDPACK_LOG_INFO_LIST_COUNT = getServerUrl() + "user/ajaxGetUserRedpackLogInfoListCount";
	public static final String GET_RECEIVE_REDPACK_INFO_LIST = getServerUrl() + "user/ajaxGetReceiveRedpackInfoList";
	public static final String GET_USER_RECEIVE_REDPACK_TOTAL_MONEY = getServerUrl() + "user/ajaxGetUserReceiveRedpackTotalMoney";
	public static final String GET_USER_RECEIVE_REDPACK_TOTAL_POINT = getServerUrl() + "user/ajaxGetUserReceiveRedpackTotalPoint";
	public static final String UPDATE_USER_REDPACK_LOG_SHARE_DATE = getServerUrl() + "user/ajaxUpdateUserRedpackLogShareDate";
	public static final String GET_USER_REDPACK_LOG_AD_MONEY = getServerUrl() + "user/ajaxGetUserRedpackLogAdMoney";
	public static final String GET_USER_REDPACK_LOG_AD_SHARE_MONEY = getServerUrl() + "user/ajaxGetUserRedpackLogAdShareMoney";
	//UserWithdrawAction
	public static final String GET_USER_WITHDRAW_TOTAL_MONEY = getServerUrl() + "user/ajaxGetUserWithdrawTotalMoney";
	public static final String GET_USER_TODAY_MONTH_REST_WITHDRAW_COUNT = getServerUrl() + "user/ajaxGetUserTodayMonthRestWithdrawCount";
	public static final String ADD_USER_WITHDRAW_INFO = getServerUrl() + "user/ajaxAddUserWithdrawInfo";
	public static final String GET_USER_WITHDRAW_INFO = getServerUrl() + "user/ajaxGetUserWithdrawInfo";
	public static final String GET_USER_WITHDRAW_INFO_LIST = getServerUrl() + "user/ajaxGetUserWithdrawInfoList";
	public static final String GET_NEW_USER_WITHDRAW_INFO_LIST = getServerUrl() + "user/ajaxGetNewUserWithdrawInfoList";
	public static final String GET_USER_WITHDRAW_TIPS = getServerUrl() + "user/ajaxGetUserWithdrawTips";
	//WxAction
	public static final String WX_APP_AUTH_CALLBACK = getServerUrl() + "user/ajaxWxAppAuthCallback";
	//WxPayAction
	public static final String GET_WX_PRE_PAY = getServerUrl() + "user/ajaxGetWxPrePay";
	//BookInfoAction
	public static final String GET_BOOK_INFO = getServerUrl() + "user/ajaxGetBookInfo";
	public static final String GET_BOOK_INFO_LIST = getServerUrl() + "user/ajaxGetBookInfoList";
	//MovieInfoAction
	public static final String GET_MOVIE_INFO = getServerUrl() + "user/ajaxGetMovieInfo";
	public static final String GET_MOVIE_INFO_LIST = getServerUrl() + "user/ajaxGetMovieInfoList";
	//ThirdArticleInfoAction
	public static final String GET_THIRD_ARTICLE_INFO = getServerUrl() + "user/ajaxGetThirdArticleInfo";
	public static final String GET_THIRD_ARTICLE_INFO_LIST = getServerUrl() + "user/ajaxGetThirdArticleInfoList";

	public static String getServerUrl()
	{
		//		if (AppConfig.isDebug())
		//		{
		//				String url = DEBUG_URL;
		//		}
		//		else
		//		{
		String url = RELEASE_URL;
		if (!url.endsWith("/"))
		{
			url += "/";
		}
		return url;
		//		}
	}

	/**
	 * 获取服务器图片地址
	 *
	 * @param picUrl 图片地址
	 *
	 * @return
	 */
	public static String getServerPicUrl(String picUrl)
	{
		return getServerUrl() + picUrl;
	}

	/**
	 * 获取消息地址
	 *
	 * @param newsId 消息ID
	 *
	 * @return
	 */
	public static String getNewsUrl(String newsId)
	{
		return getServerUrl() + "user/news?newsId=" + newsId;
	}

	/**
	 * 帮助地址
	 *
	 * @return
	 */
	public static String getHelpUrl()
	{
		return getServerUrl() + "user/pageProductConsultingListHelp";
	}

	/**
	 * 下载地址
	 *
	 * @return
	 */
	public static String getDownloadUrl()
	{
		return getServerUrl() + "down";
	}

	/**
	 * 创客分享地址
	 *
	 * @return
	 */
	public static String getMakerShareUrl()
	{
		return getServerUrl() + "user/makerShare?userId=" + PreferenceUtils.getInstance().getUserId() + "&sessionId=" + PreferenceUtils.getInstance().getSessionId();
	}

	/**
	 * 合伙人分享地址
	 *
	 * @return
	 */
	public static String getAgentShareUrl()
	{
		return getServerUrl() + "user/agentShare?userId=" + PreferenceUtils.getInstance().getUserId() + "&sessionId=" + PreferenceUtils.getInstance().getSessionId();
	}

	/**
	 * 获取资讯详情地址
	 *
	 * @param id 资讯ID
	 *
	 * @return
	 */
	public static String getThirdArticleUrl(int id)
	{
		return getServerUrl() + "user/thirdArticleInfo?id=" + id + "&userId=" + PreferenceUtils.getInstance().getUserId() + "&sessionId=" + PreferenceUtils.getInstance().getSessionId();
	}
}
