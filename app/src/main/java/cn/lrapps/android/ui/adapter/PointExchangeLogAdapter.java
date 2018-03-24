/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.android.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.lrapps.utils.StringTools;
import com.weiduyx.wdym.models.UserPointExchangeInfo;
import com.weiduyx.wdym.R;

import java.util.List;

/**
 * Created by libit on 15/8/29.
 */
public class PointExchangeLogAdapter extends BaseUserAdapter<UserPointExchangeInfo>
{
	private final IItemClick iItemClick;

	public PointExchangeLogAdapter(Context context, List<UserPointExchangeInfo> userPointExchangeInfoList, IItemClick iItemClick)
	{
		super(context, userPointExchangeInfoList);
		this.iItemClick = iItemClick;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		UserPointExchangeInfoViewHolder viewHolder = null;
		if (convertView != null)
		{
			viewHolder = (UserPointExchangeInfoViewHolder) convertView.getTag();
		}
		if (viewHolder == null)
		{
			convertView = LayoutInflater.from(context).inflate(R.layout.item_point_exchange_log, null);
			viewHolder = new UserPointExchangeInfoViewHolder();
			viewHolder.viewInit(convertView);
			convertView.setTag(viewHolder);
		}
		else
		{
			viewHolder.clear();
		}
		if (list != null && list.size() > 0 && list.size() > position)
		{
			final UserPointExchangeInfo userPointExchangeInfo = list.get(position);
			if (userPointExchangeInfo != null)
			{
				viewHolder.tvPoint.setText("兑换金币：" + userPointExchangeInfo.getPoint() + "个");
				viewHolder.tvMoney.setText(StringTools.convertToRmb(userPointExchangeInfo.getBalance()));
				viewHolder.tvMoneyUnit.setText("元");
				viewHolder.tvDate.setText("兑换时间：" + StringTools.getTime(userPointExchangeInfo.getAddDateLong()));
				if (iItemClick != null)
				{
					convertView.setOnClickListener(new View.OnClickListener()
					{
						@Override
						public void onClick(View v)
						{
							iItemClick.onItemClicked(v, userPointExchangeInfo);
						}
					});
				}
			}
		}
		return convertView;
	}

	public interface IItemClick
	{
		void onItemClicked(View v, UserPointExchangeInfo userPointExchangeInfo);
	}

	public static class UserPointExchangeInfoViewHolder
	{
		public TextView tvPoint, tvMoney, tvMoneyUnit, tvDate;

		public void viewInit(View convertView)
		{
			tvPoint = (TextView) convertView.findViewById(R.id.tv_point);
			tvMoney = (TextView) convertView.findViewById(R.id.tv_money);
			tvMoneyUnit = (TextView) convertView.findViewById(R.id.tv_money_unit);
			tvDate = (TextView) convertView.findViewById(R.id.tv_date);
		}

		public void clear()
		{
			tvPoint.setText("");
			tvMoney.setText("");
			tvMoneyUnit.setText("");
			tvDate.setText("");
		}
	}
}