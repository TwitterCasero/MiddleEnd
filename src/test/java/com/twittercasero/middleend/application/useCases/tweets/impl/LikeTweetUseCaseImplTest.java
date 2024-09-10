package com.twittercasero.middleend.application.useCases.tweets.impl;

import com.twittercasero.middleend.application.port.input.TweetInputPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LikeTweetUseCaseImplTest {

    @Mock
    private TweetInputPort tweetInputPort;

    @InjectMocks
    private LikeTweetUseCaseImpl likeTweetUseCase;

    @Test
    void testAccept() {
        String tweetId = "123";
        Boolean liked = true;

        likeTweetUseCase.accept(tweetId, liked);

        verify(tweetInputPort).likeTweet(tweetId, liked);
    }

    @Test
    void testUnLike() {
        String tweetId = "123";
        Boolean liked = false;

        likeTweetUseCase.accept(tweetId, liked);

        verify(tweetInputPort).likeTweet(tweetId, liked);
    }
}
