/**
 * 
 */
package com.zenika.betty.configuration;

import junit.framework.Assert;

import org.junit.Test;

import com.zenika.betty.configuration.support.PropertyFileConfiguration;

/**
 * @author acogoluegnes
 *
 */
public class PropertyFileConfigurationTest {

	private static Configuration conf = new PropertyFileConfiguration();

	@Test public void defaultsLoaded() {
		for(ConfigurationKey key : ConfigurationKey.values()) {
			// don't check, as it's overriden
			if(ConfigurationKey.CONFIGURATION_FILE != key) {
				Assert.assertEquals(key.getDefaultValue(), conf.get(key));
			}
		}
	}
	
	@Test public void loadedFromClasspath() {
		Assert.assertEquals("some value",conf.get("dummy.parameter"));
	}
	
	@Test public void loadedFromTheFileSystem() {
		Assert.assertEquals("very specific value",conf.get("very.specific.parameter"));
	}
	
}
