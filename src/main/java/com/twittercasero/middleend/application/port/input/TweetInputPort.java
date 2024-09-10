package com.twittercasero.middleend.application.port.input;

import com.twittercasero.middleend.domain.entities.TweetDTO;

public interface TweetInputPort {
    void createTweet(TweetDTO tweet);

    void likeTweet(String tweetId, Boolean liked);

    void reTweet(String tweetId, String userNickName);

    void replyTweet(String tweetId, String userNickName, String replyMessage);
}
