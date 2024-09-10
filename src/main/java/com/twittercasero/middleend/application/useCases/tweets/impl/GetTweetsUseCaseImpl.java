package com.twittercasero.middleend.application.useCases.tweets.impl;

import com.twittercasero.middleend.application.port.output.TweetOutputPort;
import com.twittercasero.middleend.application.port.output.UserOutputPort;
import com.twittercasero.middleend.application.useCases.tweets.GetTweetsUseCase;
import com.twittercasero.middleend.domain.entities.TweetDTO;

import java.util.List;

public class GetTweetsUseCaseImpl implements GetTweetsUseCase {

    private final TweetOutputPort tweetOutputPort;

    private final UserOutputPort userOutputPort;

    public GetTweetsUseCaseImpl(TweetOutputPort tweetOutputPort, UserOutputPort userOutputPort) {
        this.tweetOutputPort = tweetOutputPort;
        this.userOutputPort = userOutputPort;
    }


    @Override
    public List<TweetDTO> apply(String nickName) {

        List<String> following = userOutputPort.findFollow(nickName);

        return tweetOutputPort.findByOwners(following);
    }
}
