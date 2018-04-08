package com.redpois0n.bot;

import java.net.ServerSocket;

public class ThreadServer implements Runnable {
	
	private final ServerSocket socket;
	
	public ThreadServer(ServerSocket socket) {
		this.socket = socket;
	}

	/**
	 * Listens on port
	 */
	public void run() {
		try {
			while (true) {
				new Bot(socket.accept());
			}
		} catch (Exception ex) {
			Main.handleException(ex);
			Main.sockets.remove(socket.getLocalPort());
		}
	}

}
