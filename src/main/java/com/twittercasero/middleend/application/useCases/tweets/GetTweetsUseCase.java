package com.twittercasero.middleend.application.useCases.tweets;

import com.twittercasero.middleend.domain.entities.TweetDTO;

import java.util.List;
import java.util.function.Function;

public interface GetTweetsUseCase extends Function<String, List<TweetDTO>> {
}
