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

import java.util.ArrayList;

import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.splunk.entity.SplunkData;
import org.springframework.integration.splunk.example.dao.Person;

/**
 * @author Jarred Li
 * @since 1.0
 * 
 */
public class PersonToSplunkTransformer {

	@Transformer
	public SplunkData generateSplunkData(ArrayList<Person> persons) {
		System.out.println("person transformer");
		SplunkData result = new SplunkData("person-table-message", "person-table");
		Person p = persons.get(0);
		result.addPair("person-id", p.getId());
		result.addPair("person-name", p.getName());
		return result;
	}
}
