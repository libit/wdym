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
import com.weiduyx.wdym.models.AdviceInfo;
import com.weiduyx.wdym.R;

import java.util.List;

/**
 * Created by libit on 15/8/29.
 */
public class AdviceAdapter extends BaseUserAdapter<AdviceInfo>
{
	private final IItemClick iItemClick;

	public AdviceAdapter(Context context, List<AdviceInfo> adviceInfoList, IItemClick iItemClick)
	{
		super(context, adviceInfoList);
		this.iItemClick = iItemClick;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		AdviceInfoViewHolder viewHolder = null;
		if (convertView != null)
		{
			viewHolder = (AdviceInfoViewHolder) convertView.getTag();
		}
		if (viewHolder == null)
		{
			convertView = LayoutInflater.from(context).inflate(R.layout.item_advice, null);
			viewHolder = new AdviceInfoViewHolder();
			viewHolder.viewInit(convertView);
			convertView.setTag(viewHolder);
		}
		else
		{
			viewHolder.clear();
		}
		if (list != null && list.size() > 0 && list.size() > position)
		{
			final AdviceInfo adviceInfo = list.get(position);
			if (adviceInfo != null)
			{
				//				viewHolder.tvType.setText(adviceInfo.getAdviceType());
				viewHolder.tvContent.setText(adviceInfo.getContent());
				viewHolder.tvDate.setText(StringTools.getTime(adviceInfo.getAddDateLong()));
				if (iItemClick != null)
				{
					convertView.setOnClickListener(new View.OnClickListener()
					{
						@Override
						public void onClick(View v)
						{
							iItemClick.onItemClicked(v, adviceInfo);
						}
					});
				}
			}
		}
		return convertView;
	}

	public interface IItemClick
	{
		void onItemClicked(View v, AdviceInfo adviceInfo);
	}

	public static class AdviceInfoViewHolder
	{
		public TextView tvContent;
		public TextView tvType;
		public TextView tvDate;

		public void viewInit(View convertView)
		{
			tvContent = (TextView) convertView.findViewById(R.id.tv_content);
			tvType = (TextView) convertView.findViewById(R.id.tv_advcie_type);
			tvDate = (TextView) convertView.findViewById(R.id.tv_date);
		}

		public void clear()
		{
			tvContent.setText("");
			tvType.setText("");
			tvDate.setText("");
		}
	}
}