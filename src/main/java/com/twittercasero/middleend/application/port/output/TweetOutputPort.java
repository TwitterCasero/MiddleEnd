package com.twittercasero.middleend.application.port.output;

import com.twittercasero.middleend.domain.entities.TweetDTO;

import java.util.List;

public interface TweetOutputPort {

    TweetDTO findById(String tweetId);

    List<TweetDTO> findByHashtag(String hashtag);

    List<TweetDTO> findByMention(String mention);

    List<TweetDTO> findByOwners(List<String> owners);
}

