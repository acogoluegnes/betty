/**
 * 
 */
package com.zenika.betty.server.support;

import java.net.URL;
import java.security.ProtectionDomain;

import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.mortbay.jetty.webapp.WebAppContext;

import com.zenika.betty.configuration.Configuration;
import com.zenika.betty.configuration.ConfigurationKey;
import com.zenika.betty.server.ServerFactory;

/**
 * @author acogoluegnes
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
		server.setStopAtShutdown(true);
		return server;
	}
	
	protected void configureWebAppLocation(WebAppContext wac,Configuration configuration) {
		wac.setWar(getWarLocation(configuration));
	}

	protected Connector [] createConnectors(Configuration configuration) {
		SelectChannelConnector connector = new SelectChannelConnector();
		connector.setPort(configuration.getInt(ConfigurationKey.PORT));
		connector.setHost("127.0.0.1");
		return new Connector [] {connector};
	}
	
	protected String getWarLocation(Configuration configuration) {
		ProtectionDomain domain = DefaultServerFactory.class.getProtectionDomain();
		URL location = domain.getCodeSource().getLocation();
		return location.toString();
	}

}
