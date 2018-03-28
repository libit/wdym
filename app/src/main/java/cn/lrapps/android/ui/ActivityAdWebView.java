/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.android.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.androidquery.callback.AjaxStatus;
import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.sdk.CookieSyncManager;
import com.tencent.smtt.sdk.DownloadListener;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.weiduyx.wdym.R;
import com.weiduyx.wdym.models.ReturnInfo;
import com.weiduyx.wdym.models.UserRedpackLogInfo;
import com.weiduyx.wdym.services.ApiConfig;
import com.weiduyx.wdym.services.IAjaxDataResponse;
import com.weiduyx.wdym.services.UserRedpackService;

import cn.lrapps.android.ui.customer.CircleTextProgressbar;
import cn.lrapps.android.ui.customer.ToastView;
import cn.lrapps.android.ui.customer.X5WebView;
import cn.lrapps.android.ui.dialog.DialogCommon;
import cn.lrapps.enums.AdType;
import cn.lrapps.utils.ConstValues;
import cn.lrapps.utils.CryptoTools;
import cn.lrapps.utils.GsonTools;
import cn.lrapps.utils.StringTools;
import cn.lrapps.utils.viewtools.DisplayTools;

public class ActivityAdWebView extends MyBaseActivity implements IAjaxDataResponse
{
	private static final String TAG = ActivityAdWebView.class.getSimpleName();
	private X5WebView mWebView;
	private ProgressBar progressBar;
	private ValueCallback<Uri> uploadFile;
	private String userRedpackLogId, adType, webTitle, url;
	private UserRedpackService mUserRedpackService;
	/**
	 * 默认类型。
	 */
	private CircleTextProgressbar mTvDefault;

