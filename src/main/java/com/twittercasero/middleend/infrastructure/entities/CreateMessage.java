package com.twittercasero.middleend.infrastructure.entities;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateMessage {

    @Size(max = 280, message = "Message must be at most 280 characters long")
    private String message;

}
