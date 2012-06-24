/**
 * 
 */
package com.zenika.betty.configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author acogoluegnes
 *
 */
public final class DefaultConfiguration implements Configuration {
	
	private static final Map<ConfigurationKey, String> DEFAULTS = new HashMap<ConfigurationKey, String>() {{
		put(ConfigurationKey.SHUTDOWN_PORT, "8079");
		put(ConfigurationKey.PORT,"8080");
	}};

	public String get(ConfigurationKey key) {
		return DEFAULTS.get(key);
	}
	
	public String get(String key) {
		return DEFAULTS.get(ConfigurationKey.from(key));
	}
	
	public int getInt(ConfigurationKey key) {
		String value = get(key);
		return value == null ? null : Integer.valueOf(value);
	}
	
	public int getInt(String key) {
		String value = get(key);
		return value == null ? null : Integer.valueOf(value);
	}
	
}
