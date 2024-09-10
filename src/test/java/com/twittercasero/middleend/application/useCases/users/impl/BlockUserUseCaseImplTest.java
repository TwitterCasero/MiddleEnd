package com.twittercasero.middleend.application.useCases.users.impl;

import com.twittercasero.middleend.application.port.input.UserInputPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BlockUserUseCaseImplTest {

    @Mock
    private UserInputPort userInputPort;

    @InjectMocks
    private BlockUserUseCaseImpl blockUserUseCase;

    @Test
    void testAccept() {
        String userNickName = "user123";
        String nickNameToBlock = "user456";

        blockUserUseCase.accept(userNickName, nickNameToBlock);

        verify(userInputPort).blockUser(userNickName, nickNameToBlock);
    }
}
