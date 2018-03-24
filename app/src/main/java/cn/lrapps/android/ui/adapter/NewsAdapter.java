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

import com.squareup.picasso.Picasso;
import com.weiduyx.wdym.R;
import com.weiduyx.wdym.models.NewsInfo;

import java.util.List;

import cn.lrapps.utils.StringTools;

/**
 * Created by libit on 15/8/29.
 */
public class NewsAdapter extends BaseUserAdapter<NewsInfo>
{
	private final IItemClick iItemClick;

	public NewsAdapter(Context context, List<NewsInfo> newsInfoList, IItemClick iItemClick)
	{
		super(context, newsInfoList);
		this.iItemClick = iItemClick;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		NewsInfoViewHolder viewHolder = null;
		if (convertView != null)
		{
			viewHolder = (NewsInfoViewHolder) convertView.getTag();
		}
		if (viewHolder == null)
		{
			convertView = LayoutInflater.from(context).inflate(R.layout.item_news, null);
			viewHolder = new NewsInfoViewHolder();
			viewHolder.viewInit(convertView);
			convertView.setTag(viewHolder);
		}
		else
		{
			viewHolder.clear();
		}
		if (list != null && list.size() > 0 && list.size() > position)
		{
			final NewsInfo newsInfo = list.get(position);
			if (newsInfo != null)
			{
				viewHolder.tvTitle.setText(newsInfo.getTitle());
				viewHolder.tvShortContent.setText(newsInfo.getDescription());
				viewHolder.tvDate.setText(StringTools.getTime(newsInfo.getAddDateLong()));
				Picasso.with(context).load(Uri.EMPTY).placeholder(R.drawable.bg_user).error(R.drawable.bg_user).into(viewHolder.ivPic);
				if (iItemClick != null)
				{
					convertView.setOnClickListener(new View.OnClickListener()
					{
						@Override
						public void onClick(View v)
						{
							iItemClick.onItemClicked(v, newsInfo);
						}
					});
				}
			}
		}
		return convertView;
	}

	public interface IItemClick
	{
		void onItemClicked(View v, NewsInfo newsInfo);
	}

	public static class NewsInfoViewHolder
	{
		public ImageView ivPic;
		public TextView tvTitle, tvShortContent, tvDate;

		public void viewInit(View convertView)
		{
			tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
			ivPic = (ImageView) convertView.findViewById(R.id.iv_pic);
			tvShortContent = (TextView) convertView.findViewById(R.id.tv_short_content);
			tvDate = (TextView) convertView.findViewById(R.id.tv_date);
		}

		public void clear()
		{
			ivPic.setImageBitmap(null);
			tvTitle.setText("");
			tvShortContent.setText("");
			tvDate.setText("");
		}
	}
}