package com.redpois0n.commands;

import java.net.ServerSocket;

import com.redpois0n.bot.Bot;
import com.redpois0n.bot.Main;
import com.redpois0n.exceptions.IllegalArgumentLengthException;


public class CommandRemoveSocket extends Command {

	/**
	 * Closes and removes server socket
	 */
	@Override
	public void execute(String[] args, Bot[] bots) throws Exception {
		if (args.length != 1) {
			throw new IllegalArgumentLengthException();
		}
		
		int port = Integer.parseInt(args[0]);
		
		ServerSocket socket = Main.sockets.remove(port);
		
		socket.close();
		
		Main.log("Socket removed");
	}

	@Override
	public String getUsage() {
		return "removesocket [PORT]";
	}

	@Override
	public String getExample() {
		return "removesocket 2013";
	}

	@Override
	public String getDescription() {
		return "Removes and stops listen on the socket";
	}

}
