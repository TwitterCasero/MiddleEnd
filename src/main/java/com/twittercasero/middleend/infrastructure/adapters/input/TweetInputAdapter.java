package com.twittercasero.middleend.infrastructure.adapters.input;

import com.twittercasero.middleend.application.port.input.TweetInputPort;
import com.twittercasero.middleend.domain.entities.TweetDTO;
import com.twittercasero.middleend.infrastructure.message.producer.TweetMessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TweetInputAdapter implements TweetInputPort {

    @Autowired
    private TweetMessageProducer messageProducer;

    @Override
    public void createTweet(TweetDTO tweet) {
        messageProducer.createTweet(tweet);
    }

    @Override
    public void likeTweet(String tweetId, Boolean liked) {
        messageProducer.likeTweet(tweetId, liked);
    }

    @Override
    public void reTweet(String tweetId, String userNickName) {
        messageProducer.retweet(tweetId, userNickName);
    }

    @Override
    public void replyTweet(String tweetId, String userNickName, String replyMessage) {
        messageProducer.replyTweet(tweetId, userNickName, replyMessage);
    }
}
