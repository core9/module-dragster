package io.core9.dragster.data;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;

public class HtmlCssSplitter {

	private String htmlWithInlineCss;
	private String css;
	private String cleanHtml;
	
	
	public String getHtmlWithInlineCss() {
		return htmlWithInlineCss;
	}
	
	public void setHtmlWithInlineCss(String htmlWithInlineCss) {
		this.htmlWithInlineCss = htmlWithInlineCss;
	}
	
	public String getCss() {
		splitCssFromHtml(htmlWithInlineCss);
		return css;
	}

	public void setCss(String css) {
		this.css = css;
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
		        e.removeAttr("class");
		        e.removeAttr("style");
		    }
		  }

		  @Override
		  public void head(Node node, int depth) {        
		  }
		});

		traversor.traverse(doc.body());
		cleanHtml = doc.toString();
	}
	
}
