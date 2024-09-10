package com.twittercasero.middleend.application.useCases.users.impl;

import com.twittercasero.middleend.application.port.output.UserOutputPort;
import com.twittercasero.middleend.application.useCases.users.GetUserUseCase;
import com.twittercasero.middleend.domain.entities.UserDTO;

public class GetUserUseCaseImpl implements GetUserUseCase {

    private final UserOutputPort userOutputPort;

    public GetUserUseCaseImpl(UserOutputPort userOutputPort) {
        this.userOutputPort = userOutputPort;
    }

    @Override
    public UserDTO apply(String s) {
        return userOutputPort.findUserByNickName(s);
    }
}
