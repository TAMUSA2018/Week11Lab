package commands;

public class CommandDisconnect extends Command {

	/**
	 * Exists and returns next execution or computer boot
	 */
	@Override
	public void perform() throws Exception {
		System.exit(0);
	}

}
