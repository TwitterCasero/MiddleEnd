package com.twittercasero.middleend.application.useCases.tweets.impl;

import com.twittercasero.middleend.application.port.input.TweetInputPort;
import com.twittercasero.middleend.application.useCases.tweets.LikeTweetUseCase;

public class LikeTweetUseCaseImpl implements LikeTweetUseCase {

    private final TweetInputPort tweetInputPort;

    public LikeTweetUseCaseImpl(TweetInputPort tweetInputPort) {
        this.tweetInputPort = tweetInputPort;
    }

    @Override
    public void accept(String tweetId, Boolean liked) {
        tweetInputPort.likeTweet(tweetId, liked);
    }
}
