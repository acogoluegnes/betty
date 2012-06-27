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

import java.util.Map;

import com.zenika.betty.configuration.Configuration;
import com.zenika.betty.configuration.ConfigurationKey;

/**
 * 
 * @author Arnaud Cogoluègnes
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
	
	@Override
	public boolean getBoolean(ConfigurationKey key) {
		String value = get(key);
		return evaluateBoolean(value);
	}
	
	@Override
	public boolean getBoolean(String key) {
		String value = get(key);
		return evaluateBoolean(value);
	}
	
	private boolean evaluateBoolean(String value) {
		if("true".equals(value) || "yes".equals(value) || "1".equals(value)) {
			return true;
		} else {
			return false;
		}
	}
}
