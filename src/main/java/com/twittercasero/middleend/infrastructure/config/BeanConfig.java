package com.twittercasero.middleend.infrastructure.config;

import com.twittercasero.middleend.application.port.input.TweetInputPort;
import com.twittercasero.middleend.application.port.input.UserInputPort;
import com.twittercasero.middleend.application.port.output.TweetOutputPort;
import com.twittercasero.middleend.application.port.output.UserOutputPort;
import com.twittercasero.middleend.application.useCases.tweets.CreateTweetUseCase;
import com.twittercasero.middleend.application.useCases.tweets.GetTweetUseCase;
import com.twittercasero.middleend.application.useCases.tweets.GetTweetsUseCase;
import com.twittercasero.middleend.application.useCases.tweets.LikeTweetUseCase;
import com.twittercasero.middleend.application.useCases.tweets.ReplyToTweetUseCase;
import com.twittercasero.middleend.application.useCases.tweets.RetweetUseCase;
import com.twittercasero.middleend.application.useCases.tweets.impl.CreateTweetUseCaseImpl;
import com.twittercasero.middleend.application.useCases.tweets.impl.GetTweetUseCaseImpl;
import com.twittercasero.middleend.application.useCases.tweets.impl.GetTweetsUseCaseImpl;
import com.twittercasero.middleend.application.useCases.tweets.impl.LikeTweetUseCaseImpl;
import com.twittercasero.middleend.application.useCases.tweets.impl.ReplyToTweetUseCaseImpl;
import com.twittercasero.middleend.application.useCases.tweets.impl.RetweetUseCaseImpl;
import com.twittercasero.middleend.application.useCases.users.BlockUserUseCase;
import com.twittercasero.middleend.application.useCases.users.CreateUserUseCase;
import com.twittercasero.middleend.application.useCases.users.FollowUserUseCase;
import com.twittercasero.middleend.application.useCases.users.GetUserUseCase;
import com.twittercasero.middleend.application.useCases.users.UnFollowUserUseCase;
import com.twittercasero.middleend.application.useCases.users.impl.BlockUserUseCaseImpl;
import com.twittercasero.middleend.application.useCases.users.impl.CreateUserUseCaseImpl;
import com.twittercasero.middleend.application.useCases.users.impl.FollowUserUseCaseImpl;
import com.twittercasero.middleend.application.useCases.users.impl.GetUserUseCaseImpl;
import com.twittercasero.middleend.application.useCases.users.impl.UnFollowUserUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    CreateTweetUseCase createTweetUseCase(TweetInputPort tweetInputPort) {
        return new CreateTweetUseCaseImpl(tweetInputPort);
    }

    @Bean
    GetTweetsUseCase getTweetsUseCase(TweetOutputPort tweetOutputPort, UserOutputPort userOutputPort) {
        return new GetTweetsUseCaseImpl(tweetOutputPort, userOutputPort);
    }

    @Bean
    GetTweetUseCase getTweetUseCase(TweetOutputPort tweetOutputPort) {
        return new GetTweetUseCaseImpl(tweetOutputPort);
    }

    @Bean
    LikeTweetUseCase likeTweetUseCase(TweetInputPort tweetInputPort) {
        return new LikeTweetUseCaseImpl(tweetInputPort);
    }

    @Bean
    ReplyToTweetUseCase replyToTweetUseCase(TweetInputPort tweetInputPort) {
        return new ReplyToTweetUseCaseImpl(tweetInputPort);
    }

    @Bean
    RetweetUseCase retweetUseCase(TweetInputPort tweetInputPort) {
        return new RetweetUseCaseImpl(tweetInputPort);
    }

    @Bean
    BlockUserUseCase blockUserUseCase(UserInputPort userInputPort) {
        return new BlockUserUseCaseImpl(userInputPort);
    }

    @Bean
    CreateUserUseCase createUserUseCase(UserInputPort userInputPort) {
        return new CreateUserUseCaseImpl(userInputPort);
    }

    @Bean
    FollowUserUseCase followUserUseCase(UserInputPort userInputPort) {
        return new FollowUserUseCaseImpl(userInputPort);
    }

    @Bean
    GetUserUseCase getUserUseCase(UserOutputPort userOutputPort) {
        return new GetUserUseCaseImpl(userOutputPort);
    }

    @Bean
    UnFollowUserUseCase unFollowUserUseCase(UserInputPort userInputPort) {
        return new UnFollowUserUseCaseImpl(userInputPort);
    }

}
