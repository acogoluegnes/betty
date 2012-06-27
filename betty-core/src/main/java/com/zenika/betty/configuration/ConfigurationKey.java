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
package com.zenika.betty.configuration;

/**
 * 
 * @author Arnaud Cogoluègnes
 *
 */
public enum ConfigurationKey {
	
	SHUTDOWN_PORT("server.shutdown.port","Port to listen on for shutdown signal.","8079"),
	SHUTDOWN_MONITOR("server.shutdown.monitor","Should the process wait for a shutdown on a given port.","true"),
	PORT("server.port","Port to listen on.","8080"),
	CONTEXT_PATH("app.context.path","Context path of the application.","/"),
	CONFIGURATION_FILE("configuration.file","Location of a file-system-based configuration file to override other configuration. Ignored if file doesn't exist.","./betty.properties"),
	ACCEPTORS("server.acceptors","The number of thread dedicated to accepting incoming connections. They then dispatch to pool threads. " +
			"Typically between 1 and the number of processors.","1"), 
	THREAD_POOL_MIN("server.thread.pool.min","Minimum number of threads.","2"), 
	THREAD_POOL_MAX("server.thread.pool.max","Maximum number of threads.","250"),
	HOST("server.host","Host.","127.0.0.1");
	
	private final String name,description,defaultValue;
	
	private ConfigurationKey(String name,String description,String defaultValue) {
		this.name = name;
		this.description = description;
		this.defaultValue = defaultValue;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getDefaultValue() {
		return defaultValue;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public static ConfigurationKey from(String key) {
		for(ConfigurationKey confKey : ConfigurationKey.values()) {
			if(confKey.name.equals("key")) {
				return confKey;
			}
		}
		return null;
	}
	
	
}
