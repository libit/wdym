/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.android.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.weiduyx.wdym.R;

import java.util.List;

import cn.lrapps.models.FuncInfo;
import cn.lrapps.utils.StringTools;

import static com.squareup.picasso.Picasso.with;

/**
 * Created by libit on 15/8/29.
 */
public class GridFuliFuncAdapter extends BaseUserAdapter<FuncInfo>
{
	public GridFuliFuncAdapter(Context context, List<FuncInfo> funcInfoList)
	{
		super(context, funcInfoList);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		FuncViewHolder viewHolder = null;
		if (convertView != null)
		{
			viewHolder = (FuncViewHolder) convertView.getTag();
		}
		if (viewHolder == null)
		{
			convertView = LayoutInflater.from(context).inflate(R.layout.item_grid_fuli_func, null);
			viewHolder = new FuncViewHolder();
			viewHolder.viewInit(convertView);
			convertView.setTag(viewHolder);
		}
		else
		{
			viewHolder.clear();
		}
		if (list != null && list.size() > 0 && list.size() > position)
		{
			FuncInfo funcInfo = list.get(position);
			if (funcInfo != null)
			{
				viewHolder.tvName.setText(funcInfo.getName());
				if (funcInfo.getResId() != null)
				{
					with(context).load(Uri.EMPTY).placeholder(funcInfo.getResId()).error(funcInfo.getResId()).into(viewHolder.ivHead);
				}
				if (!StringTools.isNull(funcInfo.getPicUrl()))
				{
					with(context).load(Uri.parse(funcInfo.getPicUrl())).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(viewHolder.ivHead);
				}
			}
		}
		return convertView;
	}

	public static class FuncViewHolder
	{
		public ImageView ivHead;
		public TextView tvName;

		public void viewInit(View convertView)
		{
			ivHead = (ImageView) convertView.findViewById(R.id.iv_head);
			tvName = (TextView) convertView.findViewById(R.id.tv_label);
		}

		public void clear()
		{
			ivHead.setImageBitmap(null);
			tvName.setText("");
		}
	}
}