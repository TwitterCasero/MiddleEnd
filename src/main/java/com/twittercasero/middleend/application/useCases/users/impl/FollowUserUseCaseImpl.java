package com.twittercasero.middleend.application.useCases.users.impl;

import com.twittercasero.middleend.application.port.input.UserInputPort;
import com.twittercasero.middleend.application.useCases.users.FollowUserUseCase;

public class FollowUserUseCaseImpl implements FollowUserUseCase {

    private final UserInputPort userInputPort;

    public FollowUserUseCaseImpl(UserInputPort userInputPort) {
        this.userInputPort = userInputPort;
    }


    @Override
    public void accept(String userNickName, String nickNameToFollow) {
        userInputPort.followUser(userNickName, nickNameToFollow);
    }
}
