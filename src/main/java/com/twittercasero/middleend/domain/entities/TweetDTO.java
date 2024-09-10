package com.twittercasero.middleend.domain.entities;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Builder
@Getter
public class TweetDTO {

    private String id;

    @NotBlank(message = "Message name cannot be empty")
    private String message;

    @NotBlank(message = "Owner name cannot be empty")
    private String owner;

    private Date posted;

    private List<String> mentions = new ArrayList<>();

    private List<String> hashtags = new ArrayList<>();

    private int likes;

    private int retweets;

    private List<Reply> replies = new ArrayList<>();

    private boolean edited = false;

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Reply {
        private String owner;
        private String message;
        private Date posted;

    }

}
