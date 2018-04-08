package bot;

import java.io.File;

public class Util {

	/**
	 * 
	 * @return the jar file this program is running from
	 */
	public static File getJarFile() {
		return new File(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath().replace("file:", ""));
	}

}
