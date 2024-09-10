package com.twittercasero.middleend.infrastructure.adapters.input;

import com.twittercasero.middleend.domain.entities.TweetDTO;
import com.twittercasero.middleend.infrastructure.message.producer.TweetMessageProducer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TweetInputAdapterTest {

    @Mock
    private TweetMessageProducer messageProducer;

    @InjectMocks
    private TweetInputAdapter tweetInputAdapter;

    @Test
    void testCreateTweet() {
        TweetDTO tweet = TweetDTO.builder().id("tweetId").message("Hello world!").owner("user123").build();

        tweetInputAdapter.createTweet(tweet);

        verify(messageProducer).createTweet(tweet);
    }

    @Test
    void testLikeTweet() {
        String tweetId = "123";
        Boolean liked = true;

        tweetInputAdapter.likeTweet(tweetId, liked);

        verify(messageProducer).likeTweet(tweetId, liked);
    }

    @Test
    void testReTweet() {
        String tweetId = "123";
        String userNickName = "user456";

        tweetInputAdapter.reTweet(tweetId, userNickName);

        verify(messageProducer).retweet(tweetId, userNickName);
    }

    @Test
    void testReplyTweet() {
        String tweetId = "123";
        String userNickName = "user456";
        String replyMessage = "Nice post!";

        tweetInputAdapter.replyTweet(tweetId, userNickName, replyMessage);

        verify(messageProducer).replyTweet(tweetId, userNickName, replyMessage);
    }
}
