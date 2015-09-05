package com.teamnexters.bidulgi.common.response;

import java.util.List;

import com.teamnexters.bidulgi.common.data.CommentData;

public class CommentListResponsePacket extends BidulgiResponsePacket{
	private List<CommentData> commentList;

	public List<CommentData> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<CommentData> commentList) {
		this.commentList = commentList;
	}


}
