package com.teamnexters.bidulgi.common.response;

import com.teamnexters.bidulgi.common.data.ArticleData;

public class ArticleResponsePacket extends BidulgiResponsePacket{
	private ArticleData articleData;

	public ArticleData getArticleData() {
		return articleData;
	}

	public void setArticleData(ArticleData articleData) {
		this.articleData = articleData;
	}

}
