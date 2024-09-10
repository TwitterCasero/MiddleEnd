package com.twittercasero.middleend.infrastructure.message.producer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserMessageProducerTest {

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @InjectMocks
    private UserMessageProducer userMessageProducer;

    @Test
    void testFollowUser() {
        String userNickName = "user123";
        String nickNameToFollow = "user456";
        String expectedMessage = "{\"userNickName\": \"" + userNickName + "\", \"targetNickName\": \"" + nickNameToFollow + "\"}";

        userMessageProducer.followUser(userNickName, nickNameToFollow);

        verify(kafkaTemplate).send("add-followers-topic", expectedMessage);
    }

    @Test
    void testUnfollowUser() {
        String userNickName = "user123";
        String nickNameToUnFollow = "user456";
        String expectedMessage = "{\"userNickName\": \"" + userNickName + "\", \"targetNickName\": \"" + nickNameToUnFollow + "\"}";

        userMessageProducer.unfollowUser(userNickName, nickNameToUnFollow);

        verify(kafkaTemplate).send("delete-followers-topic", expectedMessage);
    }

    @Test
    void testBlockUser() {
        String userNickName = "user123";
        String nickNameToBlock = "user456";
        String expectedMessage = "{\"userNickName\": \"" + userNickName + "\", \"targetNickName\": \"" + nickNameToBlock + "\"}";

        userMessageProducer.blockUser(userNickName, nickNameToBlock);

        verify(kafkaTemplate).send("block-following-topic", expectedMessage);
    }
}
