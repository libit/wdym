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

import cn.lrapps.enums.MoneyUnit;
import cn.lrapps.utils.StringTools;
import com.weiduyx.wdym.models.UserBalanceLogInfo;
import com.weiduyx.wdym.R;

import java.util.List;

/**
 * Created by libit on 15/8/29.
 */
public class BalanceLogAdapter extends BaseUserAdapter<UserBalanceLogInfo>
{
	private final IItemClick iItemClick;

	public BalanceLogAdapter(Context context, List<UserBalanceLogInfo> userBalanceLogInfoList, IItemClick iItemClick)
	{
		super(context, userBalanceLogInfoList);
		this.iItemClick = iItemClick;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		UserBalanceLogInfoViewHolder viewHolder = null;
		if (convertView != null)
		{
			viewHolder = (UserBalanceLogInfoViewHolder) convertView.getTag();
		}
		if (viewHolder == null)
		{
			convertView = LayoutInflater.from(context).inflate(R.layout.item_balance_log, null);
			viewHolder = new UserBalanceLogInfoViewHolder();
			viewHolder.viewInit(convertView);
			convertView.setTag(viewHolder);
		}
		else
		{
			viewHolder.clear();
		}
		if (list != null && list.size() > 0 && list.size() > position)
		{
			final UserBalanceLogInfo userBalanceLogInfo = list.get(position);
			if (userBalanceLogInfo != null)
			{
				viewHolder.tvTitle.setText(userBalanceLogInfo.getRemark());
				if (userBalanceLogInfo.getMoneyUnit() == MoneyUnit.RMB.getType())
				{
					viewHolder.tvMoney.setText(StringTools.convertToRmb(userBalanceLogInfo.getMoney()));
					viewHolder.tvMoneyUnit.setText("元");
				}
				else
				{
					viewHolder.tvMoney.setText(userBalanceLogInfo.getMoney() + "");
					viewHolder.tvMoneyUnit.setText("金币");
				}
				viewHolder.tvDate.setText(StringTools.getTime(userBalanceLogInfo.getAddDateLong()));
				if (iItemClick != null)
				{
					convertView.setOnClickListener(new View.OnClickListener()
					{
						@Override
						public void onClick(View v)
						{
							iItemClick.onItemClicked(v, userBalanceLogInfo);
						}
					});
				}
			}
		}
		return convertView;
	}

	public interface IItemClick
	{
		void onItemClicked(View v, UserBalanceLogInfo userBalanceLogInfo);
	}

	public static class UserBalanceLogInfoViewHolder
	{
		public TextView tvTitle, tvMoney, tvMoneyUnit, tvDate;

		public void viewInit(View convertView)
		{
			tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
			tvMoney = (TextView) convertView.findViewById(R.id.tv_money);
			tvMoneyUnit = (TextView) convertView.findViewById(R.id.tv_money_unit);
			tvDate = (TextView) convertView.findViewById(R.id.tv_date);
		}

		public void clear()
		{
			tvTitle.setText("");
			tvMoney.setText("");
			tvMoneyUnit.setText("");
			tvDate.setText("");
		}
	}
}