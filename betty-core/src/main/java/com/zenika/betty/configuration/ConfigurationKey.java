/**
 * 
 */
package com.zenika.betty.configuration;

/**
 * @author acogoluegnes
 *
 */
public enum ConfigurationKey {
	
	SHUTDOWN_PORT("server.shutdown.port","Port to listen on for shutdown signal."),
	PORT("server.port","Port to listen on.");

	private final String name,description;
	
	private ConfigurationKey(String name,String description) {
		this.name = name;
		this.description = description;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
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
