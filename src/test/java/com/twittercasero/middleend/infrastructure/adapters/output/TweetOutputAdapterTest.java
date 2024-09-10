package com.twittercasero.middleend.infrastructure.adapters.output;

import com.twittercasero.middleend.domain.entities.TweetDTO;
import com.twittercasero.middleend.infrastructure.restclient.TweetRestServiceClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TweetOutputAdapterTest {

    @Mock
    private TweetRestServiceClient tweetRestServiceClient;

    @InjectMocks
    private TweetOutputAdapter tweetOutputAdapter;

    @Test
    void testFindById() {
        String tweetId = "123";
        TweetDTO expectedTweet = TweetDTO.builder().id(tweetId).owner("user123").message("Hello World!").build();

        when(tweetRestServiceClient.getTweetById(tweetId)).thenReturn(expectedTweet);

        TweetDTO result = tweetOutputAdapter.findById(tweetId);

        assertEquals(expectedTweet, result);
        verify(tweetRestServiceClient).getTweetById(tweetId);
    }

    @Test
    void testFindByOwners() {
        List<String> owners = List.of("user123", "user456");

        List<TweetDTO> expectedTweets = List.of(TweetDTO.builder().id("1").owner("user123").message("Tweet 1").build(),
                TweetDTO.builder().id("2").owner("user456").message("Tweet 2").build());
        when(tweetRestServiceClient.getTweetsByOwners(owners, 1, 25)).thenReturn(expectedTweets);

        List<TweetDTO> result = tweetOutputAdapter.findByOwners(owners);

        assertEquals(expectedTweets, result);
        verify(tweetRestServiceClient).getTweetsByOwners(owners, 1, 25);
    }

    @Test
    void testFindByHashtag() {
        String hashtag = "funny";
        List<TweetDTO> expectedTweets = List.of(TweetDTO.builder().id("3").owner("user789").message("Tweet 3").build());
        when(tweetRestServiceClient.getTweetsByHashtag(hashtag, 1, 25)).thenReturn(expectedTweets);

        List<TweetDTO> result = tweetOutputAdapter.findByHashtag(hashtag);

        assertEquals(expectedTweets, result);
        verify(tweetRestServiceClient).getTweetsByHashtag(hashtag, 1, 25);
    }

    @Test
    void testFindByMention() {
        String mention = "user999";
        List<TweetDTO> expectedTweets = List.of(TweetDTO.builder().id("4").owner("user999").message("Tweet 4").build());
        when(tweetRestServiceClient.getTweetsByMention(mention, 1, 25)).thenReturn(expectedTweets);

        List<TweetDTO> result = tweetOutputAdapter.findByMention(mention);

        assertEquals(expectedTweets, result);
        verify(tweetRestServiceClient).getTweetsByMention(mention, 1, 25);
    }
}
