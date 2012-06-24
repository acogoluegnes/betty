/**
 * 
 */
package com.zenika.betty.configuration;

/**
 * @author acogoluegnes
 *
 */
public interface Configuration {
	
	String get(ConfigurationKey key);

	int getInt(ConfigurationKey key);
	
	String get(String key);
	
	int getInt(String key);
	
}
