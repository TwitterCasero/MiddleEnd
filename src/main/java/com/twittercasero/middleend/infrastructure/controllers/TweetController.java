package com.twittercasero.middleend.infrastructure.controllers;

import com.twittercasero.middleend.application.useCases.tweets.CreateTweetUseCase;
import com.twittercasero.middleend.application.useCases.tweets.GetTweetUseCase;
import com.twittercasero.middleend.application.useCases.tweets.GetTweetsUseCase;
import com.twittercasero.middleend.application.useCases.tweets.LikeTweetUseCase;
import com.twittercasero.middleend.application.useCases.tweets.ReplyToTweetUseCase;
import com.twittercasero.middleend.application.useCases.tweets.RetweetUseCase;
import com.twittercasero.middleend.domain.entities.TweetDTO;
import com.twittercasero.middleend.infrastructure.entities.CreateMessage;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tweets")
public class TweetController {

    @Autowired
    private CreateTweetUseCase createTweetUseCase;
    @Autowired
    private GetTweetUseCase getTweetUseCase;
    @Autowired
    private GetTweetsUseCase getTweetsUseCase;
    @Autowired
    private LikeTweetUseCase likeTweetUseCase;
    @Autowired
    private RetweetUseCase retweetUseCase;
    @Autowired
    private ReplyToTweetUseCase replyToTweetUseCase;

    @PostMapping
    public ResponseEntity<Void> createTweet(@RequestBody @Valid CreateMessage createMessage,
                                            @RequestHeader("UserId") String userId) {

        createTweetUseCase.accept(TweetDTO.builder()
                .owner(userId)
                .message(createMessage.getMessage())
                .build());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{tweetId}")
    public ResponseEntity<TweetDTO> getTweet(@PathVariable String tweetId,
                                             @RequestHeader("UserId") String userId) {
        TweetDTO tweet = getTweetUseCase.apply(tweetId);
        return ResponseEntity.ok(tweet);
    }

    @GetMapping()
    public ResponseEntity<List<TweetDTO>> getTweets(@RequestHeader("UserId") String userId) {
        List<TweetDTO> tweet = getTweetsUseCase.apply(userId);
        return ResponseEntity.ok(tweet);
    }

    @PatchMapping("/{tweetId}/liked")
    public ResponseEntity<Void> likeTweet(@PathVariable String tweetId,
                                          @RequestHeader("UserId") String userId) {
        likeTweetUseCase.accept(tweetId, true);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{tweetId}/unliked")
    public ResponseEntity<Void> unLikeTweet(@PathVariable String tweetId,
                                            @RequestHeader("UserId") String userId) {
        likeTweetUseCase.accept(tweetId, false);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{tweetId}/retweet")
    public ResponseEntity<Void> retweet(@PathVariable String tweetId,
                                        @RequestHeader("UserId") String userId) {
        retweetUseCase.accept(tweetId, userId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{tweetId}/reply")
    public ResponseEntity<Void> replyToTweet(@PathVariable String tweetId, @RequestBody @Valid CreateMessage reply,
                                             @RequestHeader("UserId") String userId) {
        replyToTweetUseCase.accept(tweetId, userId, reply.getMessage());
        return ResponseEntity.ok().build();
    }
}
