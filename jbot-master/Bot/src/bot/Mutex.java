package bot;

import java.net.ServerSocket;

public class Mutex {
	
	public static ServerSocket socket;

	/**
	 * Blocks the mutex port so other instances will exit if blocked
	 */
	public static void hook() {
		try {
			socket = new ServerSocket(14456);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(0);
		}
	}
}
