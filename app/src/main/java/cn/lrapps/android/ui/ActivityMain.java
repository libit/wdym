/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.android.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.callback.AjaxStatus;
import com.external.xlistview.XListView;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.weiduyx.wdym.R;
import com.weiduyx.wdym.models.PicInfo;
import com.weiduyx.wdym.models.ReturnInfo;
import com.weiduyx.wdym.models.UpdateInfo;
import com.weiduyx.wdym.models.UserInfo;
import com.weiduyx.wdym.services.ApiConfig;
import com.weiduyx.wdym.services.BugService;
import com.weiduyx.wdym.services.FileService;
import com.weiduyx.wdym.services.IAjaxDataResponse;
import com.weiduyx.wdym.services.UpdateService;
import com.weiduyx.wdym.services.UserService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import cn.lrapps.android.ui.customer.LayoutSideMain;
import cn.lrapps.android.ui.customer.MyActionBarDrawerToggle;
import cn.lrapps.android.ui.customer.ToastView;
import cn.lrapps.enums.EventTypeLayoutSideMain;
import cn.lrapps.enums.UserEvent;
import cn.lrapps.models.TabInfo;
import cn.lrapps.utils.GlideImageLoader;
import cn.lrapps.utils.GsonTools;
import cn.lrapps.utils.PreferenceUtils;
import cn.lrapps.utils.apptools.SystemToolsFactory;

import static cn.lrapps.utils.ConstValues.IMAGE_PICKER;

public class ActivityMain extends MyBaseActivity implements MyActionBarDrawerToggle.ActionBarDrawerToggleStatusChanged, XListView.IXListViewListener, IAjaxDataResponse
{
	private static final String TAG = ActivityMain.class.getSimpleName();
	public static final int INDEX = 0;
	public static final int PROFIT = 1;
	public static final int JUAN = 2;
	public static final int ARTICLE = 3;
	public static final int USER = 4;
	private static ActivityMain instance;
	protected int pageIndex = 0;
	//视图控件
	private View vHead;
	protected ViewPager mViewPager;
	private XListView xListView;
	private LayoutSideMain layoutSideMain;
	private DrawerLayout mDrawerLayout;
	private UserService mUserService;
	private FileService mFileService;
	private SystemToolsFactory systemTools = SystemToolsFactory.getInstance();
	private final List<TabInfo> mTabInfoList = new ArrayList<>();
	public UserInfo mUserInfo;

	public static ActivityMain getInstance()
	{
		return instance;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		instance = this;
		mUserService = new UserService(this);
		mUserService.addDataResponse(this);
		mFileService = new FileService(this);
		mFileService.addDataResponse(this);
		ImagePicker imagePicker = ImagePicker.getInstance();
		imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
		imagePicker.setShowCamera(true);  //显示拍照按钮
		imagePicker.setMultiMode(false);//是否多选
		imagePicker.setCrop(true);        //允许裁剪（单选才有效）
		imagePicker.setSaveRectangle(true); //是否按矩形区域保存
		viewInit();
		boolean isFirst = PreferenceUtils.getInstance().getBooleanValue(PreferenceUtils.IS_FIRST_RUN);
		if (isFirst)
		{
			PreferenceUtils.getInstance().setBooleanValue(PreferenceUtils.IS_FIRST_RUN, false);
		}
		if (PreferenceUtils.getInstance().getBooleanValue(PreferenceUtils.LOGCAT_AUTO_UPDATE))
		{
			new BugService(this).submitBug();
		}
		if (!EventBus.getDefault().isRegistered(this))
		{
			EventBus.getDefault().register(this);
		}
		final UpdateService updateService = new UpdateService(this);
		updateService.addDataResponse(new IAjaxDataResponse()
		{
			@Override
			public boolean onAjaxDataResponse(String url, String result, AjaxStatus status)
			{
				if (url.endsWith(ApiConfig.CHECK_UPDATE))
				{
					UpdateInfo updateInfo = GsonTools.getReturnObject(result, UpdateInfo.class);
					if (updateInfo != null && systemTools.getVersionCode() < updateInfo.getVersionCode())
					{
						updateService.showUpdataDialog(updateInfo);
					}
					return true;
				}
				return false;
			}
		});
		updateService.checkUpdate(null, false);
		mUserService.getMyUserInfo(null, true);
	}

	@Override
	protected void onPause()
	{
		closeDrawerLayout();
		super.onPause();
	}

	@Override
	protected void onDestroy()
	{
		instance = null;
		if (EventBus.getDefault().isRegistered(this))
		{
			EventBus.getDefault().unregister(this);
		}
		super.onDestroy();
	}

	@Override
	public void onBackPressed()
	{
		exit();
	}

	@Override
	public void onDrawerOpened(View drawerView)
	{
		layoutSideMain.refresh();
	}

	@Override
	public void onDrawerClosed(View drawerView)
	{
		//		int position = bannerViewPager.getCurrentItem();
		//		if (position == FIND || position == DIALER || position == USER)
		//		{
		//			vHead.setVisibility(View.VISIBLE);
		//		}
	}

