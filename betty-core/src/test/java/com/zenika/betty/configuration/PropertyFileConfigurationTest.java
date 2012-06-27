/**
 * 
 */
package com.zenika.betty.configuration;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.zenika.betty.configuration.support.PropertyFileConfiguration;

/**
 * @author acogoluegnes
 *
 */
public class PropertyFileConfigurationTest {
	
	private Configuration conf;
	
	private static String initialSystemValue = System.getProperty("PORT");
	
	@BeforeClass
	public static void setUp() {
		System.setProperty("PORT", "9090");
	}
	
	@AfterClass
	public static void tearDown() {
		System.setProperty("PORT", initialSystemValue == null ? "" : initialSystemValue);
	}
	
	@Before public void init() {
		conf = new PropertyFileConfiguration();
	}

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
	
	@Test public void systemProperty() {
		System.setProperty("alternative.server.port", "9090");
		Assert.assertEquals("9090",conf.get("alternative.server.port"));
	}
	
}
