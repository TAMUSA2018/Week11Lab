package commands;

import bot.Main;

public class CommandReconnect extends Command {

	/**
	 * Disconnects and it will automatically reconnect after set timeout
	 */
	@Override
	public void perform() throws Exception {
		Main.disconnect();
	}

}
