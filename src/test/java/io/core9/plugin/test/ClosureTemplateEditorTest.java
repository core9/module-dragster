package io.core9.plugin.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

import org.junit.Test;

public class ClosureTemplateEditorTest {
	


	private boolean inHtml = false;
	private String htmlStr = "";

	@Test
	public void test() throws IOException {

		JSONObject rawWidget = (JSONObject) JSONValue.parse(FileUtils.readFile("TestClosureTemplateEditor.widgetExctract.json"));
		
		String template = (String) rawWidget.get("template");
		
		
		
		BufferedReader bufReader = new BufferedReader(new StringReader(template));

		String line=null;
		while( (line=bufReader.readLine()) != null )
		{
			if(line.startsWith("<!DOCTYPE")) inHtml = true;
			addToHtmlString(line);
			if(line.startsWith("</html>")) inHtml = false;
			System.out.println(line);
		}
		
		System.out.println(htmlStr);
	}

	private void addToHtmlString(String line) {
		if(inHtml){
			htmlStr += line;
		}
	}

}
