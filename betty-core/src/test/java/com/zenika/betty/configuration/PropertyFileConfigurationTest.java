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
		Configuration conf = new PropertyFileConfiguration();
		for(ConfigurationKey key : ConfigurationKey.values()) {
			Assert.assertEquals(key.getDefaultValue(), conf.get(key));
		}
	}
	
}
