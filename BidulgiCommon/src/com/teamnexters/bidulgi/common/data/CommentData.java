package com.teamnexters.bidulgi.common.data;

public class CommentData {
	private long commentId;
	private long articleId;
	private long writeUserId;
	private String writeUserName;
	private String content;
	
	public long getCommentId() {
		return commentId;
	}
	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriteUserName() {
		return writeUserName;
	}
	public void setWriteUserName(String writeUserName) {
		this.writeUserName = writeUserName;
	}
}
