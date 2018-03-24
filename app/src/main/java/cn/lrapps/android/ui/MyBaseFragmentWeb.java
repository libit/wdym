/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.android.ui;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.external.xlistview.XListView;
import com.weiduyx.wdym.services.ApiConfig;
import com.weiduyx.wdym.services.IAjaxDataResponse;
import com.weiduyx.wdym.R;

public abstract class MyBaseFragmentWeb extends MyBaseFragment implements IAjaxDataResponse
{
	protected static final String TAG = MyBaseFragmentWeb.class.getSimpleName();
	private XListView xListView;
	private View headView;
	private WebView webView;
	private ProgressBar progressBar;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View rootView = inflater.inflate(R.layout.fragment_web_view, container, false);
		viewInit(rootView);
		initService();
		refreshData();
		return rootView;
	}

	//初始化服务
	protected abstract void initService();

	//刷新数据
	protected abstract void refreshData();

	@Override
	@android.webkit.JavascriptInterface
	protected void viewInit(View rootView)
	{
		xListView = (XListView) rootView.findViewById(R.id.xlist);
		headView = LayoutInflater.from(this.getContext()).inflate(R.layout.activity_web_view_system_head, null);
		xListView.setPullRefreshEnable(false);
		xListView.setPullLoadEnable(false);
		xListView.addHeaderView(headView);
		xListView.setAdapter(null);
		progressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);
		webView = (WebView) rootView.findViewById(R.id.webview);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.clearCache(true);
		webView.clearHistory();
		webView.setWebViewClient(new WebViewClient()
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
			}
		});
		// 禁止浏览器外部攻击
		webView.removeJavascriptInterface("searchBoxJavaBredge_");
		webView.addJavascriptInterface(this, "lr");
		super.viewInit(rootView);
	}

	protected void loadWebData(String data)
	{
		//		String html = String.format("<html><body style=\"width:100%;\">%s</body></html>", data);
		webView.loadDataWithBaseURL(ApiConfig.getServerUrl(), data, "text/html", "utf-8", null);
	}

	@android.webkit.JavascriptInterface
	public void actionFromJsToStartVipIndution()
	{
		this.getActivity().runOnUiThread(new Runnable()
		{
			@Override
			public void run()
			{
				//				MyBaseFragmentWeb.this.getActivity().startActivity(new Intent(MyBaseFragmentWeb.this.getContext(), ActivityVIPIndution.class));
			}
		});
	}
	//	@android.webkit.JavascriptInterface
	//	public void actionFromJsWithParam(final String str)
	//	{
	//		this.getActivity().runOnUiThread(new Runnable()
	//		{
	//			@Override
	//			public void run()
	//			{
	//				Toast.makeText(MainActivity.this, "js调用了Native函数传递参数：" + str, Toast.LENGTH_SHORT).show();
	//				String text = logTextView.getText() + "\njs调用了Native函数传递参数：" + str;
	//				logTextView.setText(text);
	//			}
	//		});
	//	}
	//	button.setOnClickListener(new Button.OnClickListener()
	//	{
	//		public void onClick (View v){
	//		// 无参数调用
	//		mWebView.loadUrl("javascript:actionFromNative()");
	//		// 传递参数调用
	//		mWebView.loadUrl("javascript:actionFromNativeWithParam(" + "'come from Native'" + ")");
	//	}
	//	});
}
