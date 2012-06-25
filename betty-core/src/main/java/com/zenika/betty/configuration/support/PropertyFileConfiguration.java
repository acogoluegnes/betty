/**
 * 
 */
package com.zenika.betty.configuration.support;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zenika.betty.configuration.Configuration;
import com.zenika.betty.configuration.ConfigurationKey;

/**
 * @author acogoluegnes
 *
 */
public class PropertyFileConfiguration extends AbstractConfiguration {
	
	private static final String OVERRIDE_FILE_DEFAULT_LOCATION = "/META-INF/betty/betty.properties";
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PropertyFileConfiguration.class);
	
	private final Map<String, String> configuration = new HashMap<String, String>();
	
	public PropertyFileConfiguration() {
		for(ConfigurationKey key : ConfigurationKey.values()) {
			String value = key.getDefaultValue();
			if(value == null) {
				LOGGER.warn("Default value is null for key {}",value);
			} else {
				configuration.put(key.getName(), value);
			}
		}
		try {
			Enumeration<URL> files = PropertyFileConfiguration.class.getClassLoader().getResources(OVERRIDE_FILE_DEFAULT_LOCATION);
			if(files.hasMoreElements()) {
				URL url = files.nextElement();
				InputStream in = null;
				try {
					in = url.openStream();
					Properties props = new Properties();
					props.load(in);
					for(Map.Entry<Object, Object> entry : props.entrySet()) {
						String key = entry.getKey().toString();
						if(LOGGER.isDebugEnabled()) {
							LOGGER.debug("overriding key {} from {} to {}",new Object[]{
								key,configuration.get(key),entry.getValue()
							});
						}
						configuration.put(key,entry.getValue().toString());
					}
				} catch (Exception e) {
					LOGGER.error("couldn't override configuration",e);
				} finally {
					if(in != null) {
						in.close();
					}
				}
			} else {
				LOGGER.info("no {} file to override defaults",OVERRIDE_FILE_DEFAULT_LOCATION);
			}
		} catch (IOException e) {
			LOGGER.error("Cannot open default file {}",OVERRIDE_FILE_DEFAULT_LOCATION,e);
		}
	}

	@Override
	protected Map<String, String> getConfiguration() {
		return configuration;
	}

}
