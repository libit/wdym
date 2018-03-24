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

import cn.lrapps.utils.StringTools;
import com.squareup.picasso.Picasso;
import com.weiduyx.wdym.models.UserInfo;
import com.weiduyx.wdym.services.ApiConfig;
import com.weiduyx.wdym.R;

import java.util.List;

/**
 * Created by libit on 15/8/29.
 */
public class FansAdapter extends BaseUserAdapter<UserInfo>
{
	private final IItemClick iItemClick;

	public FansAdapter(Context context, List<UserInfo> userInfoList, IItemClick iItemClick)
	{
		super(context, userInfoList);
		this.iItemClick = iItemClick;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		UserInfoViewHolder viewHolder = null;
		if (convertView != null)
		{
			viewHolder = (UserInfoViewHolder) convertView.getTag();
		}
		if (viewHolder == null)
		{
			convertView = LayoutInflater.from(context).inflate(R.layout.item_fans, null);
			viewHolder = new UserInfoViewHolder();
			viewHolder.viewInit(convertView);
			convertView.setTag(viewHolder);
		}
		else
		{
			viewHolder.clear();
		}
		if (list != null && list.size() > 0 && list.size() > position)
		{
			final UserInfo userInfo = list.get(position);
			if (userInfo != null)
			{
				viewHolder.tvUserId.setText("粉丝账号：" + userInfo.getUserId());
				viewHolder.tvNickname.setText("微信昵称：" + userInfo.getNickname());
				viewHolder.tvDate.setText("扫码时间：" + StringTools.getTime(userInfo.getReferrerDateLong()));
				Picasso.with(context).load(Uri.parse(ApiConfig.getServerPicUrl(userInfo.getPicId()))).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(viewHolder.ivHead);
				if (iItemClick != null)
				{
					convertView.setOnClickListener(new View.OnClickListener()
					{
						@Override
						public void onClick(View v)
						{
							iItemClick.onItemClicked(v, userInfo);
						}
					});
				}
			}
		}
		return convertView;
	}

	public interface IItemClick
	{
		void onItemClicked(View v, UserInfo userInfo);
	}

	public static class UserInfoViewHolder
	{
		public ImageView ivHead;
		public TextView tvUserId, tvNickname, tvDate;

		public void viewInit(View convertView)
		{
			ivHead = (ImageView) convertView.findViewById(R.id.iv_head);
			tvUserId = (TextView) convertView.findViewById(R.id.tv_user_id);
			tvNickname = (TextView) convertView.findViewById(R.id.tv_nickname);
			tvDate = (TextView) convertView.findViewById(R.id.tv_date);
		}

		public void clear()
		{
			tvUserId.setText("");
			tvNickname.setText("");
			tvDate.setText("");
		}
	}
}