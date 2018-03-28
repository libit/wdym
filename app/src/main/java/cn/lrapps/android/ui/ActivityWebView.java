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
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.sdk.CookieSyncManager;
import com.tencent.smtt.sdk.DownloadListener;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.weiduyx.wdym.R;

import java.io.File;

import cn.lrapps.android.ui.customer.ToastView;
import cn.lrapps.android.ui.customer.X5WebView;
import cn.lrapps.android.ui.dialog.DialogCommon;
import cn.lrapps.android.ui.dialog.DialogFileList;
import cn.lrapps.utils.ConstValues;
import cn.lrapps.utils.StringTools;
import cn.lrapps.utils.viewtools.DisplayTools;

public class ActivityWebView extends MyBaseActivity
{
	private static final String TAG = ActivityWebView.class.getSimpleName();
	public final static int FILECHOOSER_RESULTCODE = 1;
	public final static int FILECHOOSER_RESULTCODE_FOR_ANDROID_5 = 2;
	private X5WebView mWebView;
	private ProgressBar progressBar;
	private String webTitle, url;
	private ValueCallback<Uri> mUploadMessage;
	public ValueCallback<Uri[]> mUploadMessageForAndroid5;

	public static void startWebActivity(Context c, String title, String url)
	{
		Intent intent = new Intent(c, ActivityWebView.class);
		Bundle bundle = new Bundle();
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
		setContentView(R.layout.activity_web_view);
		getWindow().setFormat(PixelFormat.TRANSLUCENT);
		Bundle bundle = getIntent().getExtras();
		if (bundle != null)
		{
			webTitle = bundle.getString(ConstValues.DATA_WEB_TITLE);
			setTitle(webTitle);
			url = bundle.getString(ConstValues.DATA_WEB_URL);
		}
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
			}// API=21(5.0)

			@Override
			public boolean onShowFileChooser(WebView webView, final ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams)
			{
				// 自定义的选择文件对话框
				if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
				{
					new DialogFileList(ActivityWebView.this, new File(Environment.getExternalStorageDirectory().getAbsolutePath()), null, new DialogFileList.IDialogChooseFile()
					{
						@Override
						public void onFileSelected(File file)
						{
							if (file != null && file.exists() && !file.isDirectory())
							{
								Toast.makeText(ActivityWebView.this, "选择文件：" + file.getAbsolutePath(), Toast.LENGTH_LONG).show();
								filePathCallback.onReceiveValue(new Uri[]{Uri.parse("file:" + file.getAbsolutePath())});
							}
						}
					}, null).show();
				}
				else
				{
					ToastView.showCenterToast(ActivityWebView.this, R.drawable.ic_do_fail, "找不到存储！");
				}
				//系统选择文件对话框
				//				openFileChooserImplForAndroid5(filePathCallback);
				return true;
			}

			// For Android 3.0+
			public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType)
			{
				openFileChooserImpl(uploadMsg);
			}

			// For Android  > 4.1.1
			public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture)
			{
				openFileChooser(uploadMsg, acceptType);
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
					openUrl(ActivityWebView.this, url);
				}
			}

			/**
			 * 防止加载网页时调起系统浏览器
			 */
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url)
			{
				view.loadUrl(url);
				return true;
			}
		});
		mWebView.setDownloadListener(new DownloadListener()
		{
			@Override
			public void onDownloadStart(final String url, String userAgent, String contentDisposition, String mimetype, long contentLength)
			{
				DialogCommon dialogCommon = new DialogCommon(ActivityWebView.this, new DialogCommon.LibitDialogListener()
				{
					@Override
					public void onOkClick()
					{
						openUrl(ActivityWebView.this, url);
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

	// 5.0以下选择图片
	private void openFileChooserImpl(ValueCallback<Uri> uploadMsg)
	{
		mUploadMessage = uploadMsg;
		Intent i = new Intent(Intent.ACTION_GET_CONTENT);
		i.addCategory(Intent.CATEGORY_OPENABLE);
		i.setType("image/*");
		startActivityForResult(Intent.createChooser(i, "选择图片"), FILECHOOSER_RESULTCODE);
	}

	//5.0及以上选择图片
	private void openFileChooserImplForAndroid5(ValueCallback<Uri[]> uploadMsg)
	{
		mUploadMessageForAndroid5 = uploadMsg;
		Intent contentSelectionIntent = new Intent(Intent.ACTION_GET_CONTENT);
		contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE);
		contentSelectionIntent.setType("image/*");
		Intent chooserIntent = new Intent(Intent.ACTION_CHOOSER);
		chooserIntent.putExtra(Intent.EXTRA_INTENT, contentSelectionIntent);
		chooserIntent.putExtra(Intent.EXTRA_TITLE, "选择图片");
		startActivityForResult(chooserIntent, FILECHOOSER_RESULTCODE_FOR_ANDROID_5);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent)
	{
		if (requestCode == FILECHOOSER_RESULTCODE)
		{
			if (null == mUploadMessage)
				return;
			Uri result = intent == null || resultCode != RESULT_OK ? null : intent.getData();
			mUploadMessage.onReceiveValue(result);
			mUploadMessage = null;
		}
		else if (requestCode == FILECHOOSER_RESULTCODE_FOR_ANDROID_5)
		{
			if (null == mUploadMessageForAndroid5)
				return;
			Uri result = (intent == null || resultCode != RESULT_OK) ? null : intent.getData();
			if (result != null)
			{
				mUploadMessageForAndroid5.onReceiveValue(new Uri[]{result});
			}
			else
			{
				mUploadMessageForAndroid5.onReceiveValue(new Uri[]{});
			}
			mUploadMessageForAndroid5 = null;
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
}
