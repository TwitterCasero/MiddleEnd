package com.twittercasero.middleend.application.useCases.users.impl;

import com.twittercasero.middleend.application.port.output.UserOutputPort;
import com.twittercasero.middleend.domain.entities.UserDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetUserUseCaseImplTest {

    @Mock
    private UserOutputPort userOutputPort;

    @InjectMocks
    private GetUserUseCaseImpl getUserUseCase;

    @Test
    void testApply() {
        String nickname = "user123";
        UserDTO expectedUser = UserDTO.builder().nickName(nickname).email("user@example.com").build();

        when(userOutputPort.findUserByNickName(nickname)).thenReturn(expectedUser);

        UserDTO result = getUserUseCase.apply(nickname);

        assertEquals(expectedUser, result);

        verify(userOutputPort).findUserByNickName(nickname);
    }
}
