package com.twittercasero.middleend.application.useCases.tweets;

import java.util.function.BiConsumer;

public interface LikeTweetUseCase extends BiConsumer<String, Boolean> {
}
