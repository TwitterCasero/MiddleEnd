package com.twittercasero.middleend.application.useCases.tweets.impl;

import com.twittercasero.middleend.application.port.output.TweetOutputPort;
import com.twittercasero.middleend.application.useCases.tweets.GetTweetUseCase;
import com.twittercasero.middleend.domain.entities.TweetDTO;

public class GetTweetUseCaseImpl implements GetTweetUseCase {

    private final TweetOutputPort tweetOutputPort;

    public GetTweetUseCaseImpl(TweetOutputPort tweetOutputPort) {
        this.tweetOutputPort = tweetOutputPort;
    }

    @Override
    public TweetDTO apply(String tweetId) {
        return tweetOutputPort.findById(tweetId);
    }
}
