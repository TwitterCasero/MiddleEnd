package com.twittercasero.middleend.infrastructure.controllers;

import com.twittercasero.middleend.application.useCases.tweets.CreateTweetUseCase;
import com.twittercasero.middleend.application.useCases.tweets.GetTweetUseCase;
import com.twittercasero.middleend.application.useCases.tweets.GetTweetsUseCase;
import com.twittercasero.middleend.application.useCases.tweets.LikeTweetUseCase;
import com.twittercasero.middleend.application.useCases.tweets.ReplyToTweetUseCase;
import com.twittercasero.middleend.application.useCases.tweets.RetweetUseCase;
import com.twittercasero.middleend.domain.entities.TweetDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class TweetControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CreateTweetUseCase createTweetUseCase;
    @Mock
    private GetTweetUseCase getTweetUseCase;
    @Mock
    private GetTweetsUseCase getTweetsUseCase;
    @Mock
    private LikeTweetUseCase likeTweetUseCase;
    @Mock
    private RetweetUseCase retweetUseCase;
    @Mock
    private ReplyToTweetUseCase replyToTweetUseCase;

    @InjectMocks
    private TweetController tweetController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(tweetController).build();
    }

    @Test
    void testCreateTweet() throws Exception {
        mockMvc.perform(post("/tweets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"message\":\"Hello World!\"}")
                        .header("UserId", "user123"))
                .andExpect(status().isOk());

        verify(createTweetUseCase).accept(any(TweetDTO.class));
    }

    @Test
    void testGetTweet() throws Exception {
        TweetDTO tweet = TweetDTO.builder().id("1").message("Hello world!").owner("user123").build();
        when(getTweetUseCase.apply("1")).thenReturn(tweet);

        mockMvc.perform(get("/tweets/1")
                        .header("UserId", "user123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.owner").value("user123"));

        verify(getTweetUseCase).apply("1");
    }

    @Test
    void testGetTweets() throws Exception {
        List<TweetDTO> tweets = List.of(TweetDTO.builder().id("1").message("Hello world!").owner("user123").build());
        when(getTweetsUseCase.apply("user123")).thenReturn(tweets);

        mockMvc.perform(get("/tweets")
                        .header("UserId", "user123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].owner").value("user123"));

        verify(getTweetsUseCase).apply("user123");
    }

    @Test
    void testLikeTweet() throws Exception {
        mockMvc.perform(patch("/tweets/1/liked")
                        .header("UserId", "user123"))
                .andExpect(status().isOk());

        verify(likeTweetUseCase).accept("1", true);
    }

    @Test
    void testUnLikeTweet() throws Exception {
        mockMvc.perform(patch("/tweets/1/unliked")
                        .header("UserId", "user123"))
                .andExpect(status().isOk());

        verify(likeTweetUseCase).accept("1", false);
    }

    @Test
    void testRetweet() throws Exception {
        mockMvc.perform(patch("/tweets/1/retweet")
                        .header("UserId", "user123"))
                .andExpect(status().isOk());

        verify(retweetUseCase).accept("1", "user123");
    }

    @Test
    void testReplyToTweet() throws Exception {
        mockMvc.perform(patch("/tweets/1/reply")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"message\":\"Reply message\"}")
                        .header("UserId", "user123"))
                .andExpect(status().isOk());

        verify(replyToTweetUseCase).accept("1", "user123", "Reply message");
    }
}
