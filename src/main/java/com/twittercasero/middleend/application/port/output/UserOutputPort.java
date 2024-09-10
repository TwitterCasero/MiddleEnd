package com.twittercasero.middleend.application.port.output;

import com.twittercasero.middleend.domain.entities.UserDTO;

import java.util.List;

public interface UserOutputPort {

    UserDTO findUserByNickName(String nickName);

    List<String> findFollow(String nickName);
}
