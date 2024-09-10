package com.twittercasero.middleend.infrastructure.message.producer;

import com.twittercasero.middleend.domain.entities.TweetDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TweetMessageProducerTest {

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @InjectMocks
    private TweetMessageProducer tweetMessageProducer;

    @Test
    void testCreateTweet() {

        TweetDTO tweet = TweetDTO.builder().owner("user123").message("Hello world!").build();
        String expectedMessage = "{\"owner\": \"" + tweet.getOwner() + "\", \"message\": \"" + tweet.getMessage() + "\"}";

        tweetMessageProducer.createTweet(tweet);

        verify(kafkaTemplate).send("new-tweet-topic", expectedMessage);
    }

    @Test
    void testLikeTweet() {
        String tweetId = "12345";
        Boolean like = true;
        String expectedMessage = "{\"like\": \"" + like + "\", \"tweetId\": \"" + tweetId + "\"}";

        tweetMessageProducer.likeTweet(tweetId, like);

        verify(kafkaTemplate).send("add-like-topic", expectedMessage);
    }

    @Test
    void testReplyTweet() {
        String tweetId = "12345";
        String userNickName = "user123";
        String replyMessage = "Nice post!";
        String expectedMessage = "{\"nickName\": \"" + userNickName + "\", \"tweetId\": \"" + tweetId + "\", \"message\": \"" + replyMessage + "\"}";

        tweetMessageProducer.replyTweet(tweetId, userNickName, replyMessage);

        verify(kafkaTemplate).send("add-reply-topic", expectedMessage);
    }

    @Test
    void testRetweet() {
        String tweetId = "12345";
        String userNickName = "user123";
        String expectedMessage = "{\"nickName\": \"" + userNickName + "\", \"tweetId\": \"" + tweetId + "\"}";

        tweetMessageProducer.retweet(tweetId, userNickName);

        verify(kafkaTemplate).send("add-retweet-topic", expectedMessage);
    }
}
