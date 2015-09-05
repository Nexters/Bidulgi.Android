package com.teamnexters.bidulgi.common.response;

import java.util.List;

import com.teamnexters.bidulgi.common.data.ArticleData;

public class ArticleListResponsePacket extends BidulgiResponsePacket{
	private List<ArticleData> articleList;

	public List<ArticleData> getArticleList() {
		return articleList;
	}

	public void setArticleList(List<ArticleData> articleList) {
		this.articleList = articleList;
	}

}
