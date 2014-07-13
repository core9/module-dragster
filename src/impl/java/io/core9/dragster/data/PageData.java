/**
 *  Copyright 2012 Wordnik, Inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

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
	static {/*
		categories.add(createCategory(1, "Dogs"));
*/


		long id = 0;
		String name = null;
		Version currentVersion = new Version();
		Host host = new Host();
		String currentHtml = "";
		String status = "active";
		pages.add(createPage(id, name, currentVersion, host, currentHtml, status));
		
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



	static Page createPage(long id, String name, Version currentVersion, Host host, String currentHtml, String status) {
		Page page = new Page();
		page.setId(id);
		page.setName(name);
		page.setCurrentVersion(currentVersion);
		page.setHost(host);
		page.setHtml(currentHtml);
		page.setStatus(status);
		return page;
	}
}