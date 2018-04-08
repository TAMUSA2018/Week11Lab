package com.redpois0n.exceptions;

@SuppressWarnings("serial")
public class CommandNotFoundException extends Exception {

	public CommandNotFoundException() {
		
	}
	
	public CommandNotFoundException(String s) {
		super(s);
	}
}