	@Override
	public void onRefresh()
	{
		layoutSideMain.refresh();
		xListView.stopRefresh();
	}

	@Override
	public void onLoadMore()
	{
		xListView.stopLoadMore();
	}

	//关闭侧滑
	private void closeDrawerLayout()
	{
		if (mDrawerLayout != null)
		{
			mDrawerLayout.closeDrawers();
		}
	}

	public void setMyTitle(String title)
	{
		mToolbar.setTitle(title);
	}

	@Override
	protected void viewInit()
	{
		super.viewInit();
		vHead = findViewById(R.id.header);
		setSwipeBackEnable(false); //禁止滑动返回
		mTabInfoList.add(new TabInfo(INDEX, getString(R.string.func_index), R.drawable.ic_tab_index_normal, FragmentIndex.class));
		mTabInfoList.add(new TabInfo(PROFIT, getString(R.string.func_fuli), R.drawable.ic_tab_profit_normal, FragmentFuli.class));
		//		mTabInfoList.add(new TabInfo(JUAN, getString(R.string.func_juan), R.drawable.ic_tab_msg_normal, FragmentServiceShopList.class));
		mTabInfoList.add(new TabInfo(JUAN, getString(R.string.func_juan), R.drawable.ic_tab_msg_normal, FragmentMovieList.class));
		mTabInfoList.add(new TabInfo(ARTICLE, getString(R.string.func_article), R.drawable.ic_tab_article_normal, FragmentThirdArticleList.class));
		mTabInfoList.add(new TabInfo(USER, getString(R.string.func_user), R.drawable.ic_tab_more_normal, FragmentUser.class));
		//加载tab布局
		ViewGroup tab = (ViewGroup) findViewById(R.id.tab);
		tab.addView(LayoutInflater.from(this).inflate(R.layout.layout_tab_icon_and_notification_mark, tab, false));
		mViewPager = (ViewPager) findViewById(R.id.pager);
		final SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
		viewPagerTab.setCustomTabView(new SmartTabLayout.TabProvider()
		{
			@Override
			public View createTabView(ViewGroup container, int position, PagerAdapter adapter)
			{
				TabInfo tabInfo = mTabInfoList.get(position);
				View view = LayoutInflater.from(viewPagerTab.getContext()).inflate(R.layout.item_tab_icon_and_notification_mark, container, false);
				tabInfo.setImgIcon((ImageView) view.findViewById(R.id.tab_icon));
				tabInfo.setTvLabel((TextView) view.findViewById(R.id.tab_label));
				tabInfo.getImgIcon().setImageResource(tabInfo.getImgResId());
				tabInfo.setViewNotificationMark((TextView) view.findViewById(R.id.custom_tab_notification_mark));
				tabInfo.getTvLabel().setText(tabInfo.getLabel());
				return view;
			}
		});
		viewPagerTab.setOnPageChangeListener(new ViewPager.OnPageChangeListener()
		{
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
			{
			}

			@Override
			public void onPageSelected(int position)
			{
				pageIndex = position;
				int size = mTabInfoList.size();
				for (int i = 0; i < size; i++)
				{
					TabInfo tabInfo = mTabInfoList.get(i);
					if (i == position)
					{
						tabInfo.getTvLabel().setTextColor(getResources().getColor(R.color.tab_text_enabled));
						mToolbar.setTitle(tabInfo.getLabel());
					}
					else
					{
						tabInfo.getTvLabel().setTextColor(getResources().getColor(R.color.tab_text_disabled));
					}
				}
				//				setMenuShow();
			}

			@Override
			public void onPageScrollStateChanged(int state)
			{
			}
		});
		mViewPager.setOffscreenPageLimit(mTabInfoList.size());
		FragmentPagerItems pages = new FragmentPagerItems(this);
		for (TabInfo tabInfo : mTabInfoList)
		{
			pages.add(FragmentPagerItem.of(tabInfo.getLabel(), tabInfo.getLoadClass()));
		}
		FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getSupportFragmentManager(), pages);
		mViewPager.setAdapter(adapter);
		viewPagerTab.setViewPager(mViewPager);
		mTabInfoList.get(0).getTvLabel().setTextColor(getResources().getColor(R.color.tab_text_enabled));
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
		ActionBarDrawerToggle mDrawerToggle = new MyActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close, this);
		mDrawerToggle.syncState();
		mDrawerLayout.addDrawerListener(mDrawerToggle);
		pageIndex = 0;
		//侧滑布局
		xListView = (XListView) findViewById(R.id.xlist);
		layoutSideMain = new LayoutSideMain(this);
		xListView.setPullRefreshEnable(true);
		xListView.setPullLoadEnable(false);
		xListView.addHeaderView(layoutSideMain);
		xListView.setAdapter(null);
		xListView.setXListViewListener(this);
	}
	//	private void setMenuShow()
	//	{
	//		//		if (pageIndex == JUAN)
	//		//		{
	//		//			//显示标题
	//		//			vHead.setVisibility(View.VISIBLE);
	//		//		}
	//		//		else
	//		{
	//			//隐藏标题
	//			vHead.setVisibility(View.GONE);
	//		}
	//	}

	public void setCurrentItem(int index)
	{
		mViewPager.setCurrentItem(index);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.menu_activity_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		int id = item.getItemId();
		if (id == R.id.action_settings)
		{
			startActivity(new Intent(this, ActivitySettings.class));
			return true;
		}
		else if (id == R.id.action_about)
		{
			startActivity(new Intent(this, ActivityAbout.class));
			return true;
		}
		else if (id == R.id.action_update)
		{
			new UpdateService(this).checkUpdate("正在检查更新", true);
			return true;
		}
		else if (id == R.id.action_welcome)
		{
			startActivity(new Intent(this, ActivityWelcome.class));
			return true;
		}
		else if (id == R.id.action_share)
		{
			new UserService(this).share("请稍后...", true);
			return true;
		}
		else if (id == R.id.action_exit)
		{
			exit();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	//退出程序
	public void exit()
	{
		finish();
		System.exit(0);
	}

	/**
	 * 当前显示的页面索引
	 *
	 * @return
	 */
	public int getPageIndex()
	{
		return pageIndex;
	}

	@Subscribe
	public void onEventMainThread(final EventTypeLayoutSideMain msg)
	{
		runOnUiThread(new Thread()
		{
			@Override
			public void run()
			{
				super.run();
				if (msg != null)
				{
					if (msg.getType().equalsIgnoreCase(EventTypeLayoutSideMain.CLOSE_DRAWER.getType()))
					{
						closeDrawerLayout();
					}
				}
			}
		});
	}

	@Subscribe
	public void onEventMainThread(final UserEvent userEvent)
	{
		runOnUiThread(new Thread()
		{
			@Override
			public void run()
			{
				super.run();
				if (userEvent != null)
				{
					if (userEvent.getEvent().equalsIgnoreCase(UserEvent.LOGOUT.getEvent()))
					{
						startActivity(new Intent(ActivityMain.this, ActivityLogin.class));
						finish();
					}
					else if (userEvent.getEvent().equalsIgnoreCase(UserEvent.CHANGE_HEADER.getEvent()))
					{
						ImagePicker imagePicker = ImagePicker.getInstance();
						//						imagePicker.setShowCamera(true);  //显示拍照按钮
						//						imagePicker.setCrop(true);        //允许裁剪（单选才有效）
						imagePicker.setSaveRectangle(false); //是否按矩形区域保存
						imagePicker.setStyle(CropImageView.Style.CIRCLE);  //裁剪框的形状
						imagePicker.setFocusWidth(512);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
						imagePicker.setFocusHeight(512);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
						imagePicker.setOutPutX(512);//保存文件的宽度。单位像素
						imagePicker.setOutPutY(512);//保存文件的高度。单位像素
						startActivityForResult(new Intent(ActivityMain.this, ImageGridActivity.class), IMAGE_PICKER);
					}
					else if (userEvent.getEvent().equalsIgnoreCase(UserEvent.CHANGE_MAIN_TITLE.getEvent()))
					{
						setMyTitle(userEvent.getData());
					}
				}
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == ImagePicker.RESULT_CODE_ITEMS)
		{
			if (data != null && requestCode == IMAGE_PICKER)
			{
				ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
				if (images != null && images.size() > 0)
				{
					ImageItem imageItem = images.get(0);
					mFileService.uploadPic(imageItem.path, "header", "正在上传图片...", true);
				}
			}
			else
			{
				Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
			}
		}
	}

	@Override
	public boolean onAjaxDataResponse(String url, String result, AjaxStatus status)
	{
		if (url.endsWith(ApiConfig.GET_MY_USER_INFO))
		{
			ReturnInfo returnInfo = GsonTools.getReturnInfo(result);
			if (ReturnInfo.isSuccess(returnInfo))
			{
				mUserInfo = GsonTools.getReturnObject(returnInfo, UserInfo.class);
				if (mUserInfo != null)
				{
				}
			}
		}
		else if (url.endsWith(ApiConfig.UPLOAD_PIC))
		{
			ReturnInfo returnInfo = GsonTools.getReturnInfo(result);
			if (ReturnInfo.isSuccess(returnInfo))
			{
				PicInfo picInfo = GsonTools.getReturnObject(returnInfo, PicInfo.class);
				if (picInfo != null)
				{
					mUserService.updateUserHeader(picInfo.getPicUrl(), "正在设置头像，请稍后...", true);
				}
			}
			else
			{
				String msg = "上传图片失败！";
				if (returnInfo != null)
				{
					msg = returnInfo.getMsg();
				}
				ToastView.showCenterToast(this, R.drawable.ic_do_fail, msg);
			}
		}
		else if (url.endsWith(ApiConfig.UPDATE_USER_HEADER))
		{
			ReturnInfo returnInfo = GsonTools.getReturnInfo(result);
			if (ReturnInfo.isSuccess(returnInfo))
			{
				ToastView.showCenterToast(this, R.drawable.ic_done, "设置头像成功！");
				EventBus.getDefault().post(UserEvent.CHANGED_HEADER);
			}
			else
			{
				String msg = "设置头像失败！";
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
