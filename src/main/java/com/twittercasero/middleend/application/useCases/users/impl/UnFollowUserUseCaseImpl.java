package com.twittercasero.middleend.application.useCases.users.impl;

import com.twittercasero.middleend.application.port.input.UserInputPort;
import com.twittercasero.middleend.application.useCases.users.UnFollowUserUseCase;

public class UnFollowUserUseCaseImpl implements UnFollowUserUseCase {

    private final UserInputPort userInputPort;

    public UnFollowUserUseCaseImpl(UserInputPort userInputPort) {
        this.userInputPort = userInputPort;
    }


    @Override
    public void accept(String userNickName, String nickNameToUnFollow) {
        userInputPort.unFollowUser(userNickName, nickNameToUnFollow);
    }
}
