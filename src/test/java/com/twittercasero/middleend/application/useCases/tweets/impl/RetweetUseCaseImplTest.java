package com.twittercasero.middleend.application.useCases.tweets.impl;

import com.twittercasero.middleend.application.port.input.TweetInputPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RetweetUseCaseImplTest {

    @Mock
    private TweetInputPort tweetInputPort;

    @InjectMocks
    private RetweetUseCaseImpl retweetUseCase;

    @Test
    void testAccept() {
        String tweetId = "123";
        String userNickName = "user456";

        retweetUseCase.accept(tweetId, userNickName);

        verify(tweetInputPort).reTweet(tweetId, userNickName);
    }
}
