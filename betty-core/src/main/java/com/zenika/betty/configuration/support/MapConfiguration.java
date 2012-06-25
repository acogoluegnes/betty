/**
 * 
 */
package com.zenika.betty.configuration.support;

import java.util.Map;

/**
 * @author acogoluegnes
 *
 */
public class MapConfiguration extends AbstractConfiguration {
	
	private final Map<String,String> configuration;
	
	public MapConfiguration(Map<String,String> configuration) {
		this.configuration = configuration;
	}

	/* (non-Javadoc)
	 * @see com.zenika.betty.configuration.support.AbstractConfiguration#getConfiguration()
	 */
	@Override
	protected Map<String, String> getConfiguration() {
		return configuration;
	}

}
