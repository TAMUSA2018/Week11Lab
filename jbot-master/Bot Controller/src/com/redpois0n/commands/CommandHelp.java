package com.redpois0n.commands;

import com.redpois0n.bot.Bot;
import com.redpois0n.bot.Main;

public class CommandHelp extends Command {

	/**
	 * Prints out every command and their usage, example and description
	 */
	@Override
	public void execute(String[] args, Bot[] bots) throws Exception {
		for (String str : Commands.commands.keySet()) {
			Command command = Commands.commands.get(str).newInstance();
			Main.log("* " + str + " ----------");
			Main.log("    Usage: " + command.getUsage());
			Main.log("    Example: " + command.getExample());
			Main.log("    Description: " + command.getDescription());
		}
	}

	@Override
	public String getUsage() {
		return "help";
	}

	@Override
	public String getExample() {
		return "help";
	}

	@Override
	public String getDescription() {
		return "Displays all commands";
	}

}
