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
import com.weiduyx.wdym.models.ThirdArticleInfo;
import com.weiduyx.wdym.services.ApiConfig;

import java.util.List;

import cn.lrapps.android.ui.ActivityAdWebView;
import cn.lrapps.enums.AdType;
import cn.lrapps.utils.TimeTools;
import cn.lrapps.utils.viewtools.DisplayTools;

/**
 * Created by libit on 15/8/29.
 */
public class ThirdArticleAdapter extends BaseUserAdapter<ThirdArticleInfo>
{
	private final IItemClick iItemClick;

	public ThirdArticleAdapter(Context context, List<ThirdArticleInfo> thirdArticleInfoList, IItemClick iItemClick)
	{
		super(context, thirdArticleInfoList);
		this.iItemClick = iItemClick;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		ThirdArticleInfoViewHolder viewHolder = null;
		if (convertView != null)
		{
			viewHolder = (ThirdArticleInfoViewHolder) convertView.getTag();
		}
		if (viewHolder == null)
		{
			convertView = LayoutInflater.from(context).inflate(R.layout.item_third_article, null);
			viewHolder = new ThirdArticleInfoViewHolder();
			viewHolder.viewInit(convertView);
			convertView.setTag(viewHolder);
		}
		else
		{
			viewHolder.clear();
		}
		if (list != null && list.size() > 0 && list.size() > position)
		{
			final ThirdArticleInfo thirdArticleInfo = list.get(position);
			if (thirdArticleInfo != null)
			{
				if (thirdArticleInfo.getAddDateLong() == 0)
				{
					//设置图片的长宽，这里便于制作图片
					ViewGroup.LayoutParams layoutParams = viewHolder.ivPic.getLayoutParams();
					int width = (DisplayTools.getWindowWidth(context) - DisplayTools.dip2px(context, 20));
					layoutParams.width = width;
					layoutParams.height = width / 2;
					viewHolder.ivPic.setLayoutParams(layoutParams);
					viewHolder.tvAd.setVisibility(View.VISIBLE);
					viewHolder.vInfo.setVisibility(View.GONE);
					viewHolder.tvTitle.setVisibility(View.GONE);
					convertView.setOnClickListener(new View.OnClickListener()
					{
						@Override
						public void onClick(View v)
						{
							ActivityAdWebView.startWebActivity(context, thirdArticleInfo.getFlag(), AdType.NET.getType(), thirdArticleInfo.getTitle(), thirdArticleInfo.getSource());
						}
					});
				}
				else
				{
					//设置图片的长宽，这里便于制作图片
					ViewGroup.LayoutParams layoutParams = viewHolder.ivPic.getLayoutParams();
					int width = (DisplayTools.getWindowWidth(context) - DisplayTools.dip2px(context, 40)) / 3;
					layoutParams.width = width;
					layoutParams.height = width;
					viewHolder.ivPic.setLayoutParams(layoutParams);
					viewHolder.tvTitle.setText(thirdArticleInfo.getTitle());
					viewHolder.tvName.setText(thirdArticleInfo.getWriter());
					viewHolder.tvCommentCount.setText("阅读 " + thirdArticleInfo.getClick());
					viewHolder.tvDate.setText(TimeTools.getDateTimeString(thirdArticleInfo.getAddDateLong()));
					viewHolder.vInfo.setVisibility(View.VISIBLE);
					viewHolder.tvTitle.setVisibility(View.VISIBLE);
					viewHolder.tvAd.setVisibility(View.GONE);
					if (iItemClick != null)
					{
						convertView.setOnClickListener(new View.OnClickListener()
						{
							@Override
							public void onClick(View v)
							{
								iItemClick.onItemClicked(v, thirdArticleInfo);
							}
						});
					}
				}
				Picasso.with(context).load(Uri.parse(ApiConfig.getServerPicUrl(thirdArticleInfo.getLitpic()))).placeholder(R.drawable.img).error(R.drawable.img).into(viewHolder.ivPic);
			}
		}
		return convertView;
	}

	public interface IItemClick
	{
		void onItemClicked(View v, ThirdArticleInfo thirdArticleInfo);
	}

	public static class ThirdArticleInfoViewHolder
	{
		public View vInfo;
		public ImageView ivPic;
		public TextView tvTitle, tvName, tvCommentCount, tvDate, tvAd;

		public void viewInit(View convertView)
		{
			vInfo = convertView.findViewById(R.id.v_info);
			ivPic = (ImageView) convertView.findViewById(R.id.iv_pic);
			tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
			tvName = (TextView) convertView.findViewById(R.id.tv_name);
			tvCommentCount = (TextView) convertView.findViewById(R.id.tv_comment_count);
			tvDate = (TextView) convertView.findViewById(R.id.tv_date);
			tvAd = (TextView) convertView.findViewById(R.id.tv_ad);
		}

		public void clear()
		{
			tvTitle.setText("");
			tvName.setText("");
			tvCommentCount.setText("");
			tvDate.setText("");
		}
	}
}