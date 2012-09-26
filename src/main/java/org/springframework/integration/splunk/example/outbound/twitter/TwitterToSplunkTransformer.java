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

import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.splunk.entity.SplunkData;
import org.springframework.social.twitter.api.Tweet;

/**
 * @author Jarred Li
 * @since 1.0
 * 
 */
public class TwitterToSplunkTransformer {

	@Transformer
	public SplunkData generateSplunkData(Tweet t) {
		SplunkData data = new SplunkData("twitter-message", "twitter");
		data.addPair("source", t.getSource());
		data.addPair("from_user", t.getFromUser());
		data.addPair("from_user_id", t.getFromUserId());
		data.addPair("created_at", t.getCreatedAt());
		data.addPair("text", t.getText());
		return data;
	}
}
