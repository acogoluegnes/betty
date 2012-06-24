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

	@Test public void loadingDefault() {
		Configuration defaultConfiguration = new DefaultConfiguration();
		Configuration conf = new PropertyFileConfiguration();
		for(ConfigurationKey key : ConfigurationKey.values()) {
			Assert.assertEquals(defaultConfiguration.get(key), conf.get(key));
		}
	}
	
}
