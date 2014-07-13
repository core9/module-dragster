package io.core9.dragster.data;

import io.core9.dragster.model.Host;
import io.core9.dragster.model.Page;
import io.core9.dragster.model.Version;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageData {
	static List<Page> pages = new ArrayList<Page>();
	static Map<String, Object> repository = new HashMap<>();
	static {

		long id = 0;
		String name = null;
		Version currentVersion = new Version();
		Host host = new Host();
		String currentHtml = "";
		String status = "available";
		List<Version> versions = new ArrayList<>();
		pages.add(createPage(id, name, currentVersion, host, currentHtml, status, versions));
		
	}

	public Page getPagebyId(long pageId) {
		for (Page page : pages) {
			if (page.getId() == pageId) {
				return page;
			}
		}
		return null;
	}

	public List<Page> findPageByStatus(String status) {
		String[] statues = status.split(",");
		List<Page> result = new java.util.ArrayList<Page>();
		for (Page page : pages) {
			for (String s : statues) {
				if (s.equals(page.getStatus())) {
					result.add(page);
				}
			}
		}
		return result;
	}

	public List<Page> findPageByVersions(String versions) {
		String[] versionList = versions.split(",");
		List<Page> result = new java.util.ArrayList<Page>();
		for (Page page : pages) {
			if (null != page.getVersions()) {
				for (Version version : page.getVersions()) {
					for (String versionListString : versionList) {
						if (versionListString.equals(version.getName()))
							result.add(page);
					}
				}
			}
		}
		return result;
	}

	public void addPage(Page page) {
		if (pages.size() > 0) {
			for (int i = pages.size() - 1; i >= 0; i--) {
				if (pages.get(i).getId() == page.getId()) {
					pages.remove(i);
				}
			}
		}
		pages.add(page);
	}



	static Page createPage(long id, String name, Version currentVersion, Host host, String currentHtml, String status, List<Version> versions) {
		Page page = new Page();
		page.setId(id);
		page.setName(name);
		page.setCurrentVersion(currentVersion);
		page.setHost(host);
		page.setHtml(currentHtml);
		page.setStatus(status);
		page.setVersions(versions);
		return page;
	}
}