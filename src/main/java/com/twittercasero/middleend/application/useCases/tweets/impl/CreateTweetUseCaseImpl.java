package com.twittercasero.middleend.application.useCases.tweets.impl;


import com.twittercasero.middleend.application.port.input.TweetInputPort;
import com.twittercasero.middleend.application.useCases.tweets.CreateTweetUseCase;
import com.twittercasero.middleend.domain.entities.TweetDTO;

public class CreateTweetUseCaseImpl implements CreateTweetUseCase {

    private final TweetInputPort tweetInputPort;

    public CreateTweetUseCaseImpl(TweetInputPort tweetInputPort) {
        this.tweetInputPort = tweetInputPort;
    }

    @Override
    public void accept(TweetDTO tweet) {
        tweetInputPort.createTweet(tweet);
    }
}
