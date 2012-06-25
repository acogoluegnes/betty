/**
 * 
 */
package com.zenika.betty.configuration;

/**
 * @author acogoluegnes
 *
 */
public enum ConfigurationKey {
	
	SHUTDOWN_PORT("server.shutdown.port","Port to listen on for shutdown signal.","8079"),
	PORT("server.port","Port to listen on.","8080"),
	CONTEXT_PATH("app.context.path","Context path of the application.","/");

	private final String name,description,defaultValue;
	
	private ConfigurationKey(String name,String description,String defaultValue) {
		this.name = name;
		this.description = description;
		this.defaultValue = defaultValue;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getDefaultValue() {
		return defaultValue;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public static ConfigurationKey from(String key) {
		for(ConfigurationKey confKey : ConfigurationKey.values()) {
			if(confKey.name.equals("key")) {
				return confKey;
			}
		}
		return null;
	}
	
	
}
