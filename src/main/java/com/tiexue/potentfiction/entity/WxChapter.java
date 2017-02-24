package com.tiexue.potentfiction.entity;

import java.util.Date;

public class WxChapter {
    private Integer id;

    private Integer bookid;

    private String intro;

    private Integer sortorder;

    private String title;

    private Integer chaptertype;

    private Integer pirce;

    private Integer status;

    private Integer contentlen;

    private Date createtime;

    private Date updatetime;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }

    public Integer getSortorder() {
        return sortorder;
    }

    public void setSortorder(Integer sortorder) {
        this.sortorder = sortorder;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getChaptertype() {
        return chaptertype;
    }

    public void setChaptertype(Integer chaptertype) {
        this.chaptertype = chaptertype;
    }

    public Integer getPirce() {
        return pirce;
    }

    public void setPirce(Integer pirce) {
        this.pirce = pirce;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getContentlen() {
        return contentlen;
    }

    public void setContentlen(Integer contentlen) {
        this.contentlen = contentlen;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}