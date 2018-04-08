package com.redpois0n.commands;

import com.redpois0n.bot.Bot;
import com.redpois0n.bot.Main;

public class CommandClear extends Command {

	/**
	 * Clears console(s)
	 */
	@Override
	public void execute(String[] args, Bot[] bots) throws Exception {
		Main.gui.clear();
		
		for (int i = 0; i < 25; i++) {
			System.out.println();
		}
	}

	@Override
	public String getUsage() {
		return "clear";
	}

	@Override
	public String getExample() {
		return "clear";
	}

	@Override
	public String getDescription() {
		return "Clears log and console";
	}

}
