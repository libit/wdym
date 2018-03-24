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

import com.androidquery.callback.AjaxStatus;
import cn.lrapps.enums.AdType;
import cn.lrapps.enums.StatusType;
import cn.lrapps.utils.GsonTools;
import cn.lrapps.utils.StringTools;
import com.squareup.picasso.Picasso;
import com.weiduyx.wdym.models.AdInfo;
import com.weiduyx.wdym.models.UserRedpackLogInfo;
import com.weiduyx.wdym.services.AdService;
import com.weiduyx.wdym.services.ApiConfig;
import com.weiduyx.wdym.services.IAjaxDataResponse;
import com.weiduyx.wdym.R;

import java.util.List;

/**
 * Created by libit on 15/8/29.
 */
public class RedpackLogAdapter extends BaseUserAdapter<UserRedpackLogInfo>
{
	private final IItemClick iItemClick;

	public RedpackLogAdapter(Context context, List<UserRedpackLogInfo> userRedpackLogInfoList, IItemClick iItemClick)
	{
		super(context, userRedpackLogInfoList);
		this.iItemClick = iItemClick;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		UserRedpackLogInfoViewHolder viewHolder = null;
		if (convertView != null)
		{
			viewHolder = (UserRedpackLogInfoViewHolder) convertView.getTag();
		}
		if (viewHolder == null)
		{
			convertView = LayoutInflater.from(context).inflate(R.layout.item_redpack_log, null);
			viewHolder = new UserRedpackLogInfoViewHolder();
			viewHolder.viewInit(convertView);
			convertView.setTag(viewHolder);
		}
		else
		{
			viewHolder.clear();
		}
		if (list != null && list.size() > 0 && list.size() > position)
		{
			final UserRedpackLogInfo userRedpackLogInfo = list.get(position);
			if (userRedpackLogInfo != null)
			{
				viewHolder.tvContent.setText(userRedpackLogInfo.getRemark());
				viewHolder.tvMoney.setText(StringTools.convertToRmb(userRedpackLogInfo.getMoney()) + "元");
				viewHolder.tvStatus.setText(StatusType.ENABLE.getStatus() == userRedpackLogInfo.getStatus() ? "已领取" : "未领取");
				viewHolder.tvDate.setText("领取时间：" + StringTools.getTime(userRedpackLogInfo.getAddDateLong()));
				AdService adService = new AdService(context);
				final UserRedpackLogInfoViewHolder userRedpackLogInfoViewHolder = viewHolder;
				adService.addDataResponse(new IAjaxDataResponse()
				{
					@Override
					public boolean onAjaxDataResponse(String url, String result, AjaxStatus status)
					{
						AdInfo adInfo = GsonTools.getReturnObject(result, AdInfo.class);
						if (adInfo != null)
						{
							userRedpackLogInfoViewHolder.tvRedpackType.setText(AdType.getDesc(adInfo.getTypeId()));
							userRedpackLogInfoViewHolder.tvTitle.setText(adInfo.getTitle());
							userRedpackLogInfoViewHolder.tvContent.setText(adInfo.getContent());
							Picasso.with(context).load(Uri.parse(ApiConfig.getServerPicUrl(adInfo.getPicUrl()))).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(userRedpackLogInfoViewHolder.ivPic);
						}
						return false;
					}
				});
				adService.getAdInfo(userRedpackLogInfo.getSendId(), null, true);
				if (iItemClick != null)
				{
					convertView.setOnClickListener(new View.OnClickListener()
					{
						@Override
						public void onClick(View v)
						{
							iItemClick.onItemClicked(v, userRedpackLogInfo);
						}
					});
				}
			}
		}
		return convertView;
	}

	public interface IItemClick
	{
		void onItemClicked(View v, UserRedpackLogInfo userRedpackLogInfo);
	}

	public static class UserRedpackLogInfoViewHolder
	{
		public ImageView ivPic;
		public TextView tvRedpackType, tvTitle, tvContent, tvMoney, tvStatus, tvDate;

		public void viewInit(View convertView)
		{
			ivPic = (ImageView) convertView.findViewById(R.id.iv_pic);
			tvRedpackType = (TextView) convertView.findViewById(R.id.tv_redpack_type);
			tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
			tvContent = (TextView) convertView.findViewById(R.id.tv_content);
			tvMoney = (TextView) convertView.findViewById(R.id.tv_money);
			tvStatus = (TextView) convertView.findViewById(R.id.tv_status);
			tvDate = (TextView) convertView.findViewById(R.id.tv_date);
		}

		public void clear()
		{
			ivPic.setImageBitmap(null);
			tvRedpackType.setText("");
			tvTitle.setText("");
			tvContent.setText("");
			tvMoney.setText("");
			tvStatus.setText("");
			tvDate.setText("");
		}
	}
}