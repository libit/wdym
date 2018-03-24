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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.weiduyx.wdym.R;
import com.weiduyx.wdym.models.BookInfo;
import com.weiduyx.wdym.services.ApiConfig;

import java.util.List;

import cn.lrapps.android.ui.ActivityWebView;
import cn.lrapps.utils.StringTools;

/**
 * Created by libit on 15/8/29.
 */
public class BookAdapter extends BaseUserAdapter<BookInfo>
{
	private final IItemClick iItemClick;

	public BookAdapter(Context context, List<BookInfo> bookInfoList, IItemClick iItemClick)
	{
		super(context, bookInfoList);
		this.iItemClick = iItemClick;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		BookInfoViewHolder viewHolder = null;
		if (convertView != null)
		{
			viewHolder = (BookInfoViewHolder) convertView.getTag();
		}
		if (viewHolder == null)
		{
			convertView = LayoutInflater.from(context).inflate(R.layout.item_book, null);
			viewHolder = new BookInfoViewHolder();
			viewHolder.viewInit(convertView);
			convertView.setTag(viewHolder);
		}
		else
		{
			viewHolder.clear();
		}
		if (list != null && list.size() > 0 && list.size() > position)
		{
			final BookInfo bookInfo = list.get(position);
			if (bookInfo != null)
			{
				viewHolder.tvName.setText(bookInfo.getName());
				viewHolder.tvAuthor.setText("作者：" + bookInfo.getAuthor());
				viewHolder.tvReadCount.setText("阅读人数：" + bookInfo.getReadCount() + "人");
				viewHolder.tvShortContent.setText("小说介绍：" + bookInfo.getShortContent());
				Picasso.with(context).load(Uri.parse(ApiConfig.getServerPicUrl(bookInfo.getPicUrl()))).placeholder(R.drawable.img).error(R.drawable.img).into(viewHolder.ivPic);
//				if (iItemClick != null)
//				{
//					convertView.setOnClickListener(new View.OnClickListener()
//					{
//						@Override
//						public void onClick(View v)
//						{
//							iItemClick.onItemClicked(v, bookInfo);
//						}
//					});
//				}
				if (!StringTools.isNull(bookInfo.getUrl()))
				{
					viewHolder.ivRead.setOnClickListener(new View.OnClickListener()
					{
						@Override
						public void onClick(View v)
						{
							ActivityWebView.startWebActivity(context, bookInfo.getName(), bookInfo.getUrl());
						}
					});
				}
			}
		}
		return convertView;
	}

	public interface IItemClick
	{
		void onItemClicked(View v, BookInfo bookInfo);
	}

	public static class BookInfoViewHolder
	{
		public ImageView ivPic;
		public TextView tvName, tvAuthor, tvReadCount, tvShortContent;
		public ImageButton ivRead;

		public void viewInit(View convertView)
		{
			ivPic = (ImageView) convertView.findViewById(R.id.iv_pic);
			tvName = (TextView) convertView.findViewById(R.id.tv_name);
			tvAuthor = (TextView) convertView.findViewById(R.id.tv_author);
			tvReadCount = (TextView) convertView.findViewById(R.id.tv_read_count);
			tvShortContent = (TextView) convertView.findViewById(R.id.tv_short_content);
			ivRead = (ImageButton) convertView.findViewById(R.id.iv_read);
		}

		public void clear()
		{
			tvName.setText("");
			tvAuthor.setText("");
			tvReadCount.setText("");
			tvShortContent.setText("");
		}
	}
}