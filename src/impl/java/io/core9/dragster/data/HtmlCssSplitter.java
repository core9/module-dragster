package io.core9.dragster.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;

public class HtmlCssSplitter {

	private String htmlWithInlineCss;
	private String css = "";
	private String cleanHtml;
	private Map<String, String> cssIdRegister = new HashMap<>();
	
	
	public String getHtmlWithInlineCss() {
		return htmlWithInlineCss;
	}
	
	public void setHtmlWithInlineCss(String htmlWithInlineCss) {
		splitCssFromHtml(htmlWithInlineCss);
		formatCssToFile(cssIdRegister);
		this.htmlWithInlineCss = htmlWithInlineCss;
	}
	
	public String getCss() {
		return css;
	}

	public void setCss(String css) {
		this.css = css;
	}
	
	private void formatCssToFile(Map<String, String> cssIdRegister2) {
		for(Entry<String, String> cssItem : cssIdRegister2.entrySet()){
			css += "#" + cssItem.getKey() + "{ " + cssItem.getValue() + " }" + "\r\n";
		}
	}

	public String getCleanHtml() {
		splitCssFromHtml(htmlWithInlineCss);
		return cleanHtml;
	}
	
	public void setCleanHtml(String cleanHtml) {
		this.cleanHtml = cleanHtml;
	}

	
	private void splitCssFromHtml(String htmlWithInlineCssArg) {
		Document doc = Jsoup.parse(htmlWithInlineCssArg);


		NodeTraversor traversor  = new NodeTraversor(new NodeVisitor() {

		  @Override
		  public void tail(Node node, int depth) {
		    if (node instanceof Element) {
		        Element e = (Element) node;
		        
		        if(e.className().equals("columns-wrapper")){
		        	editCss(e);
		        }
		        
		        if(e.className().equals("column")){
		        	editCss(e);
		        	e.children().remove();
		        }
		        
		        
		        e.removeAttr("class");
		        e.removeAttr("style");
		        e.removeAttr("draggable");
		    }
		  }

		  private void editCss(Element e) {
	        	String id = e.attr("id");
	        	String style = e.attr("style");
	        	cssIdRegister.put(id, style);
		}

		@Override
		  public void head(Node node, int depth) {        
		  }
		});

		traversor.traverse(doc.body());
		cleanHtml = doc.toString();
	}
	
}
