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
package org.springframework.integration.splunk.example.outbound.twitter;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.integration.Message;
import org.springframework.integration.core.MessageSelector;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.util.Assert;

/**
 * @author Jarred Li
 * @since 1.0
 * 
 */
public class FromUserSelector implements MessageSelector, InitializingBean {

	private List<String> fromUsers;

	public List<String> getFromUsers() {
		return fromUsers;
	}

	public void setFromUsers(List<String> fromUsers) {
		this.fromUsers = fromUsers;
	}

	/* (non-Javadoc)
	 * @see org.springframework.integration.core.MessageSelector#accept(org.springframework.integration.Message)
	 */
	@Override
	public boolean accept(Message<?> message) {
		Tweet t = (Tweet) message.getPayload();
		String from = t.getFromUser();
		if (fromUsers.contains(from)) {
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(fromUsers, "you must specify the users which you are intrested in");
	}

}
