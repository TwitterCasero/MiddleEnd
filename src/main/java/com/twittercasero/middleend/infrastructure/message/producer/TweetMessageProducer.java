package com.twittercasero.middleend.infrastructure.message.producer;

import com.twittercasero.middleend.domain.entities.TweetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TweetMessageProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    public void createTweet(TweetDTO tweet) {
        String message = "{\"owner\": \"" + tweet.getOwner() + "\", \"message\": \"" + tweet.getMessage() + "\"}";
        kafkaTemplate.send("new-tweet-topic", message);
    }

    public void likeTweet(String tweetId, Boolean like) {
        String message = "{\"like\": \"" + like + "\", \"tweetId\": \"" + tweetId + "\"}";
        kafkaTemplate.send("add-like-topic", message);
    }

    public void replyTweet(String tweetId, String userNickName, String replyMessage) {
        String message = "{\"nickName\": \"" + userNickName + "\", \"tweetId\": \"" + tweetId + "\", \"message\": \"" + replyMessage + "\"}";
        kafkaTemplate.send("add-reply-topic", message);
    }

    public void retweet(String tweetId, String userNickName) {
        String message = "{\"nickName\": \"" + userNickName + "\", \"tweetId\": \"" + tweetId + "\"}";
        kafkaTemplate.send("add-retweet-topic", message);
    }
}
