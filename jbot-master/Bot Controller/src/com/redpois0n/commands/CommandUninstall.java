package com.redpois0n.commands;

import com.redpois0n.bot.Bot;
import com.redpois0n.bot.Main;
import com.redpois0n.common.packets.Headers;


public class CommandUninstall extends Command {

	/**
	 * Sends the uninstall packet
	 */
	@Override
	public void execute(String[] args, Bot[] bots) throws Exception {
		for (int i = 0; i < bots.length; i++) {
			try {
				Bot bot = bots[i];
				bot.writeHandledByte(Headers.PACKET_UNINSTALL);
			} catch (Exception ex) {
				Main.handleException(ex);
			}
		}
	}

	@Override
	public String getUsage() {
		return "uninstall";
	}

	@Override
	public String getExample() {
		return "uninstall";
	}

	@Override
	public String getDescription() {
		return "Uninstalls all bots";
	}

}
