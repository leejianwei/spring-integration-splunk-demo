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
package org.springframework.integration.splunk.example.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.integration.splunk.example.outbound.jdbc.PersonRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author Jarred Li
 * @since 1.0
 * 
 */
public class PersonDAO {

	private static int count = 1;

	private JdbcTemplate template;

	public void setDataSource(DataSource ds) {
		this.template = new JdbcTemplate(ds);
	}

	public void generateData(int a) {
		for (int i = 0; i < a; i++) {
			template.execute("insert into data(id,name,status) values(" + count++ + ", 'test-data',0)");
		}
	}

	public void queryData() {
		List<Person> data = template.query("select id, name,status from data", new PersonRowMapper());
		for (Person d : data) {
			System.out.println("data: id=" + d.getId() + ", name=" + d.getName() + ", status=" + d.getStatus());
		}

		System.out.println("total record:" + template.queryForInt("select count(id) from data"));

	}

}
