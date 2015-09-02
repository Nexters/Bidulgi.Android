package com.teamnexters.bidulgi.common.data;

public class ArticleData {
	private long articleId;
	private long writeUserId;
	private String title;
	private String content;
	private int commentCount;
	
	
	public long getArticleId() {
		return articleId;
	}
	public void setArticleId(long articleId) {
		this.articleId = articleId;
	}
	public long getWriteUserId() {
		return writeUserId;
	}
	public void setWriteUserId(long writeUserId) {
		this.writeUserId = writeUserId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
}
