package com.twittercasero.middleend.infrastructure.message.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserMessageProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void followUser(String userNickName, String nickNameToFollow) {
        String message = "{\"userNickName\": \"" + userNickName + "\", \"targetNickName\": \"" + nickNameToFollow + "\"}";
        kafkaTemplate.send("add-followers-topic", message);
    }

    public void unfollowUser(String userNickName, String nickNameToUnFollow) {
        String message = "{\"userNickName\": \"" + userNickName + "\", \"targetNickName\": \"" + nickNameToUnFollow + "\"}";
        kafkaTemplate.send("delete-followers-topic", message);
    }

    public void blockUser(String userNickName, String nickNameToBlock) {
        String message = "{\"userNickName\": \"" + userNickName + "\", \"targetNickName\": \"" + nickNameToBlock + "\"}";
        kafkaTemplate.send("block-following-topic", message);
    }
}
