package com.twittercasero.middleend.application.useCases.users.impl;


import com.twittercasero.middleend.application.port.input.UserInputPort;
import com.twittercasero.middleend.application.useCases.users.CreateUserUseCase;
import com.twittercasero.middleend.domain.entities.UserDTO;

public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserInputPort userInputPort;

    public CreateUserUseCaseImpl(UserInputPort userInputPort) {
        this.userInputPort = userInputPort;
    }

    @Override
    public UserDTO apply(UserDTO user) {
        return userInputPort.createUser(user);
    }
}
