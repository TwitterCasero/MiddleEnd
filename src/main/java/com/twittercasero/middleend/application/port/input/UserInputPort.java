package com.twittercasero.middleend.application.port.input;

import com.twittercasero.middleend.domain.entities.UserDTO;

public interface UserInputPort {
    UserDTO createUser(UserDTO user);

    void followUser(String userNickName, String nickNameToFollow);

    void unFollowUser(String userNickName, String nickNameToUnFollow);

    void blockUser(String userNickName, String nickNameToBlock);

}
