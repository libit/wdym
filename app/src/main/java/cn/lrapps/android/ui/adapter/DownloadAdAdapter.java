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
import com.weiduyx.wdym.models.AdInfo;
import com.weiduyx.wdym.services.ApiConfig;

import java.util.List;

import cn.lrapps.utils.StringTools;

/**
 * Created by libit on 15/8/29.
 */
public class DownloadAdAdapter extends BaseUserAdapter<AdInfo>
{
	private final IItemClick iItemClick;

	public DownloadAdAdapter(Context context, List<AdInfo> adInfoList, IItemClick iItemClick)
	{
		super(context, adInfoList);
		this.iItemClick = iItemClick;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		AdInfoViewHolder viewHolder = null;
		if (convertView != null)
		{
			viewHolder = (AdInfoViewHolder) convertView.getTag();
		}
		if (viewHolder == null)
		{
			convertView = LayoutInflater.from(context).inflate(R.layout.item_download_ad, null);
			viewHolder = new AdInfoViewHolder();
			viewHolder.viewInit(convertView);
			convertView.setTag(viewHolder);
		}
		else
		{
			viewHolder.clear();
		}
		if (list != null && list.size() > 0 && list.size() > position)
		{
			final AdInfo adInfo = list.get(position);
			if (adInfo != null)
			{
				viewHolder.tvContent.setText(adInfo.getContent());
				viewHolder.tvSendCount.setText("已累计发放" + adInfo.getDisplayedAdCount() + "份");
				if (adInfo.getAdMaxUnitPrice() <= adInfo.getAdMinUnitPrice())
				{
					viewHolder.tvPrice.setText(StringTools.convertToRmb(adInfo.getAdMinUnitPrice() * 3 / 10) + "元");
				}
				else
				{
					viewHolder.tvPrice.setText(StringTools.convertToRmb(adInfo.getAdMinUnitPrice() * 3 / 10) + "元 ~ " + StringTools.convertToRmb(adInfo.getAdMaxUnitPrice() * 3 / 10) + "元");
				}
				if (iItemClick != null)
				{
					convertView.setOnClickListener(new View.OnClickListener()
					{
						@Override
						public void onClick(View v)
						{
							iItemClick.onItemClicked(v, adInfo);
						}
					});
				}
				Picasso.with(context).load(Uri.parse(ApiConfig.getServerPicUrl(adInfo.getPicUrl()))).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(viewHolder.ivPic);
			}
		}
		return convertView;
	}

	public interface IItemClick
	{
		void onItemClicked(View v, AdInfo adInfo);
	}

	public static class AdInfoViewHolder
	{
		public ImageView ivPic;
		public TextView tvContent, tvSendCount, tvPrice;

		public void viewInit(View convertView)
		{
			ivPic = (ImageView) convertView.findViewById(R.id.iv_pic);
			tvContent = (TextView) convertView.findViewById(R.id.tv_content);
			tvSendCount = (TextView) convertView.findViewById(R.id.tv_send_count);
			tvPrice = (TextView) convertView.findViewById(R.id.tv_price);
		}

		public void clear()
		{
			tvContent.setText("");
			tvSendCount.setText("");
			tvPrice.setText("");
		}
	}
}