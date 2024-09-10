package com.twittercasero.middleend.infrastructure.restclient;

import com.twittercasero.middleend.domain.entities.TweetDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TweetRestServiceClientTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private TweetRestServiceClient tweetRestServiceClient;

    @BeforeEach
    void setUp() {
        tweetRestServiceClient.tweetServiceUrl = "http://example.com";
    }

    @Test
    void testGetTweetsByOwners() {
        List<String> owners = List.of("user1", "user2");
        List<TweetDTO> expectedTweets = List.of(TweetDTO.builder().message("Content 1").build(), TweetDTO.builder().message("Content 2").build());
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://example.com/tweets")
                .queryParam("owners", String.join(",", owners))
                .queryParam("page", 0)
                .queryParam("size", 5);

        when(restTemplate.exchange(
                eq(uriBuilder.toUriString()),
                eq(HttpMethod.GET),
                any(),
                any(ParameterizedTypeReference.class)
        )).thenReturn(ResponseEntity.ok(expectedTweets));

        List<TweetDTO> result = tweetRestServiceClient.getTweetsByOwners(owners, 0, 5);

        assertEquals(expectedTweets, result);
        verify(restTemplate).exchange(eq(uriBuilder.toUriString()), eq(HttpMethod.GET), any(), any(ParameterizedTypeReference.class));
    }

    @Test
    void testGetTweetById() {
        TweetDTO expectedTweet = TweetDTO.builder().message("Content 1").build();
        String tweetId = "123";
        when(restTemplate.getForEntity(
                "http://example.com/tweets/" + tweetId,
                TweetDTO.class
        )).thenReturn(ResponseEntity.ok(expectedTweet));

        TweetDTO result = tweetRestServiceClient.getTweetById(tweetId);

        assertEquals(expectedTweet, result);
        verify(restTemplate).getForEntity("http://example.com/tweets/" + tweetId, TweetDTO.class);
    }

    @Test
    void testGetTweetsByMention() {
        List<TweetDTO> expectedTweets = List.of(TweetDTO.builder().message("Content 1").build());
        String nickname = "user1";
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://example.com/tweets/" + nickname + "/mentions")
                .queryParam("page", 0)
                .queryParam("size", 5);

        when(restTemplate.exchange(
                eq(uriBuilder.toUriString()),
                eq(HttpMethod.GET),
                any(),
                any(ParameterizedTypeReference.class)
        )).thenReturn(ResponseEntity.ok(expectedTweets));

        List<TweetDTO> result = tweetRestServiceClient.getTweetsByMention(nickname, 0, 5);

        assertEquals(expectedTweets, result);
        verify(restTemplate).exchange(eq(uriBuilder.toUriString()), eq(HttpMethod.GET), any(), any(ParameterizedTypeReference.class));
    }

    @Test
    void testGetTweetsByHashtag() {
        List<TweetDTO> expectedTweets = List.of(TweetDTO.builder().message("Content 1").build());
        String hashtag = "tech";
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://example.com/tweets/" + hashtag + "/hashtag")
                .queryParam("page", 0)
                .queryParam("size", 5);

        when(restTemplate.exchange(
                eq(uriBuilder.toUriString()),
                eq(HttpMethod.GET),
                any(),
                any(ParameterizedTypeReference.class)
        )).thenReturn(ResponseEntity.ok(expectedTweets));

        List<TweetDTO> result = tweetRestServiceClient.getTweetsByHashtag(hashtag, 0, 5);

        assertEquals(expectedTweets, result);
        verify(restTemplate).exchange(eq(uriBuilder.toUriString()), eq(HttpMethod.GET), any(), any(ParameterizedTypeReference.class));
    }
}
