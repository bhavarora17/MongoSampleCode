/**
 * Enum to store message direction
 */
package com.sample.messaging.constants;

public enum MessageDirection {

	INCOMING("INCOMING"), OUTGOING("OUTGOING");

	private String direction;

	/**
	 * Constructor
	 * 
	 * @param direction
	 */
	private MessageDirection(String direction) {
		this.direction = direction;
	}

	public String getDirection() {
		return direction;
	}

	public static MessageDirection getDirection(String value) {
		if (value != null) {
			for (MessageDirection e : MessageDirection.values()) {
				if (value.equalsIgnoreCase(e.getDirection())) {
					return e;
				}
			}
		}
		return null;
	}

}
