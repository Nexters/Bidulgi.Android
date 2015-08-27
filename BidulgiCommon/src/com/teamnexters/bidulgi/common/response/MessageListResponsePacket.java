package com.teamnexters.bidulgi.common.response;

import java.util.List;

import com.teamnexters.bidulgi.common.data.MessageData;

public class MessageListResponsePacket extends BidulgiResponsePacket{
	private List<MessageData> messageData;

	public List<MessageData> getMessageData() {
		return messageData;
	}

	public void setMessageData(List<MessageData> messageData) {
		this.messageData = messageData;
	}

}