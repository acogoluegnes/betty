/**
 * 
 */
package com.zenika.betty.lifecycle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.mortbay.jetty.Server;

import com.zenika.betty.configuration.Configuration;
import com.zenika.betty.configuration.ConfigurationKey;

/**
 * @author acogoluegnes
 *
 */
public class StopMonitorThread extends Thread {

	private ServerSocket socket;
	private final Server server;

	public StopMonitorThread(Configuration configuration,Server server) {
		this.server = server;
		setDaemon(true);
		setName("BettyStopMonitor");
		try {
			socket = new ServerSocket(
				configuration.getInt(ConfigurationKey.SHUTDOWN_PORT), 
				1,
				InetAddress.getByName("127.0.0.1")
			);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void run() {
		Socket accept;
		try {
			accept = socket.accept();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(accept.getInputStream()));
			reader.readLine();
			if (server.isRunning()) {
				server.stop();
			}
			accept.close();
			socket.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
