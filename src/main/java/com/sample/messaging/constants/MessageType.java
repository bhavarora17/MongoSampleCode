/**
 * enum to store message type
 */
package com.sample.messaging.constants;

public enum MessageType {

	CONNECT("CONNECT"), MESSAGE("MESSAGE"), DISCONNECT("DISCONNECT");

	private String type;

	/**
	 * Constructor
	 * 
	 * @param direction
	 */
	private MessageType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public static MessageType getType(String value) {
		if (value != null) {
			for (MessageType e : MessageType.values()) {
				if (value.equalsIgnoreCase(e.getType())) {
					return e;
				}
			}
		}
		return null;
	}
}
