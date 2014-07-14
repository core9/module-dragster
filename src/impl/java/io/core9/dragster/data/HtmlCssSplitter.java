package io.core9.dragster.data;

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
		return cleanHtml;
	}
	
	public void setCleanHtml(String cleanHtml) {
		this.cleanHtml = cleanHtml;
	}

	
	private void splitCssFromHtml(String htmlWithInlineCssArg) {
		
	}
	
}
