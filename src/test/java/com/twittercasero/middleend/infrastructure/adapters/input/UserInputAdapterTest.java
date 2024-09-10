package com.twittercasero.middleend.infrastructure.adapters.input;

import com.twittercasero.middleend.domain.entities.UserDTO;
import com.twittercasero.middleend.infrastructure.message.producer.UserMessageProducer;
import com.twittercasero.middleend.infrastructure.restclient.UserRestServiceClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserInputAdapterTest {

    @Mock
    private UserMessageProducer userMessageProducer;
    @Mock
    private UserRestServiceClient userRestServiceClient;

    @InjectMocks
    private UserInputAdapter userInputAdapter;

    @Test
    void testCreateUser() {
        UserDTO user = UserDTO.builder().nickName("user123").email("user@example.com").build();
        when(userRestServiceClient.createUser(user)).thenReturn(user);

        UserDTO result = userInputAdapter.createUser(user);

        verify(userRestServiceClient).createUser(user);
        assertSame(user, result);
    }

    @Test
    void testFollowUser() {
        String userNickName = "user123";
        String nickNameToFollow = "user456";

        userInputAdapter.followUser(userNickName, nickNameToFollow);

        verify(userMessageProducer).followUser(userNickName, nickNameToFollow);
    }

    @Test
    void testUnFollowUser() {
        String userNickName = "user123";
        String nickNameToUnFollow = "user789";

        userInputAdapter.unFollowUser(userNickName, nickNameToUnFollow);

        verify(userMessageProducer).unfollowUser(userNickName, nickNameToUnFollow);
    }

    @Test
    void testBlockUser() {
        String userNickName = "user123";
        String nickNameToBlock = "user999";

        userInputAdapter.blockUser(userNickName, nickNameToBlock);

        verify(userMessageProducer).blockUser(userNickName, nickNameToBlock);
    }
}
