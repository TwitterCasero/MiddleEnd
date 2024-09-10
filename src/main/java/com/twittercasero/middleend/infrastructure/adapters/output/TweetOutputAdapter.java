package com.twittercasero.middleend.infrastructure.adapters.output;

import com.twittercasero.middleend.application.port.output.TweetOutputPort;
import com.twittercasero.middleend.domain.entities.TweetDTO;
import com.twittercasero.middleend.infrastructure.restclient.TweetRestServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TweetOutputAdapter implements TweetOutputPort {

    @Autowired
    private TweetRestServiceClient tweetRestServiceClient;

    @Override
    public TweetDTO findById(String tweetId) {
        return tweetRestServiceClient.getTweetById(tweetId);
    }

    @Override
    public List<TweetDTO> findByOwners(List<String> owners) {
        return tweetRestServiceClient.getTweetsByOwners(owners, 1, 25);
    }

    @Override
    public List<TweetDTO> findByHashtag(String hashtag) {
        return tweetRestServiceClient.getTweetsByHashtag(hashtag, 1, 25);
    }

    @Override
    public List<TweetDTO> findByMention(String mention) {
        return tweetRestServiceClient.getTweetsByMention(mention, 1, 25);
    }

}