	public static void startWebActivity(Context c, String userRedpackLogId, String adType, String title, String url)
	{
		Intent intent = new Intent(c, ActivityAdWebView.class);
		Bundle bundle = new Bundle();
		bundle.putString(ConstValues.DATA_USER_REDPACK_ID, userRedpackLogId);
		bundle.putString(ConstValues.DATA_AD_TYPE_ID, adType);
		bundle.putString(ConstValues.DATA_WEB_TITLE, title);
		bundle.putString(ConstValues.DATA_WEB_URL, url);
		intent.putExtras(bundle);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		c.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ad_web_view);
		getWindow().setFormat(PixelFormat.TRANSLUCENT);
		Bundle bundle = getIntent().getExtras();
		if (bundle != null)
		{
			userRedpackLogId = bundle.getString(ConstValues.DATA_USER_REDPACK_ID);
			adType = bundle.getString(ConstValues.DATA_AD_TYPE_ID);
			webTitle = bundle.getString(ConstValues.DATA_WEB_TITLE);
			setTitle(webTitle);
			url = bundle.getString(ConstValues.DATA_WEB_URL);
		}
		mUserRedpackService = new UserRedpackService(this);
		mUserRedpackService.addDataResponse(this);
		viewInit();
		loadUrl();
	}

	private void loadUrl()
	{
		if (!StringTools.isNull(url))
		{
			mWebView.loadUrl(url);
		}
	}

	public void viewInit()
	{
		super.viewInit();
		setBackButton();
		getSwipeBackLayout().setEdgeSize(DisplayTools.getWindowWidth(this) / 20);//设置滑动返回区域
		progressBar = (ProgressBar) findViewById(R.id.progress_bar);
		mWebView = (X5WebView) findViewById(R.id.webview);
		mTvDefault = (CircleTextProgressbar) findViewById(R.id.tv_ad_tm_down);
		mTvDefault.setInCircleColor(getResources().getColor(R.color.black));
		final int time = 30000;
		mTvDefault.setTimeMillis(time);
		mTvDefault.setCountdownProgressListener(1, new CircleTextProgressbar.OnCountdownProgressListener()
		{
			@Override
			public void onProgress(int what, int progress)
			{
				mTvDefault.setText(String.format("%d秒", time * progress / 100000));
				if (progress == 0)
				{
					mTvDefault.setText("领取红包");
					mTvDefault.setTextSize(16);
					mTvDefault.setTextColor(getResources().getColor(R.color.red));
					mTvDefault.setOnClickListener(new View.OnClickListener()
					{
						@Override
						public void onClick(View v)
						{
							if (AdType.NET.getType().equals(adType))
							{
								mUserRedpackService.getNetUserRedpack(userRedpackLogId, CryptoTools.getMD5Str(userRedpackLogId), "", true);
							}
							else if (AdType.COMMON.getType().equals(adType))
							{
								mUserRedpackService.sendRedpack(userRedpackLogId, CryptoTools.getMD5Str(userRedpackLogId), "", true);
							}
						}
					});
				}
			}
		});
		mTvDefault.start();
		mWebView.setWebChromeClient(new WebChromeClient()
		{
			@Override
			public void onReceivedTitle(WebView view, String title)
			{
				mToolbar.setTitle(title);
			}

			@Override
			public boolean onJsConfirm(WebView view, String url, String message, JsResult result)
			{
				return super.onJsConfirm(view, url, message, result);
			}

			@Override
			public void onProgressChanged(WebView view, int newProgress)
			{
				super.onProgressChanged(view, newProgress);
				progressBar.setProgress(newProgress);
			}

			View myVideoView;
			View myNormalView;
			IX5WebChromeClient.CustomViewCallback callback;
			// /////////////////////////////////////////////////////////
			//

			/**
			 * 全屏播放配置
			 */
			@Override
			public void onShowCustomView(View view, IX5WebChromeClient.CustomViewCallback customViewCallback)
			{
				FrameLayout normalView = (FrameLayout) findViewById(R.id.web_filechooser);
				ViewGroup viewGroup = (ViewGroup) normalView.getParent();
				viewGroup.removeView(normalView);
				viewGroup.addView(view);
				myVideoView = view;
				myNormalView = normalView;
				callback = customViewCallback;
			}

			@Override
			public void onHideCustomView()
			{
				if (callback != null)
				{
					callback.onCustomViewHidden();
					callback = null;
				}
				if (myVideoView != null)
				{
					ViewGroup viewGroup = (ViewGroup) myVideoView.getParent();
					viewGroup.removeView(myVideoView);
					viewGroup.addView(myNormalView);
				}
			}

			@Override
			public boolean onJsAlert(WebView arg0, String arg1, String arg2, JsResult arg3)
			{
				/**
				 * 这里写入你自定义的window alert
				 */
				return super.onJsAlert(null, arg1, arg2, arg3);
			}
		});
		mWebView.setWebViewClient(new WebViewClient()
		{
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon)
			{
				super.onPageStarted(view, url, favicon);
				progressBar.setVisibility(View.VISIBLE);
			}

			@Override
			public void onPageFinished(WebView view, String url)
			{
				super.onPageFinished(view, url);
				progressBar.setVisibility(View.GONE);
				if (!url.startsWith("http"))
				{
					openUrl(ActivityAdWebView.this, url);
				}
			}

			/**
			 * 防止加载网页时调起系统浏览器
			 */
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url)
			{
				//该方法在Build.VERSION_CODES.LOLLIPOP以前有效，从Build.VERSION_CODES.LOLLIPOP起，建议使用shouldOverrideUrlLoading(WebView, WebResourceRequest)} instead
				//返回false，意味着请求过程里，不管有多少次的跳转请求（即新的请求地址），均交给webView自己处理，这也是此方法的默认处理
				//返回true，说明你自己想根据url，做新的跳转，比如在判断url符合条件的情况下，我想让webView加载http://ask.csdn.net/questions/178242
				if (url.startsWith("http"))
				{
					view.loadUrl(url);
					return true;
				}
				return false;
			}

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request)
			{
				//返回false，意味着请求过程里，不管有多少次的跳转请求（即新的请求地址），均交给webView自己处理，这也是此方法的默认处理
				//返回true，说明你自己想根据url，做新的跳转，比如在判断url符合条件的情况下，我想让webView加载http://ask.csdn.net/questions/178242
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
				{
					if (request.getUrl().toString().startsWith("http"))
					{
						view.loadUrl(request.getUrl().toString());
						return true;
					}
				}
				return false;
			}
		});
		mWebView.setDownloadListener(new DownloadListener()
		{
			@Override
			public void onDownloadStart(final String url, String userAgent, String contentDisposition, String mimetype, long contentLength)
			{
				DialogCommon dialogCommon = new DialogCommon(ActivityAdWebView.this, new DialogCommon.LibitDialogListener()
				{
					@Override
					public void onOkClick()
					{
						openUrl(ActivityAdWebView.this, url);
					}

					@Override
					public void onCancelClick()
					{
					}
				}, "提示", "确定要下载" + url + "的内容吗？", true, false, true);
				dialogCommon.show();
				dialogCommon.setOKString("是的");
				dialogCommon.setCancelString("取消");
			}
		});
		// 禁止浏览器外部攻击
		mWebView.removeJavascriptInterface("searchBoxJavaBredge_");
		WebSettings webSetting = mWebView.getSettings();
		webSetting.setAllowFileAccess(true);
		webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
		webSetting.setSupportZoom(true);
		webSetting.setBuiltInZoomControls(true);
		webSetting.setUseWideViewPort(true);
		webSetting.setSupportMultipleWindows(false);
		// webSetting.setLoadWithOverviewMode(true);
		webSetting.setAppCacheEnabled(true);
		// webSetting.setDatabaseEnabled(true);
		webSetting.setDomStorageEnabled(true);
		webSetting.setJavaScriptEnabled(true);
		webSetting.setGeolocationEnabled(true);
		webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
		webSetting.setAppCachePath(this.getDir("appcache", 0).getPath());
		webSetting.setDatabasePath(this.getDir("databases", 0).getPath());
		webSetting.setGeolocationDatabasePath(this.getDir("geolocation", 0).getPath());
		// webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
		webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
		// webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
		// webSetting.setPreFectch(true);
		CookieSyncManager.createInstance(this);
		CookieSyncManager.getInstance().sync();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if (resultCode == RESULT_OK)
		{
			switch (requestCode)
			{
				case 0:
					if (null != uploadFile)
					{
						Uri result = data == null || resultCode != RESULT_OK ? null : data.getData();
						uploadFile.onReceiveValue(result);
						uploadFile = null;
					}
					break;
				default:
					break;
			}
		}
		else if (resultCode == RESULT_CANCELED)
		{
			if (null != uploadFile)
			{
				uploadFile.onReceiveValue(null);
				uploadFile = null;
			}
		}
	}

	@Override
	protected void onNewIntent(Intent intent)
	{
		if (intent == null || mWebView == null || intent.getData() == null)
			return;
		mWebView.loadUrl(intent.getData().toString());
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		//为了使WebView退出时音频或视频关闭
		mWebView.destroy();
	}

	@Override
	public void onBackPressed()
	{
		if (mWebView.canGoBack())
		{
			mWebView.goBack();
		}
		else
		{
			super.onBackPressed();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.menu_activity_webview, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		int id = item.getItemId();
		if (id == R.id.action_refresh)
		{
			mWebView.reload();
			return true;
		}
		else if (id == R.id.action_back)
		{
			if (mWebView.canGoBack())
			{
				mWebView.goBack();
			}
			return true;
		}
		else if (id == R.id.action_forward)
		{
			if (mWebView.canGoForward())
			{
				mWebView.goForward();
			}
			return true;
		}
		else if (id == R.id.action_home)
		{
			loadUrl();
			return true;
		}
		else if (id == R.id.action_close)
		{
			finish();
			return true;
		}
		else if (id == R.id.action_open_in_browser)
		{
			openUrl(this, mWebView.getUrl());
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public static void openUrl(Context context, String url)
	{
		try
		{
			Intent intent = new Intent().setAction(Intent.ACTION_VIEW);
			intent.setData(Uri.parse(url));
			context.startActivity(intent);
		}
		catch (Exception e)
		{
			String msg = e.getMessage();
			if (msg.contains("No Activity found"))
			{
				msg = "没有找到程序可打开" + url + "！";
			}
			ToastView.showCenterToast(context, R.drawable.ic_do_fail, msg);
		}
	}

	@Override
	public boolean onAjaxDataResponse(String url, String result, AjaxStatus status)
	{
		if (url.endsWith(ApiConfig.GET_NET_USER_REDPACK))
		{
			ReturnInfo returnInfo = GsonTools.getReturnInfo(result);
			if (ReturnInfo.isSuccess(returnInfo))
			{
				mTvDefault.setVisibility(View.GONE);
				int money = Integer.parseInt(returnInfo.getMsg());
				ToastView.showCenterToast(this, R.drawable.ic_done, "恭喜您领到" + StringTools.convertToRmb(money) + "元红包！");
			}
			else
			{
				String msg = "领取红包失败。";
				if (returnInfo != null)
				{
					msg = returnInfo.getMsg();
				}
				ToastView.showCenterToast(this, R.drawable.ic_do_fail, msg);
			}
		}
		else if (url.endsWith(ApiConfig.SEND_REDPACK))
		{
			ReturnInfo returnInfo = GsonTools.getReturnInfo(result);
			if (ReturnInfo.isSuccess(returnInfo))
			{
				mTvDefault.setVisibility(View.GONE);
				UserRedpackLogInfo userRedpackLogInfo = GsonTools.getReturnObject(returnInfo, UserRedpackLogInfo.class);
				if (userRedpackLogInfo != null)
				{
					ToastView.showCenterToast(this, R.drawable.ic_done, "恭喜您领到" + StringTools.convertToRmb(userRedpackLogInfo.getMoney()) + "元红包！");
				}
				else
				{
					ToastView.showCenterToast(this, R.drawable.ic_done, "恭喜您领到红包！");
				}
			}
			else
			{
				String msg = "领取红包失败。";
				if (returnInfo != null)
				{
					msg = returnInfo.getMsg();
				}
				ToastView.showCenterToast(this, R.drawable.ic_do_fail, msg);
			}
		}
		return true;
	}
}
