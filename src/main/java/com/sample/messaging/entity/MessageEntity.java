/**
 * message entity class,used in database interaction
 */
package com.sample.messaging.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "message")
public class MessageEntity {

	@Id
	private String messageId;

	private String userId;

	private Long timestamp;

	private String type;

	private String direction;

	private String content;

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

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
		return "MessageEntity [messageId=" + messageId + ", userId=" + userId + ", timestamp=" + timestamp + ", type="
				+ type + ", direction=" + direction + ", content=" + content + "]";
	}

}
