package com.redpois0n.commands;

import java.util.List;

import com.redpois0n.bot.Bot;


public abstract class Command {
	
	/**
	 * Executes the command from List
	 * @param args
	 * @param bots
	 * @throws Exception
	 */
	public void execute(String[] args, List<Bot> bots) throws Exception {
		this.execute(args, bots.toArray(new Bot[0]));
	}
	
	/**
	 * Executes the command
	 * @param args
	 * @param bots
	 * @throws Exception
	 */
	public abstract void execute(String[] args, Bot[] bots) throws Exception;
	
	/**
	 * Returns the usage
	 * @return
	 */
	public abstract String getUsage();
	
	/**
	 * Returns the example
	 * @return
	 */
	public abstract String getExample();
	
	/**
	 * Returns the description
	 * @return
	 */
	public abstract String getDescription();

}
