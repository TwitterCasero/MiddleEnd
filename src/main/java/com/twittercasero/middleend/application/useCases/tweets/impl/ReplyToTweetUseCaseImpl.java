package com.twittercasero.middleend.application.useCases.tweets.impl;

import com.twittercasero.middleend.application.port.input.TweetInputPort;
import com.twittercasero.middleend.application.useCases.tweets.ReplyToTweetUseCase;

public class ReplyToTweetUseCaseImpl implements ReplyToTweetUseCase {

    private final TweetInputPort tweetInputPort;

    public ReplyToTweetUseCaseImpl(TweetInputPort tweetInputPort) {
        this.tweetInputPort = tweetInputPort;
    }

    @Override
    public void accept(String tweetId, String userNickName, String replyMessage) {
        tweetInputPort.replyTweet(tweetId, userNickName, replyMessage);
    }
}
