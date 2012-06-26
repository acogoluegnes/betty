# Betty

Betty is a small toolkit to embed a Jetty server inside a Java web archive and make it
runnable as a standalone application (no deployment in a web container or an 
application server).

Betty shines in the following scenarios: quick and easy deployment for staging or 
production environment, deployment on cloud infrastructure like Heroku.

## Maven integration

Configuring Betty in the POM of your web application is straightforward:

	<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
		xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
		<modelVersion>4.0.0</modelVersion>
		<groupId>com.zenika</groupId>
		<artifactId>simple-webapp</artifactId>
		<version>0.1.0</version>
		<!-- JAR packaging -->	
		<packaging>jar</packaging>
	
		<name>simple webapp</name>
	
		<dependencies>
			<!-- Betty dependency -->
			<dependency>
				<groupId>com.zenika</groupId>
				<artifactId>betty-core</artifactId>
				<version>0.1.0</version>
			</dependency>
	
		</dependencies>
		
		<build>
			<resources>
				<resource>
					<directory>${basedir}/src/main/resources</directory>
				</resource>
				<!-- include the web app resources in the build directory -->
				<resource>
					<directory>${basedir}/src/main/webapp</directory>
				</resource>
			</resources>
			<plugins>
				<!-- shade plugin to package everything up in the JAR -->
				<plugin>
					<groupId>org.apache.maven.plugins</groupId>
					<artifactId>maven-shade-plugin</artifactId>
					<version>1.7</version>
					<configuration>
					</configuration>
					<executions>
						<execution>
							<phase>package</phase>
							<goals>
								<goal>shade</goal>
							</goals>
						</execution>
					</executions>
				</plugin>
				<!-- JAR plugin to tweak the manifest -->
				<plugin>
					<groupId>org.apache.maven.plugins</groupId>
					<artifactId>maven-jar-plugin</artifactId>
					<version>2.4</version>
					<configuration>
						<archive>
							<index>true</index>
							<manifest>
								<addClasspath>true</addClasspath>
								<mainClass>com.zenika.betty.Server</mainClass>
							</manifest>
						</archive>
					</configuration>
				</plugin>
			</plugins>
		</build>
	
	</project>
	
You can then package the JAR :

	mvn clean package
	
And launch it:

	java -jar target/simple-webapp-0.1.0.jar
	
To stop:

	java -jar target/simple-webapp-0.1.0.jar stop

## Jetty configuration

TBD

## Logging

TDB

