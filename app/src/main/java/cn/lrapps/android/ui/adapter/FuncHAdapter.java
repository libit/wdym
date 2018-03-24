/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.android.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import cn.lrapps.models.FuncInfo;
import com.weiduyx.wdym.R;

import java.util.List;

/**
 * Created by libit on 15/8/29.
 */
public class FuncHAdapter extends BaseUserAdapter<FuncInfo>
{
	protected IFuncsAdapterItemClicked funcsAdapterItemClicked;

	public FuncHAdapter(Context context, List<FuncInfo> funcInfoList, IFuncsAdapterItemClicked funcsAdapterItemClicked)
	{
		super(context, funcInfoList);
		this.funcsAdapterItemClicked = funcsAdapterItemClicked;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		FuncInfoViewHolder viewHolder = null;
		if (convertView != null)
		{
			viewHolder = (FuncInfoViewHolder) convertView.getTag();
		}
		if (viewHolder == null)
		{
			convertView = LayoutInflater.from(context).inflate(R.layout.item_func, null);
			viewHolder = new FuncInfoViewHolder();
			viewHolder.viewInit(convertView);
			convertView.setTag(viewHolder);
		}
		else
		{
			viewHolder.clear();
		}
		final FuncInfo funcInfo = list.get(position);
		if (funcInfo != null)
		{
			viewHolder.tvName.setText(funcInfo.getName());
			if (funcInfo.getResId() != null)
			{
				viewHolder.ivHead.setImageResource(funcInfo.getResId());
			}
			else
			{
				viewHolder.ivHead.setVisibility(View.GONE);
			}
		}
		if (funcsAdapterItemClicked != null)
		{
			convertView.setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					funcsAdapterItemClicked.onItemClicked(funcInfo);
				}
			});
		}
		return convertView;
	}

	public interface IFuncsAdapterItemClicked
	{
		void onItemClicked(FuncInfo funcInfo);
	}

	public static class FuncInfoViewHolder
	{
		public ImageView ivHead;
		public TextView tvName;

		public void viewInit(View convertView)
		{
			ivHead = (ImageView) convertView.findViewById(R.id.iv_icon);
			tvName = (TextView) convertView.findViewById(R.id.tv_name);
		}

		public void clear()
		{
			ivHead.setImageBitmap(null);
			tvName.setText("");
		}
	}
}