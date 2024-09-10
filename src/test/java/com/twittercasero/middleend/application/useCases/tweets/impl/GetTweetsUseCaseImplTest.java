package com.twittercasero.middleend.application.useCases.tweets.impl;

import com.twittercasero.middleend.application.port.output.TweetOutputPort;
import com.twittercasero.middleend.application.port.output.UserOutputPort;
import com.twittercasero.middleend.domain.entities.TweetDTO;
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
class GetTweetsUseCaseImplTest {

    @Mock
    private TweetOutputPort tweetOutputPort;
    @Mock
    private UserOutputPort userOutputPort;

    @InjectMocks
    private GetTweetsUseCaseImpl getTweetsUseCase;

    @Test
    void testApply() {
        String nickName = "user123";
        List<String> following = List.of("user456", "user789");
        List<TweetDTO> expectedTweets = List.of(TweetDTO.builder().id("1").message("Tweet from user456").owner("user456").build(),
                TweetDTO.builder().id("2").message("Tweet from user789").owner("user789").build());

        when(userOutputPort.findFollow(nickName)).thenReturn(following);
        when(tweetOutputPort.findByOwners(following)).thenReturn(expectedTweets);

        List<TweetDTO> result = getTweetsUseCase.apply(nickName);

        assertEquals(expectedTweets, result);

        verify(userOutputPort).findFollow(nickName);
        verify(tweetOutputPort).findByOwners(following);
    }
}
