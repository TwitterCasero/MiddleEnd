package com.twittercasero.middleend.application.useCases.users.impl;

import com.twittercasero.middleend.application.port.input.UserInputPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UnFollowUserUseCaseImplTest {

    @Mock
    private UserInputPort userInputPort;

    @InjectMocks
    private UnFollowUserUseCaseImpl unFollowUserUseCase;

    @Test
    void testAccept() {
        String userNickName = "user123";
        String nickNameToUnFollow = "user456";

        unFollowUserUseCase.accept(userNickName, nickNameToUnFollow);

        verify(userInputPort).unFollowUser(userNickName, nickNameToUnFollow);
    }
}
