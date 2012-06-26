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
 * 
 * @author Arnaud Cogoluègnes
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
