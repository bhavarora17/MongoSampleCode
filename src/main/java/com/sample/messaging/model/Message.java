/**
 * message model class,used in REST API interaction
 */
package com.sample.messaging.model;

public class Message {

	private String userId;

	private Long timestamp;

	private String type;

	private String direction;

	private String content;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	@Override
	public String toString() {
		return "Message [userId=" + userId + ", timestamp=" + timestamp + ", type=" + type + ", direction=" + direction
				+ ", content=" + content + "]";
	}

}
