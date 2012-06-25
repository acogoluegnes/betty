/*
 * 
 */
package com.zenika.betty;

import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

import com.zenika.betty.configuration.Configuration;
import com.zenika.betty.configuration.ConfigurationKey;
import com.zenika.betty.configuration.support.PropertyFileConfiguration;
import com.zenika.betty.lifecycle.StopMonitorThread;
import com.zenika.betty.server.ServerFactory;
import com.zenika.betty.server.support.DefaultServerFactory;

/**
 * 
 * @author acogoluegnes
 *
 */
public class Server {

	public static void main(String ... args) throws Exception {
		// TODO use Java service extension to find the configuration impl to use
		Configuration configuration = new PropertyFileConfiguration();
		
		// TODO log configuration
		
		if (args.length == 1 && "stop".equals(args[0])) {
			sendStopSignal(configuration);
		} else {
			// TODO use Java service extension to find the server factory impl to use
			ServerFactory serverFactory = new DefaultServerFactory();
			org.mortbay.jetty.Server server = serverFactory.createServer(configuration);
			new StopMonitorThread(configuration,server).start();
			server.start();
		}
		
	}
	
	private static void sendStopSignal(Configuration configuration) throws Exception {
		Socket s = new Socket(InetAddress.getByName("127.0.0.1"), configuration.getInt(ConfigurationKey.SHUTDOWN_PORT));
		OutputStream out = s.getOutputStream();
		out.write(("\r\n").getBytes());
		out.flush();
		s.close();
	}
	
}
