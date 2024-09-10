package com.twittercasero.middleend.infrastructure.adapters.input;

import com.twittercasero.middleend.application.port.input.UserInputPort;
import com.twittercasero.middleend.domain.entities.UserDTO;
import com.twittercasero.middleend.infrastructure.message.producer.UserMessageProducer;
import com.twittercasero.middleend.infrastructure.restclient.UserRestServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInputAdapter implements UserInputPort {

    @Autowired
    private UserMessageProducer userMessageProducer;

    @Autowired
    private UserRestServiceClient userRestServiceClient;

    @Override
    public UserDTO createUser(UserDTO user) {
        return userRestServiceClient.createUser(user);
    }

    @Override
    public void followUser(String userNickName, String nickNameToFollow) {
        userMessageProducer.followUser(userNickName, nickNameToFollow);

    }

    @Override
    public void unFollowUser(String userNickName, String nickNameToUnFollow) {
        userMessageProducer.unfollowUser(userNickName, nickNameToUnFollow);
    }

    @Override
    public void blockUser(String userNickName, String nickNameToBlock) {
        userMessageProducer.blockUser(userNickName, nickNameToBlock);
    }
}
