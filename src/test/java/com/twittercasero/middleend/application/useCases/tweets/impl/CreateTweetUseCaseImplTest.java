package com.twittercasero.middleend.application.useCases.tweets.impl;

import com.twittercasero.middleend.application.port.input.TweetInputPort;
import com.twittercasero.middleend.domain.entities.TweetDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CreateTweetUseCaseImplTest {

    @Mock
    private TweetInputPort tweetInputPort;

    @InjectMocks
    private CreateTweetUseCaseImpl createTweetUseCase;

    @Test
    void testAccept() {
        TweetDTO tweet = TweetDTO.builder().id("1").message("Hello world!").owner("user123").build();

        // Execute the use case
        createTweetUseCase.accept(tweet);

        // Verify that the method createTweet of tweetInputPort is called with the correct tweet
        verify(tweetInputPort).createTweet(tweet);
    }
}
