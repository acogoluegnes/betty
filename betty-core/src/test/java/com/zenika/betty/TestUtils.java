/**
 * 
 */
package com.zenika.betty;

import java.io.IOException;
import java.net.ServerSocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author acogoluegnes
 *
 */
public class TestUtils {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TestUtils.class);

	public static int getAvailablePort() {
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(0);
			return ss.getLocalPort();
		} catch (IOException e) {
			LOGGER.error("couldn't open server socket",e);
		} finally {
			if(ss != null && !ss.isClosed()) {
				try {
					ss.close();
				} catch (IOException e) {
					LOGGER.error("couldn't close server socket",e);
				}
			}
		}
		return -1;
	}
	
}
