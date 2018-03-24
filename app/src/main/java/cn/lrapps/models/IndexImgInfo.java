/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.models;

/**
 * Created by libit on 2018/3/6.
 */
public class IndexImgInfo
{
	private String adId;
	private String title;
	private String url;

	public IndexImgInfo()
	{
	}

	public IndexImgInfo(String adId, String title, String url)
	{
		this.adId = adId;
		this.title = title;
		this.url = url;
	}

	public String getAdId()
	{
		return adId;
	}

	public void setAdId(String adId)
	{
		this.adId = adId;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}
}
