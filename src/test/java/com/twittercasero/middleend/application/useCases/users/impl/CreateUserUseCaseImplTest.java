package com.twittercasero.middleend.application.useCases.users.impl;

import com.twittercasero.middleend.application.port.input.UserInputPort;
import com.twittercasero.middleend.domain.entities.UserDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateUserUseCaseImplTest {

    @Mock
    private UserInputPort userInputPort;

    @InjectMocks
    private CreateUserUseCaseImpl createUserUseCase;

    @Test
    void testApply() {
        UserDTO user = UserDTO.builder().nickName("newUser").email("new@user.com").build();
        UserDTO expectedUser = UserDTO.builder().nickName("newUser").email("new@user.com").build();

        when(userInputPort.createUser(any(UserDTO.class))).thenReturn(expectedUser);

        UserDTO result = createUserUseCase.apply(user);

        assertEquals(expectedUser, result);

        verify(userInputPort).createUser(user);
    }
}
