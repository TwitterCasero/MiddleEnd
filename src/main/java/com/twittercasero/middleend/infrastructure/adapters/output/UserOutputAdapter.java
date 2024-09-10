package com.twittercasero.middleend.infrastructure.adapters.output;

import com.twittercasero.middleend.application.port.output.UserOutputPort;
import com.twittercasero.middleend.domain.entities.UserDTO;
import com.twittercasero.middleend.infrastructure.restclient.UserRestServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserOutputAdapter implements UserOutputPort {

    @Autowired
    private UserRestServiceClient userRestServiceClient;

    @Override
    public UserDTO findUserByNickName(String nickName) {
        return userRestServiceClient.getUserByNickname(nickName);
    }

    @Override
    public List<String> findFollow(String nickName) {
        return userRestServiceClient.getFollowersByNickname(nickName);
    }
}
