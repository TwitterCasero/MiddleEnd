package com.twittercasero.middleend.application.useCases.tweets;

import org.apache.logging.log4j.util.TriConsumer;

public interface ReplyToTweetUseCase extends TriConsumer<String, String, String> {
}
