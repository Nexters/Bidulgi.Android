package com.teamnexters.bidulgi.common.request;

public class CommentRequestPacket extends BidulgiRequestPacket {

	private String content;
	private long articleId;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getArticleId() {
		return articleId;
	}

	public void setArticleId(long articleId) {
		this.articleId = articleId;
	}
}
