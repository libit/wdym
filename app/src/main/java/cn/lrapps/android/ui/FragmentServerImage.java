/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.android.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import cn.lrapps.utils.StringTools;
import com.squareup.picasso.Picasso;
import com.weiduyx.wdym.R;

public class FragmentServerImage extends Fragment
{
	private static final String ARG_IMAGE_URL = "ARG_IMAGE_URL";
	private static final String ARG_CLICK_URL = "ARG_CLICK_URL";
	private static final String ARG_SCALE_TYPE = "ARG_SCALE_TYPE";
	private String mImageUrl;
	private String mClickUrl;
	private String mScaleTypeName;
	private View rootView;
	private ImageView imageView;

	public FragmentServerImage()
	{
		// Required empty public constructor
	}

	public static FragmentServerImage newInstance(String imageUrl, String clickUrl, String scaleTypeName)
	{
		FragmentServerImage fragment = new FragmentServerImage();
		Bundle args = new Bundle();
		args.putString(ARG_IMAGE_URL, imageUrl);
		args.putString(ARG_CLICK_URL, clickUrl);
		args.putString(ARG_SCALE_TYPE, scaleTypeName);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		if (getArguments() != null)
		{
			mImageUrl = getArguments().getString(ARG_IMAGE_URL);
			mClickUrl = getArguments().getString(ARG_CLICK_URL);
			mScaleTypeName = getArguments().getString(ARG_SCALE_TYPE);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		rootView = inflater.inflate(R.layout.fragment_server_image, container, false);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		initView();
		Picasso.with(this.getContext()).load(Uri.parse(mImageUrl)).into(imageView);
		if (!StringTools.isNull(mClickUrl))
		{
			imageView.setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					if (mClickUrl.startsWith("http"))
					{
						ActivityWebView.startWebActivity(FragmentServerImage.this.getContext(), "", mClickUrl);
					}
					else
					{
						//						ClientFuncTypeInfo clientClientFuncTypeInfo = GsonTools.getObject(mClickUrl, ClientFuncTypeInfo.class);
						//						if (clientClientFuncTypeInfo != null)
						//						{
						//							if (clientClientFuncTypeInfo.getType().equalsIgnoreCase(ClientFuncType.OPEN_URL.getType()))
						//							{
						//								ActivityWebView.startWebActivity(FragmentServerImage.this.getContext(), "", clientClientFuncTypeInfo.getContent());
						//							}
						//							else if (clientClientFuncTypeInfo.getType().equalsIgnoreCase(ClientFuncType.OPEN_ACTIIVTY.getType()))
						//							{
						//								if (clientClientFuncTypeInfo.getContent().equalsIgnoreCase(ClientFuncActivityType.SHOP_PRODUCTS.getType()))
						//								{
						//									startActivity(new Intent(FragmentServerImage.this.getContext(), ActivityShopProducts.class));
						//								}
						//								else if (clientClientFuncTypeInfo.getContent().equalsIgnoreCase(ClientFuncActivityType.RECHARGE_DATA_TRAFFIC.getType()))
						//								{
						//									if (UserService.isLogin())
						//									{
						//										startActivity(new Intent(FragmentServerImage.this.getContext(), ActivityRechargeDataTraffic.class));
						//									}
						//									else
						//									{
						//										startActivity(new Intent(FragmentServerImage.this.getContext(), ActivityLogin.class));
						//									}
						//								}
						//								else if (clientClientFuncTypeInfo.getContent().equalsIgnoreCase(ClientFuncActivityType.POINT_PRODUCT_SHOP.getType()))
						//								{
						//									startActivity(new Intent(FragmentServerImage.this.getContext(), ActivityPointProductShop.class));
						//								}
						//								else if (clientClientFuncTypeInfo.getContent().equalsIgnoreCase(ClientFuncActivityType.PRODUCTS.getType()))
						//								{
						//									Intent intent = new Intent(FragmentServerImage.this.getContext(), ActivityProductsSearch.class);
						//									intent.putExtra(ConstValues.DATA_PRODUCT_SORT_ID, clientClientFuncTypeInfo.getParams());
						//									startActivity(intent);
						//								}
						//								else if (clientClientFuncTypeInfo.getContent().equalsIgnoreCase(ClientFuncActivityType.SHENGXIAN.getType()))
						//								{
						//									Intent intent = new Intent(FragmentServerImage.this.getContext(), ActivityShengxian.class);
						//									intent.putExtra(ConstValues.DATA_PRODUCT_SORT_ID, clientClientFuncTypeInfo.getParams());
						//									startActivity(intent);
						//								}
						//								else if (clientClientFuncTypeInfo.getContent().equalsIgnoreCase(ClientFuncActivityType.PRODUCT.getType()))
						//								{
						//									Intent intent = new Intent(FragmentServerImage.this.getContext(), ActivityProduct.class);
						//									intent.putExtra(ConstValues.DATA_PRODUCT_ID, clientClientFuncTypeInfo.getParams());
						//									startActivity(intent);
						//								}
						//							}
						//						}
					}
				}
			});
		}
	}

	public void initView()
	{
		imageView = (ImageView) rootView.findViewById(R.id.iv_bg);
		imageView.setScaleType(ImageView.ScaleType.valueOf(mScaleTypeName));
	}
}
