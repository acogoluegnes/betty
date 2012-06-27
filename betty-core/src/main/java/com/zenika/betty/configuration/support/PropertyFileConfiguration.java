/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zenika.betty.configuration.support;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zenika.betty.configuration.ConfigurationKey;

/**
 * 
 * @author Arnaud Cogoluègnes
 *
 */
public class PropertyFileConfiguration extends AbstractConfiguration {

	private static final String OVERRIDE_FILE_DEFAULT_LOCATION = "META-INF/betty/betty.properties";

	private static final Logger LOGGER = LoggerFactory
			.getLogger(PropertyFileConfiguration.class);

	private final Map<String, String> configuration = new HashMap<String, String>();

	public PropertyFileConfiguration() {
		for (ConfigurationKey key : ConfigurationKey.values()) {
			String value = key.getDefaultValue();
			if (value == null) {
				LOGGER.warn("Default value is null for key {}", value);
			} else {
				configuration.put(key.getName(), value);
			}
		}
		try {
			Enumeration<URL> files = PropertyFileConfiguration.class
					.getClassLoader().getResources(
							OVERRIDE_FILE_DEFAULT_LOCATION);
			if (files.hasMoreElements()) {
				URL url = files.nextElement();
				InputStream in = null;
				try {
					in = url.openStream();
					Properties props = new Properties();
					props.load(in);
					copyPropertiesIntoMap(props, configuration);
					if (files.hasMoreElements()) {
						LOGGER.warn(
								"More than one {} file found, used the first one, but this is perhaps not what you're expecting. "
										+ "Please check the classpath to be sure there's only one {} file.",
								OVERRIDE_FILE_DEFAULT_LOCATION,
								OVERRIDE_FILE_DEFAULT_LOCATION);
					}
				} catch (Exception e) {
					LOGGER.error("couldn't override configuration", e);
				} finally {
					if (in != null) {
						in.close();
					}
				}
			} else {
				LOGGER.info("no {} file to override defaults",
						OVERRIDE_FILE_DEFAULT_LOCATION);
			}
		} catch (IOException e) {
			LOGGER.error("Cannot open default file {}",
					OVERRIDE_FILE_DEFAULT_LOCATION, e);
		}

		String configurationFile = configuration
				.get(ConfigurationKey.CONFIGURATION_FILE.getName());
		if (configurationFile != null && configurationFile.trim().length() > 0) {
			File file = new File(configurationFile);
			if (file.exists()) {
				InputStream in = null;
				try {
					in = new BufferedInputStream(new FileInputStream(file));
					Properties props = new Properties();
					props.load(in);
					copyPropertiesIntoMap(props, configuration);
				} catch (Exception e) {
					LOGGER.error("couldn't override configuration", e);
				} finally {
					if (in != null) {
						try {
							in.close();
						} catch (IOException e) {
							LOGGER.error("couldn't close input stream", e);
						}
					}
				}
			} else {
				LOGGER.info("File-system based configuration ({}) doesn't exist.",configurationFile);
			}
		}
		for(Map.Entry<String, String> entry : configuration.entrySet()) {
			String value = entry.getValue();
			String [] placeholders = PropertyUtils.getPlaceholders(value);
			if(placeholders.length > 0) {
				for(String placeholder : placeholders) {
					String placeholderValue = System.getProperty(placeholder);
					if(placeholderValue == null) {
						placeholderValue = System.getenv(placeholder);
						if(placeholderValue != null) {
							value = PropertyUtils.replace(value, placeholder, placeholderValue);
						}
					} else {
						value = PropertyUtils.replace(value, placeholder, placeholderValue);
					}
				}
				entry.setValue(value);
			}
		}
	}

	private void copyPropertiesIntoMap(Properties props,
			Map<String, String> conf) {
		for (Map.Entry<Object, Object> entry : props.entrySet()) {
			String key = entry.getKey().toString();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("overriding key {} from {} to {}", new Object[] {
						key, conf.get(key), entry.getValue() });
			}
			conf.put(key, entry.getValue().toString());
		}
	}

	@Override
	protected Map<String, String> getConfiguration() {
		return configuration;
	}

}
