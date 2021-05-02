package com.iotanesia.jamkrindocms.dto.response;

public class MainMenuResponse {
	
	private String name;
	private String path;
	private String icon;
	private String url;
	private String category;
	private PermissionDto permission;
	private MainMenuResponse[] child;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public PermissionDto getPermission() {
		return permission;
	}
	public void setPermission(PermissionDto permission) {
		this.permission = permission;
	}
	public MainMenuResponse[] getChild() {
		return child;
	}
	public void setChild(MainMenuResponse[] child) {
		this.child = child;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
}
