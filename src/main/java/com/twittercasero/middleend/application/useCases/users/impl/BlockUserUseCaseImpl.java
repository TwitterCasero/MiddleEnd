package com.twittercasero.middleend.application.useCases.users.impl;

import com.twittercasero.middleend.application.port.input.UserInputPort;
import com.twittercasero.middleend.application.useCases.users.BlockUserUseCase;

public class BlockUserUseCaseImpl implements BlockUserUseCase {

    private final UserInputPort userInputPort;

    public BlockUserUseCaseImpl(UserInputPort userInputPort) {
        this.userInputPort = userInputPort;
    }


    @Override
    public void accept(String userNickName, String nickNameToBlock) {
        userInputPort.blockUser(userNickName, nickNameToBlock);

    }
}
