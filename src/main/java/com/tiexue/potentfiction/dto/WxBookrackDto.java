package com.tiexue.potentfiction.dto;
public class WxBookrackDto {
	    private Integer id;

	    private Integer bookid;

	    private String bookname;

	    private Integer chapterid;

	    private String chaptertitle;

	    private Integer location;

	    private Integer userid;
		// 简介
		private String intr;
		// 封面图
		private String coverimgs;

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

	    public String getBookname() {
	        return bookname;
	    }

	    public void setBookname(String bookname) {
	        this.bookname = bookname == null ? null : bookname.trim();
	    }

	    public Integer getChapterid() {
	        return chapterid;
	    }

	    public void setChapterid(Integer chapterid) {
	        this.chapterid = chapterid;
	    }

	    public String getChaptertitle() {
	        return chaptertitle;
	    }

	    public void setChaptertitle(String chaptertitle) {
	        this.chaptertitle = chaptertitle == null ? null : chaptertitle.trim();
	    }

	    public Integer getLocation() {
	        return location;
	    }

	    public void setLocation(Integer location) {
	        this.location = location;
	    }

	    public Integer getUserid() {
	        return userid;
	    }

	    public void setUserid(Integer userid) {
	        this.userid = userid;
	    }

		public String getIntr() {
			return intr;
		}

		public void setIntr(String intr) {
			this.intr = intr;
		}

		public String getCoverimgs() {
			return coverimgs;
		}

		public void setCoverimgs(String coverimgs) {
			this.coverimgs = coverimgs;
		}

	
}
