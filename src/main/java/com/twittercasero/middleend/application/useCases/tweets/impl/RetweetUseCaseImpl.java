package com.twittercasero.middleend.application.useCases.tweets.impl;

import com.twittercasero.middleend.application.port.input.TweetInputPort;
import com.twittercasero.middleend.application.useCases.tweets.RetweetUseCase;

public class RetweetUseCaseImpl implements RetweetUseCase {

    private final TweetInputPort tweetInputPort;

    public RetweetUseCaseImpl(TweetInputPort tweetInputPort) {
        this.tweetInputPort = tweetInputPort;
    }

    @Override
    public void accept(String tweetId, String userNickName) {
        tweetInputPort.reTweet(tweetId, userNickName);
    }
}
