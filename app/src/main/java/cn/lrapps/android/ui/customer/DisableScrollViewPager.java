package cn.lrapps.android.ui.customer;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by libit on 18/3/28.
 */
public class DisableScrollViewPager extends ViewPager
{
	private boolean isCanScroll = false;

	public DisableScrollViewPager(Context context)
	{
		super(context);
	}

	public DisableScrollViewPager(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	/**
	 * 设置其是否能滑动换页
	 *
	 * @param isCanScroll false 不能换页， true 可以滑动换页
	 */
	public void setScanScroll(boolean isCanScroll)
	{
		this.isCanScroll = isCanScroll;
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev)
	{
		return isCanScroll && super.onInterceptTouchEvent(ev);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev)
	{
		return isCanScroll && super.onTouchEvent(ev);
	}
}
