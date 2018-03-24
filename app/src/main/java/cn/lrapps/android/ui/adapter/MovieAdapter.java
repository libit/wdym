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
import com.weiduyx.wdym.models.MovieInfo;
import com.weiduyx.wdym.services.ApiConfig;

import java.util.List;

import cn.lrapps.android.ui.ActivityAdWebView;
import cn.lrapps.enums.AdType;
import cn.lrapps.utils.viewtools.DisplayTools;

/**
 * Created by libit on 15/8/29.
 */
public class MovieAdapter extends BaseUserAdapter<MovieInfo>
{
	private final IItemClick iItemClick;

	public MovieAdapter(Context context, List<MovieInfo> movieInfoList, IItemClick iItemClick)
	{
		super(context, movieInfoList);
		this.iItemClick = iItemClick;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		MovieInfoViewHolder viewHolder = null;
		if (convertView != null)
		{
			viewHolder = (MovieInfoViewHolder) convertView.getTag();
		}
		if (viewHolder == null)
		{
			convertView = LayoutInflater.from(context).inflate(R.layout.item_movie, null);
			viewHolder = new MovieInfoViewHolder();
			viewHolder.viewInit(convertView);
			convertView.setTag(viewHolder);
		}
		else
		{
			viewHolder.clear();
		}
		if (list != null && list.size() > 0 && list.size() > position)
		{
			final MovieInfo movieInfo = list.get(position);
			if (movieInfo != null)
			{
				if (movieInfo.getAddDateLong() == 0)
				{
					//设置图片的长宽，这里便于制作图片
					ViewGroup.LayoutParams layoutParams = viewHolder.ivPic.getLayoutParams();
					int width = (DisplayTools.getWindowWidth(context) - DisplayTools.dip2px(context, 40)) / 3;
					layoutParams.width = width;
					layoutParams.height = width * 4 / 3;
					viewHolder.ivPic.setLayoutParams(layoutParams);
					viewHolder.tvName.setText(movieInfo.getName());
					viewHolder.tvAd.setVisibility(View.VISIBLE);
					convertView.setOnClickListener(new View.OnClickListener()
					{
						@Override
						public void onClick(View v)
						{
							ActivityAdWebView.startWebActivity(context, movieInfo.getMovieId(), AdType.NET.getType(), movieInfo.getName(), movieInfo.getUrl());
						}
					});
				}
				else
				{
					//设置图片的长宽，这里便于制作图片
					ViewGroup.LayoutParams layoutParams = viewHolder.ivPic.getLayoutParams();
					int width = (DisplayTools.getWindowWidth(context) - DisplayTools.dip2px(context, 40)) / 3;
					layoutParams.width = width;
					layoutParams.height = width * 4 / 3;
					viewHolder.ivPic.setLayoutParams(layoutParams);
					viewHolder.tvName.setText(movieInfo.getName());
					viewHolder.tvAd.setVisibility(View.GONE);
					if (iItemClick != null)
					{
						convertView.setOnClickListener(new View.OnClickListener()
						{
							@Override
							public void onClick(View v)
							{
								iItemClick.onItemClicked(v, movieInfo);
							}
						});
					}
				}
				Picasso.with(context).load(Uri.parse(ApiConfig.getServerPicUrl(movieInfo.getPicUrl()))).placeholder(R.drawable.img).error(R.drawable.img).into(viewHolder.ivPic);
			}
		}
		return convertView;
	}

	public interface IItemClick
	{
		void onItemClicked(View v, MovieInfo movieInfo);
	}

	public static class MovieInfoViewHolder
	{
		public ImageView ivPic;
		public TextView tvName, tvAd;

		public void viewInit(View convertView)
		{
			ivPic = (ImageView) convertView.findViewById(R.id.iv_pic);
			tvName = (TextView) convertView.findViewById(R.id.tv_name);
			tvAd = (TextView) convertView.findViewById(R.id.tv_ad);
		}

		public void clear()
		{
			tvName.setText("");
		}
	}
}