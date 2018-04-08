package com.redpois0n.exceptions;

@SuppressWarnings("serial")
public class IllegalPacketException extends Exception {

	public IllegalPacketException() {
		
	}

	public IllegalPacketException(String message) {
		super(message);
	}

	public IllegalPacketException(Throwable cause) {
		super(cause);
	}

	public IllegalPacketException(String message, Throwable cause) {
		super(message, cause);
	}

}
