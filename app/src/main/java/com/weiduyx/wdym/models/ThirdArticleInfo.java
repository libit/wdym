//
// ThirdArticleInfo.h
//
// 作者：libit 创建于 2018年3月1日.
// Copyright © 2018年 LR. All rights reserved.
//
package com.weiduyx.wdym.models;

import com.google.gson.annotations.SerializedName;

public class ThirdArticleInfo
{
	@SerializedName("id")
	private Integer id;
	@SerializedName("thirdId")
	private Integer thirdId;
	@SerializedName("typeid")
	private Short typeid;
	@SerializedName("typeid2")
	private String typeid2;
	@SerializedName("sortrank")
	private Integer sortrank;
	@SerializedName("flag")
	private String flag;
	@SerializedName("ismake")
	private Short ismake;
	@SerializedName("channel")
	private Short channel;
	@SerializedName("arcrank")
	private Short arcrank;
	@SerializedName("click")
	private Integer click;
	@SerializedName("money")
	private Short money;
	@SerializedName("title")
	private String title;
	@SerializedName("shorttitle")
	private String shorttitle;
	@SerializedName("color")
	private String color;
	@SerializedName("writer")
	private String writer;
	@SerializedName("source")
	private String source;
	@SerializedName("litpic")
	private String litpic;
	@SerializedName("pubdate")
	private Integer pubdate;
	@SerializedName("senddate")
	private Integer senddate;
	@SerializedName("mid")
	private Integer mid;
	@SerializedName("keywords")
	private String keywords;
	@SerializedName("lastpost")
	private Integer lastpost;
	@SerializedName("scores")
	private Integer scores;
	@SerializedName("goodpost")
	private Integer goodpost;
	@SerializedName("badpost")
	private Integer badpost;
	@SerializedName("voteid")
	private Integer voteid;
	@SerializedName("notpost")
	private Boolean notpost;
	@SerializedName("description")
	private String description;
	@SerializedName("filename")
	private String filename;
	@SerializedName("dutyadmin")
	private Integer dutyadmin;
	@SerializedName("tackid")
	private Integer tackid;
	@SerializedName("mtype")
	private Integer mtype;
	@SerializedName("weight")
	private Integer weight;
	@SerializedName("body")
	private String body;
	@SerializedName("redirecturl")
	private String redirecturl;
	@SerializedName("templet")
	private String templet;
	@SerializedName("sortIndex")
	private int sortIndex;
	@SerializedName("status")
	private byte status;
	@SerializedName("addDateLong")
	private long addDateLong;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getThirdId()
	{
		return thirdId;
	}

	public void setThirdId(Integer thirdId)
	{
		this.thirdId = thirdId;
	}

	public Short getTypeid()
	{
		return typeid;
	}

	public void setTypeid(Short typeid)
	{
		this.typeid = typeid;
	}

	public String getTypeid2()
	{
		return typeid2;
	}

	public void setTypeid2(String typeid2)
	{
		this.typeid2 = typeid2;
	}

	public Integer getSortrank()
	{
		return sortrank;
	}

	public void setSortrank(Integer sortrank)
	{
		this.sortrank = sortrank;
	}

	public String getFlag()
	{
		return flag;
	}

	public void setFlag(String flag)
	{
		this.flag = flag;
	}

	public Short getIsmake()
	{
		return ismake;
	}

	public void setIsmake(Short ismake)
	{
		this.ismake = ismake;
	}

	public Short getChannel()
	{
		return channel;
	}

	public void setChannel(Short channel)
	{
		this.channel = channel;
	}

	public Short getArcrank()
	{
		return arcrank;
	}

	public void setArcrank(Short arcrank)
	{
		this.arcrank = arcrank;
	}

	public Integer getClick()
	{
		return click;
	}

	public void setClick(Integer click)
	{
		this.click = click;
	}

	public Short getMoney()
	{
		return money;
	}

	public void setMoney(Short money)
	{
		this.money = money;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getShorttitle()
	{
		return shorttitle;
	}

	public void setShorttitle(String shorttitle)
	{
		this.shorttitle = shorttitle;
	}

	public String getColor()
	{
		return color;
	}

	public void setColor(String color)
	{
		this.color = color;
	}

	public String getWriter()
	{
		return writer;
	}

	public void setWriter(String writer)
	{
		this.writer = writer;
	}

	public String getSource()
	{
		return source;
	}

	public void setSource(String source)
	{
		this.source = source;
	}

	public String getLitpic()
	{
		return litpic;
	}

	public void setLitpic(String litpic)
	{
		this.litpic = litpic;
	}

	public Integer getPubdate()
	{
		return pubdate;
	}

	public void setPubdate(Integer pubdate)
	{
		this.pubdate = pubdate;
	}

	public Integer getSenddate()
	{
		return senddate;
	}

	public void setSenddate(Integer senddate)
	{
		this.senddate = senddate;
	}

	public Integer getMid()
	{
		return mid;
	}

	public void setMid(Integer mid)
	{
		this.mid = mid;
	}

	public String getKeywords()
	{
		return keywords;
	}

	public void setKeywords(String keywords)
	{
		this.keywords = keywords;
	}

	public Integer getLastpost()
	{
		return lastpost;
	}

	public void setLastpost(Integer lastpost)
	{
		this.lastpost = lastpost;
	}

	public Integer getScores()
	{
		return scores;
	}

	public void setScores(Integer scores)
	{
		this.scores = scores;
	}

	public Integer getGoodpost()
	{
		return goodpost;
	}

	public void setGoodpost(Integer goodpost)
	{
		this.goodpost = goodpost;
	}

	public Integer getBadpost()
	{
		return badpost;
	}

	public void setBadpost(Integer badpost)
	{
		this.badpost = badpost;
	}

	public Integer getVoteid()
	{
		return voteid;
	}

	public void setVoteid(Integer voteid)
	{
		this.voteid = voteid;
	}

	public Boolean getNotpost()
	{
		return notpost;
	}

	public void setNotpost(Boolean notpost)
	{
		this.notpost = notpost;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getFilename()
	{
		return filename;
	}

	public void setFilename(String filename)
	{
		this.filename = filename;
	}

	public Integer getDutyadmin()
	{
		return dutyadmin;
	}

	public void setDutyadmin(Integer dutyadmin)
	{
		this.dutyadmin = dutyadmin;
	}

	public Integer getTackid()
	{
		return tackid;
	}

	public void setTackid(Integer tackid)
	{
		this.tackid = tackid;
	}

	public Integer getMtype()
	{
		return mtype;
	}

	public void setMtype(Integer mtype)
	{
		this.mtype = mtype;
	}

	public Integer getWeight()
	{
		return weight;
	}

	public void setWeight(Integer weight)
	{
		this.weight = weight;
	}

	public String getBody()
	{
		return body;
	}

	public void setBody(String body)
	{
		this.body = body;
	}

	public String getRedirecturl()
	{
		return redirecturl;
	}

	public void setRedirecturl(String redirecturl)
	{
		this.redirecturl = redirecturl;
	}

	public String getTemplet()
	{
		return templet;
	}

	public void setTemplet(String templet)
	{
		this.templet = templet;
	}

	public int getSortIndex()
	{
		return sortIndex;
	}

	public void setSortIndex(int sortIndex)
	{
		this.sortIndex = sortIndex;
	}

	public byte getStatus()
	{
		return status;
	}

	public void setStatus(byte status)
	{
		this.status = status;
	}

	public long getAddDateLong()
	{
		return addDateLong;
	}

	public void setAddDateLong(long addDateLong)
	{
		this.addDateLong = addDateLong;
	}
}