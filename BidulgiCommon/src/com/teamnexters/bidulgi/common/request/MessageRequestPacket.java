package com.teamnexters.bidulgi.common.request;



public class MessageRequestPacket extends BidulgiRequestPacket{

	private String articleTitle;
	private String articleText;
	private String articlePassword;
	private long soldierId;
	
	public String getArticleTitle() {
		return articleTitle;
	}
	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}
	public String getArticleText() {
		return articleText;
	}
	public void setArticleText(String articleText) {
		this.articleText = articleText;
	}
	public String getArticlePassword() {
		return articlePassword;
	}
	public void setArticlePassword(String articlePassword) {
		this.articlePassword = articlePassword;
	}
	public long getSoldierId() {
		return soldierId;
	}
	public void setSoldierId(long soldierId) {
		this.soldierId = soldierId;
	}

}
