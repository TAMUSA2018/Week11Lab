package com.redpois0n.commands;

import com.redpois0n.bot.Bot;
import com.redpois0n.common.packets.Headers;


public class CommandPing extends Command {

	/**
	 * Pings all bots
	 */
	@Override
	public void execute(String[] args, Bot[] bots) throws Exception {
		for (Bot bot : bots) {
			bot.writeHandledByte(Headers.PACKET_LOG_PING);
		}
	}

	@Override
	public String getUsage() {
		return "ping";
	}

	@Override
	public String getExample() {
		return "ping";

	}

	@Override
	public String getDescription() {
		return "Pings all bots";

	}

}
