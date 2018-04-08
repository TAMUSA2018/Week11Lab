package com.redpois0n.bot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Util {

	public static String[] fileToArray(File file) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

		List<String> list = new ArrayList<String>();
		String line;

		while ((line = reader.readLine()) != null) {
			list.add(line);
		}

		reader.close();

		return list.toArray(new String[0]);
	}

	public static String getStackTrace(Throwable aThrowable) {
		Writer result = new StringWriter();
		PrintWriter printWriter = new PrintWriter(result);
		aThrowable.printStackTrace(printWriter);
		return result.toString();
	}
	
	public static ImageIcon getIcon(String name) {
		return new ImageIcon(Main.class.getResource("/icons/" + name + ".png"));
	}
}
