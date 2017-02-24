package com.tiexue.potentfiction.entity;

import java.io.Serializable;
import java.util.Date;

public class WxBook implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 书籍Id
	private Integer id;
	// 书籍名称
	private String name;
	// 简介
	private String intr;
	// 发布者ID
	private Integer publisherId;
	// 发布者名称
	private String publisherName;
	// 封面图
	private String coverImgs;
	// tag
	private String tag;
	// mark
	private Integer mark;
	// 排序
	private Integer sort;
	// 状态
	private Integer status;
	// 点击量
	private Integer viewCount;
	// 评论量
	private Integer commentCount;
	// 顶
	private Integer dingCount;
	// 踩
	private Integer caiCount;
	// 分享
	private Integer shareCount;
	// 长度
	private Integer contentLen;
	// 创建时间
	private Date createTime;
	// 更新时间
	private Date updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String GetIntr() {
		return this.intr;
	}

	public void SetIntr(String intr) {
		this.intr = intr;
	}

	public Integer getPublisherId() {
		return this.publisherId;
	}

	public void setPublisherId(Integer publisherId) {
		this.publisherId = publisherId;
	}

	public String getPublisherName() {
		return this.publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public String getCoverImgs() {
		return this.coverImgs;
	}

	public void setCoverImgs(String coverImgs) {
		this.coverImgs = coverImgs;
	}

	public String getTag() {
		return this.tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Integer getMark() {
		return this.mark;
	}

	public void setMark(Integer mark) {
		this.mark = mark;
	}

	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getViewCount() {
		return this.viewCount;
	}

	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}

	public Integer getCommentCount() {
		return this.commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public Integer getDingCount() {
		return this.dingCount;
	}

	public void setDingCount(Integer dingCount) {
		this.dingCount = dingCount;
	}

	public Integer getCaiCount() {
		return this.caiCount;
	}

	public void setCaiCount(Integer caiCount) {
		this.caiCount = caiCount;
	}

	public Integer getShareCount() {
		return this.shareCount;
	}

	public void setShareCount(Integer shareCount) {
		this.shareCount = shareCount;
	}

	public Integer getCotentLen() {
		return this.contentLen;
	}

	public void setContentLen(Integer contentLen) {
		this.contentLen = contentLen;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
