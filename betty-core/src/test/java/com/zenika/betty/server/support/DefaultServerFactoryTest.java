/**
 * 
 */
package com.zenika.betty.server.support;

import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;

import com.zenika.betty.TestUtils;
import com.zenika.betty.configuration.Configuration;
import com.zenika.betty.configuration.ConfigurationKey;
import com.zenika.betty.configuration.support.MapConfiguration;

/**
 * @author acogoluegnes
 *
 */
public class DefaultServerFactoryTest {

	@Test public void runServer() throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		for(ConfigurationKey key : ConfigurationKey.values()) {
			map.put(key.getName(), key.getDefaultValue());
		}
		final int port = TestUtils.getAvailablePort();
		map.put(ConfigurationKey.PORT.getName(), String.valueOf(port));
		
		Configuration configuration = new MapConfiguration(map);
		DefaultServerFactory serverFactory = new DefaultServerFactory() {
			@Override
			protected String getWarLocation(Configuration configuration) {
				return "";
			}
			@Override
			protected void configureWebAppLocation(WebAppContext wac,
					Configuration configuration) {
				wac.setDescriptor("./src/test/resources/com/zenika/betty/server/support/default_server_factory_web.xml");
				wac.setWar("./src/test/resources/com/zenika/betty/server/support/");
			}
		};
		Server server = serverFactory.createServer(configuration);
		try {
			server.start();
			URL url = new URL("http://127.0.0.1:"+port+"/hello");
		    URLConnection conn = url.openConnection();
		    conn.connect();
		    Assert.assertEquals("Hello World!",IOUtils.toString(conn.getInputStream()));
		} finally {
			server.stop();
		}
	}
	
	
	
}
