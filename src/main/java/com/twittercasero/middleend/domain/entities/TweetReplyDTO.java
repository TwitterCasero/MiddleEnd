package com.twittercasero.middleend.domain.entities;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TweetReplyDTO {

    private String message;

}
