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
package com.zenika.betty.server.support;

import java.net.URL;
import java.security.ProtectionDomain;

import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.mortbay.jetty.webapp.WebAppContext;
import org.mortbay.thread.QueuedThreadPool;
import org.mortbay.thread.ThreadPool;

import com.zenika.betty.configuration.Configuration;
import com.zenika.betty.configuration.ConfigurationKey;
import com.zenika.betty.server.ServerFactory;

/**
 * 
 * @author Arnaud Cogoluègnes
 *
 */
public class DefaultServerFactory implements ServerFactory {

	/* (non-Javadoc)
	 * @see com.zenika.betty.server.ServerFactory#createServer()
	 */
	public Server createServer(Configuration configuration) {
		Server server = new Server();
		
		Connector [] connectors = createConnectors(configuration);
		for(Connector connector : connectors) {
			server.addConnector(connector);
		}

		WebAppContext wac = new WebAppContext();
		configureWebAppLocation(wac,configuration);
		wac.setContextPath(configuration.get(ConfigurationKey.CONTEXT_PATH));
		server.setHandler(wac);
		server.setThreadPool(createThreadPool(configuration));
		server.setStopAtShutdown(true);
		return server;
	}
	
	private ThreadPool createThreadPool(Configuration configuration) {
		QueuedThreadPool pool = new QueuedThreadPool();
		pool.setMinThreads(configuration.getInt(ConfigurationKey.THREAD_POOL_MIN));
		pool.setMinThreads(configuration.getInt(ConfigurationKey.THREAD_POOL_MAX));
		return pool;
	}

	protected void configureWebAppLocation(WebAppContext wac,Configuration configuration) {
		wac.setWar(getWarLocation(configuration));
	}

	protected Connector [] createConnectors(Configuration configuration) {
		SelectChannelConnector connector = new SelectChannelConnector();
		connector.setAcceptors(configuration.getInt(ConfigurationKey.ACCEPTORS));
		connector.setPort(configuration.getInt(ConfigurationKey.PORT));
		connector.setHost(configuration.get(ConfigurationKey.HOST));
		return new Connector [] {connector};
	}
	
	protected String getWarLocation(Configuration configuration) {
		ProtectionDomain domain = DefaultServerFactory.class.getProtectionDomain();
		URL location = domain.getCodeSource().getLocation();
		return location.toString();
	}

}
