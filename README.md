Development
===================================
### to generate eclipse project:
1. git clone https://github.com/leejianwei/spring-integration-splunk-demo.git
2. change to "spring-integration-splunk-demo" dir
3. ./gradlew eclipse
	
### to build project:
	./gradlew build

Download and install Splunk
=====================================

### Splunk Server

You need to install or have access to a Splunk Server. You can download Splunk from:

http://www.splunk.com/download


### Splunk SDK

You can get more information on Splunk from:

http://dev.splunk.com/


Create Twitter Application
=====================================
1. got to https://dev.twitter.com
2. click "create an app"
3. fill all the data to generate twitter app
4. update "src/main/resources/twitter.properties" to your twitter application
5. update Splunk server info in the "src/main/resources/org/springframework/integration/splunk/example/SplunkCommon-context.xml"
6. run the main class:

### Twitter into Splunk: Get Tweet and push to Splunk with outbound channel adapter

	src/main/java/org/springframework/integration/splunk/example/outbound/twitter/TwitterMain.java
	src/main/resources/org/springframework/integration/splunk/example/outbound/twitter/SpringSplunkShowcaseTwitter-context.xml
	
change the "fromUsers" properties to the user name of your followers whose tweet will be pushed to Splunk
~~~~~xml
	<bean id="fromUserSelector"
		class="org.springframework.integration.splunk.example.outbound.twitter.FromUserSelector">
		<property name="fromUsers">
			<list>
				<value>AP</value>
				<value>nytimes</value>
			</list>
		</property>
	</bean>
~~~~~
	
### Database into Splunk: Get data from Database and push to Splunk with outbound channel adapter

	src/main/java/org/springframework/integration/splunk/example/outbound/jdbc/DatabaseMain.java
	
### Read data from Splunk: Read data from Splunk with inbound channel adapter, persist the data into Database.

	src/main/java/org/springframework/integration/splunk/example/inbound/SplunkMain.java
	



