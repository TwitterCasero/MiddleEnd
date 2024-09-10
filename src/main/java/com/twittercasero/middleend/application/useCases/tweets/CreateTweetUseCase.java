package com.twittercasero.middleend.application.useCases.tweets;

import com.twittercasero.middleend.domain.entities.TweetDTO;

import java.util.function.Consumer;

public interface CreateTweetUseCase extends Consumer<TweetDTO> {
}
