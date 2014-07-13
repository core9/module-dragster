package io.core9.dragster.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.wordnik.swagger.annotations.ApiModelProperty;


@XmlRootElement(name = "Page")
public class Page {
	private long id;
	private HostName hostname;
	private String name;
	private List<Version> versions = new ArrayList<Version>();
	@SuppressWarnings("unused")
	private String currentHtml;
	private Version currentVersion;
	private String status;
	private Map<String, String> htmlRepository = new HashMap<>();

	@XmlElement(name = "id")
	@ApiModelProperty(value = "id of page", required = true)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@XmlElement(name = "hostname")
	public HostName getHostname() {
		return hostname;
	}

	public void setHostname(HostName hostname) {
		this.hostname = hostname;
	}

	@XmlElement(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@XmlElement(name = "currentVersion")
	public Version getCurrentVersion() {
		return currentVersion;
	}

	public void setCurrentVersion(Version currentVersion) {
		this.currentVersion = currentVersion;
	}

	@XmlElementWrapper(name = "versions")
	@XmlElement(name = "versions")
	public List<Version> getVersions() {
		return versions;
	}

	public void setVersions(List<Version> versions) {
		this.versions = versions;
	}

	@XmlElementWrapper(name = "currentHtml")
	@XmlElement(name = "currentHtml")
	public String getHtml() {
		return htmlRepository.get(currentVersion);
	}

	public void setHtml(String currentHtml) {
		this.currentHtml = currentHtml;
	}

	@XmlElement(name = "status")
	@ApiModelProperty(value = "page status in the store", allowableValues = "available,pending,sold")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
