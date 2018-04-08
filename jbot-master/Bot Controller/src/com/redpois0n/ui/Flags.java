package com.redpois0n.ui;

import java.net.URL;
import java.util.HashMap;

import javax.swing.ImageIcon;

import net.firefang.ip2c.Country;
import net.firefang.ip2c.IP2Country;

import com.redpois0n.bot.Bot;
import com.redpois0n.bot.Main;

public class Flags {

	/**
	 * Cache of flags
	 */
	public static HashMap<String, ImageIcon> flags = new HashMap<String, ImageIcon>();
	
	/**
	 * ip2c class
	 */
	public static IP2Country ip2c;
	
	static {
		try {
			ip2c = new IP2Country("db.dat", IP2Country.MEMORY_MAPPED);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets country from the bots IP address
	 * @param bot
	 * @return Country class
	 * @throws Exception
	 */
	public static Country getCountry(Bot bot) throws Exception {
		return getCountry(bot.getIP());
	}
	
	/**
	 * Gets country from this IP address
	 * @param ip
	 * @return Country class
	 * @throws Exception
	 */
	public static Country getCountry(String ip) throws Exception {
		return ip2c.getCountry(ip);
	}
	
	/**
	 * Gets flag from country class
	 * @param country
	 * @return
	 */
	public static ImageIcon getFlag(Country country) {
		String name = null;
		
		if (country != null) {
			name = country.get2cStr();
		} else {
			name = "errorflag";
		}
		
		return getFlag(name);
	}
	
	/**
	 * Gets flag from string (If not exists, errorflag will be used)
	 * @param name
	 * @return
	 */
	public static ImageIcon getFlag(String name) {
		if (flags.containsKey(name)) {
			return flags.get(name);
		} else {
			URL url = Main.class.getResource("/flags/" + name.toLowerCase() + ".png");
			
			if (url == null) {
				url = Main.class.getResource("/flags/errorflag.png");
			}
			
			ImageIcon icon = new ImageIcon(url);
			flags.put(name, icon);
			return icon;
		}
	}
	
}
