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
package org.springframework.integration.splunk.example.inbound;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.integration.Message;
import org.springframework.integration.annotation.Splitter;
import org.springframework.integration.splunk.entity.SplunkData;

/**
 * @author Jarred Li
 * @since 1.0
 * 
 */
public class SplunkToPersonSplitter {

	/* (non-Javadoc)
	 * Splunk event data raw value is like this:
	 * key=_raw,value=2012-09-25 14:57:58:472+0800 name="person-table-message" event_id="person-table" person-id="1" person-name="test-data"
	 * @see org.springframework.integration.splitter.AbstractMessageSplitter#splitMessage(org.springframework.integration.Message)
	 */
	@Splitter
	public List<Map<String, Object>> splitMessage(Message<?> message) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		@SuppressWarnings("unchecked")
		List<SplunkData> splunkData = (List<SplunkData>) message.getPayload();
		for (SplunkData sd : splunkData) {
			Map<String, Object> data = new HashMap<String, Object>();
			Map<String, String> eventData = sd.getEventData();
			String raw = eventData.get("_raw");
			String[] keyValues = raw.split(" ");
			for (String kv : keyValues) {
				String[] splittedKV = kv.split("=");
				if (splittedKV[0].equals("person-id")) {
					data.put("personId", Integer.parseInt(splittedKV[1].substring(1, splittedKV[1].length() - 1)));
				}
				if (splittedKV[0].equals("person-name")) {
					data.put("personName", splittedKV[1]);
				}
			}
			result.add(data);
		}
		return result;
	}

}
