/*
 * 
 */
package com.zenika.betty;

import com.zenika.betty.configuration.Configuration;
import com.zenika.betty.configuration.support.PropertyFileConfiguration;

/**
 * 
 * @author acogoluegnes
 *
 */
public class Server {

	public static void main(String ... args) {
		// TODO use Java service extension to find the configuration impl to use
		Configuration configuration = new PropertyFileConfiguration();
		
		
	}
	
}
