package io.core9.plugin.test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import io.core9.dragster.data.HtmlCssSplitter;

import org.junit.Test;

public class HtmlCssSplitterTest {

	@Test
	public void test() {
	
		String html = readFile("html-to-split-css.html");
		
		HtmlCssSplitter htmlSplitter = new HtmlCssSplitter();
		htmlSplitter.setHtmlWithInlineCss(html);
		
		String cleanHtml = htmlSplitter.getCleanHtml();
		System.out.println(cleanHtml);
		String cleanCss = htmlSplitter.getCss();
		System.out.println(cleanCss);
		
		System.out.println("pause");
	}
	
	
	static String readFile(String file) {
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
