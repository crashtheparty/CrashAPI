package org.ctp.crashapi.utils;

import java.io.*;
import java.net.URL;

public class CrashConfigUtils {

	public static File getTempFile(String resource) {
		return new CrashConfigUtils().getFile(resource);
	}

	public static File getTempFile(Class<?> clazz, String resource) {
		return new CrashConfigUtils().getFile(clazz, resource);
	}

	public File getFile(String resource) {
		return getFile(getClass(), resource);
	}

	public File getFile(Class<?> clazz, String resource) {
		File file = null;
		URL res = clazz.getResource(resource);
		if (res.getProtocol().equals("jar")) {
			InputStream input = null;
			OutputStream out = null;
			try {
				input = clazz.getResourceAsStream(resource);
				file = File.createTempFile("/tempfile", ".tmp");
				out = new FileOutputStream(file);
				int read;
				byte[] bytes = new byte[1024];

				while ((read = input.read(bytes)) != -1)
					out.write(bytes, 0, read);
			} catch (IOException ex) {
				ex.printStackTrace();
			} finally {
				try {
					input.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				try {
					out.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		} else
			// this will probably work in your IDE, but not from a JAR
			file = new File(res.getFile());

		if (file != null && !file.exists()) throw new RuntimeException("Error: File " + file + " not found!");
		file.deleteOnExit();
		return file;
	}
}
