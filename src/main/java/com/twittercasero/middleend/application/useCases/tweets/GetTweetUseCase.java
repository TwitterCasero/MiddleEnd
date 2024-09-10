package com.twittercasero.middleend.application.useCases.tweets;

import com.twittercasero.middleend.domain.entities.TweetDTO;

import java.util.function.Function;

public interface GetTweetUseCase extends Function<String, TweetDTO> {
}
