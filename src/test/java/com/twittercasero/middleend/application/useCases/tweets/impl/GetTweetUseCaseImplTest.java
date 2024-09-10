package com.twittercasero.middleend.application.useCases.tweets.impl;

import com.twittercasero.middleend.application.port.output.TweetOutputPort;
import com.twittercasero.middleend.domain.entities.TweetDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetTweetUseCaseImplTest {

    @Mock
    private TweetOutputPort tweetOutputPort;

    @InjectMocks
    private GetTweetUseCaseImpl getTweetUseCase;

    @Test
    void testApply() {
        String tweetId = "123";
        TweetDTO expectedTweet = TweetDTO.builder().id(tweetId).message("Hello world!").owner("user123").build();

        when(tweetOutputPort.findById(tweetId)).thenReturn(expectedTweet);

        TweetDTO result = getTweetUseCase.apply(tweetId);

        assertEquals(expectedTweet, result);

        verify(tweetOutputPort).findById(tweetId);
    }
}
