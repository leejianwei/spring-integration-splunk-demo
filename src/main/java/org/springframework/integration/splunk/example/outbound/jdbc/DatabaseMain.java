/*
 * Copyright 2011-2012 the original author or authors.
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
package org.springframework.integration.splunk.example.outbound.jdbc;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.splunk.example.dao.PersonDAO;

/**
 * @author Jarred Li
 * @since 1.0
 * 
 */
public class DatabaseMain {

	public static void main(String args[]) throws InterruptedException {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
				" SpringSplunkShowcaseDatabase-context.xml", DatabaseMain.class);
		ctx.start();

		generateData(ctx);
		Thread.sleep(10000);
		queryData(ctx);
	}

	private static void generateData(ClassPathXmlApplicationContext ctx) {
		PersonDAO dao = ctx.getBean("personDAO", PersonDAO.class);
		dao.generateData(7);
	}

	private static void queryData(ClassPathXmlApplicationContext ctx) {
		PersonDAO dao = ctx.getBean("personDAO", PersonDAO.class);
		dao.queryData();
	}



}
