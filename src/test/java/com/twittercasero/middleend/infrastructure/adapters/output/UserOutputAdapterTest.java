package com.twittercasero.middleend.infrastructure.adapters.output;

import com.twittercasero.middleend.domain.entities.UserDTO;
import com.twittercasero.middleend.infrastructure.restclient.UserRestServiceClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserOutputAdapterTest {

    @Mock
    private UserRestServiceClient userRestServiceClient;

    @InjectMocks
    private UserOutputAdapter userOutputAdapter;

    @Test
    void testFindUserByNickName() {
        String nickName = "user123";
        UserDTO expectedUser = UserDTO.builder().nickName(nickName).email("user@example.com").build();
        when(userRestServiceClient.getUserByNickname(nickName)).thenReturn(expectedUser);

        UserDTO result = userOutputAdapter.findUserByNickName(nickName);

        assertEquals(expectedUser, result);
        verify(userRestServiceClient).getUserByNickname(nickName);
    }

    @Test
    void testFindFollow() {
        String nickName = "user123";
        List<String> expectedFollowers = List.of("user456", "user789");
        when(userRestServiceClient.getFollowersByNickname(nickName)).thenReturn(expectedFollowers);

        List<String> result = userOutputAdapter.findFollow(nickName);

        assertEquals(expectedFollowers, result);
        verify(userRestServiceClient).getFollowersByNickname(nickName);
    }
}
