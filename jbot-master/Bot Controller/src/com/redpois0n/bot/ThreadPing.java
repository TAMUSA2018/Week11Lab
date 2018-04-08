package com.redpois0n.bot;

import com.redpois0n.common.packets.Headers;

public class ThreadPing implements Runnable {

	/**
	 * Pings all bots and repeats after 2,5 seconds
	 */
	public void run() {
		while (true) {

			for (int i = 0; i < Main.bots.size(); i++) {
				try {
					Bot bot = Main.bots.get(i);
					bot.writeHandledByte(Headers.PACKET_PING);
				} catch (Exception ex) {
					Main.handleException(ex);
				}
			}

			try {
				Thread.sleep(2500L);
			} catch (Exception ex) {
				Main.handleException(ex);
			}
		}
	}

}
