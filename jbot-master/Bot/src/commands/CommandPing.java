package commands;

import bot.Main;

import com.redpois0n.common.packets.Headers;

public class CommandPing extends Command {

	/**
	 * Replies ping
	 */
	@Override
	public void perform() throws Exception {
		Main.writeByte(Headers.PACKET_PING);
	}

}
