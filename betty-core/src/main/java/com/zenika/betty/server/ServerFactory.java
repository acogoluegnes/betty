/**
 * 
 */
package com.zenika.betty.server;

import org.mortbay.jetty.Server;

import com.zenika.betty.configuration.Configuration;

/**
 * @author acogoluegnes
 *
 */
public interface ServerFactory {

	Server createServer(Configuration configuration);
	
}
