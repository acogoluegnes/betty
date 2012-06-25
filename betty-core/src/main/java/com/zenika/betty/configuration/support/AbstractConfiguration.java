/**
 * 
 */
package com.zenika.betty.configuration.support;

import java.util.Map;

import com.zenika.betty.configuration.Configuration;
import com.zenika.betty.configuration.ConfigurationKey;

/**
 * @author acogoluegnes
 *
 */
public abstract class AbstractConfiguration implements Configuration {
	
	protected abstract Map<String,String> getConfiguration();

	public String get(ConfigurationKey key) {
		return getConfiguration().get(key.getName());
	}
	
	public String get(String key) {
		return getConfiguration().get(key);
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
