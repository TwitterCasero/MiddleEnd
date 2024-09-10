package com.twittercasero.middleend.application.useCases.users.impl;

import com.twittercasero.middleend.application.port.input.UserInputPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class FollowUserUseCaseImplTest {

    @Mock
    private UserInputPort userInputPort;

    @InjectMocks
    private FollowUserUseCaseImpl followUserUseCase;

    @Test
    void testAccept() {
        String userNickName = "user123";
        String nickNameToFollow = "user456";

        followUserUseCase.accept(userNickName, nickNameToFollow);

        verify(userInputPort).followUser(userNickName, nickNameToFollow);
    }
}
