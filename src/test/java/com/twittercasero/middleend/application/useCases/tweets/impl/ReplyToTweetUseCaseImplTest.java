package com.twittercasero.middleend.application.useCases.tweets.impl;

import com.twittercasero.middleend.application.port.input.TweetInputPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ReplyToTweetUseCaseImplTest {

    @Mock
    private TweetInputPort tweetInputPort;

    @InjectMocks
    private ReplyToTweetUseCaseImpl replyToTweetUseCase;

    @Test
    void testAccept() {
        String tweetId = "123";
        String userNickName = "user456";
        String replyMessage = "Great tweet!";

        replyToTweetUseCase.accept(tweetId, userNickName, replyMessage);

        verify(tweetInputPort).replyTweet(tweetId, userNickName, replyMessage);
    }
}
