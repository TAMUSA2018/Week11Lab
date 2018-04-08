package commands;

public abstract class Command {

	/**
	 * Command being performed
	 * @throws Exception
	 */
	public abstract void perform() throws Exception;
	
}
