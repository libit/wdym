/*
 * Libit保留所有版权，如有疑问联系QQ：308062035
 * Copyright (c) 2018.
 */
package cn.lrapps.models;

/**
 * Created by libit on 2017/10/14.
 */
public class WxLoginEvent
{
	public static final String WX_REQUEST_LOGIN = "wx_request_login";
	private String event;
	private String code;
	private String state;

	public WxLoginEvent(String event)
	{
		this.event = event;
	}

	public WxLoginEvent(String event, String code)
	{
		this.event = event;
		this.code = code;
	}

	public WxLoginEvent(String event, String code, String state)
	{
		this.event = event;
		this.code = code;
		this.state = state;
	}

	public String getEvent()
	{
		return event;
	}

	public void setEvent(String event)
	{
		this.event = event;
	}

	public String getCode()
	{
		return code;
	}

	public WxLoginEvent setCode(String code)
	{
		this.code = code;
		return this;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}
}
