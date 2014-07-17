package io.core9.plugin.test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtils {

	
	public static String readFile(String file) {
		URL main = TestDragsterRestRouter.class.getResource(file);
		File path = new File(main.getPath());
		byte[] encoded = null;
		try {
			encoded = Files.readAllBytes(Paths.get(path.getAbsolutePath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new String(encoded, StandardCharsets.UTF_8);
	}
	
	
}
