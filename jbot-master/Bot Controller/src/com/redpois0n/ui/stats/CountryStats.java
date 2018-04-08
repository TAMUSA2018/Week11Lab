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

import net.firefang.ip2c.Country;

import com.redpois0n.bot.Bot;
import com.redpois0n.bot.Main;
import com.redpois0n.ui.Flags;

@SuppressWarnings("unchecked")
public class CountryStats implements Serializable {
	
	private static final long serialVersionUID = 904453855311458163L;
	
	public static transient HashMap<String, CountryStats> map = new HashMap<String, CountryStats>();
	
	private String code;
	private List<String> ip = new ArrayList<String>();
	
	public CountryStats(String code) {
		this.code = code;
	}
	
	/**
	 * Returns the country code
	 * @return
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * Returns IPs already saved
	 * @return
	 */
	public List<String> getIPs() {
		return ip;
	}

	/**
	 * Loads the map
	 * @throws Exception
	 */
	public static void load() throws Exception {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(getFile()));
		map = (HashMap<String, CountryStats>) in.readObject();
		in.close();
	}
	
	/**
	 * Saves the map
	 * @throws Exception
	 */
	public static void save() throws Exception {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(getFile()));
		out.writeObject(map);
		out.close();
	}

	/**
	 * Returns the file to save to
	 * @return
	 */
	public static File getFile() {
		return new File("country.dat");
	}
	
	/**
	 * Adds the bot and it's country to stats
	 * @param bot
	 */
	public static void add(Bot bot) {
		Country country = null;
		
		try {
			country = Flags.getCountry(bot);
		} catch (Exception ex) {
			Main.handleException(ex);
		}
		
		String name = "Unknown (?)";
		
		if (country != null) {
			name = country.getName() + " (" + country.get2cStr() + ")";
		}
		
		CountryStats stats = map.get(name);
		
		if (stats != null){
			if (!stats.ip.contains(bot.getIP())) {
				stats.ip.add(bot.getIP());
			}
		} else {
			stats = new CountryStats(name);
			map.put(name, stats);
			stats.ip.add(bot.getIP());
		}
	}

}
