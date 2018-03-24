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
import com.weiduyx.wdym.models.UserWithdrawInfo;
import com.weiduyx.wdym.R;

import java.util.List;

import cn.lrapps.enums.ApplyStatus;

/**
 * Created by libit on 15/8/29.
 */
public class WithdrawAdapter extends BaseUserAdapter<UserWithdrawInfo>
{
	private final IItemClick iItemClick;

	public WithdrawAdapter(Context context, List<UserWithdrawInfo> userWithdrawInfoList, IItemClick iItemClick)
	{
		super(context, userWithdrawInfoList);
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
			convertView = LayoutInflater.from(context).inflate(R.layout.item_withdraw_log, null);
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
			final UserWithdrawInfo userWithdrawInfo = list.get(position);
			if (userWithdrawInfo != null)
			{
				viewHolder.tvWithdrawId.setText("提现交易号：" + userWithdrawInfo.getWithdrawId());
				viewHolder.tvMoney.setText(StringTools.convertToRmb(userWithdrawInfo.getMoney()));
				viewHolder.tvMoneyUnit.setText("元");
				viewHolder.tvStatus.setText("提现状态：" + ApplyStatus.getDesc(userWithdrawInfo.getStatus()));
				viewHolder.tvRemark.setText("备注信息：" + userWithdrawInfo.getRemark());
				if (userWithdrawInfo.getStatus() != ApplyStatus.VERIFY_FAIL.getStatus())
				{
				}
				viewHolder.tvDate.setText("申请时间：" + StringTools.getTime(userWithdrawInfo.getAddDateLong()));
				if (iItemClick != null)
				{
					convertView.setOnClickListener(new View.OnClickListener()
					{
						@Override
						public void onClick(View v)
						{
							iItemClick.onItemClicked(v, userWithdrawInfo);
						}
					});
				}
			}
		}
		return convertView;
	}

	public interface IItemClick
	{
		void onItemClicked(View v, UserWithdrawInfo userWithdrawInfo);
	}

	public static class UserBalanceLogInfoViewHolder
	{
		public TextView tvWithdrawId, tvMoney, tvMoneyUnit, tvStatus, tvRemark, tvDate;

		public void viewInit(View convertView)
		{
			tvWithdrawId = (TextView) convertView.findViewById(R.id.tv_withdraw_id);
			tvMoney = (TextView) convertView.findViewById(R.id.tv_money);
			tvMoneyUnit = (TextView) convertView.findViewById(R.id.tv_money_unit);
			tvStatus = (TextView) convertView.findViewById(R.id.tv_status);
			tvRemark = (TextView) convertView.findViewById(R.id.tv_remark);
			tvDate = (TextView) convertView.findViewById(R.id.tv_date);
		}

		public void clear()
		{
			tvWithdrawId.setText("");
			tvMoney.setText("");
			tvMoneyUnit.setText("");
			tvStatus.setText("");
			tvRemark.setText("");
			tvDate.setText("");
		}
	}
}