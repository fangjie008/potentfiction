package com.tiexue.potentfiction.dto;

public class WxChapterSubDto {
	//章节Id
    private Integer id;
    //章节名称
    private String title;
    //对应的小说Id
    private Integer bookId;
    //章节内容
    private String content;
    //上一章
    private Integer preId;
    //下一章
    private Integer nextId;
    //书名
    private String bookName;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getPreId() {
		return preId;
	}
	public void setPreId(Integer preId) {
		this.preId = preId;
	}
	public Integer getNextId() {
		return nextId;
	}
	public void setNextId(Integer nextId) {
		this.nextId = nextId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookid) {
		this.bookId = bookid;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
    
    
}
