package com.teamnexters.bidulgi.common.data;

public class MessageData {
	private String content;
	private String title;
	private long messageId;
	private long sendTime;
	private long sendUserId;
	private long receiveSoldierId;
	
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
	public long getMessageId() {
		return messageId;
	}
	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}
	public long getSendTime() {
		return sendTime;
	}
	public void setSendTime(long sendTime) {
		this.sendTime = sendTime;
	}
	public long getReceiveSoldierId() {
		return receiveSoldierId;
	}
	public void setReceiveSoldierId(long receiveSoldierId) {
		this.receiveSoldierId = receiveSoldierId;
	}
	public long getSendUserId() {
		return sendUserId;
	}
	public void setSendUserId(long sendUserId) {
		this.sendUserId = sendUserId;
	}
}
