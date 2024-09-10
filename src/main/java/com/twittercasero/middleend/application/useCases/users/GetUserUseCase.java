package com.twittercasero.middleend.application.useCases.users;

import com.twittercasero.middleend.domain.entities.UserDTO;

import java.util.function.Function;

public interface GetUserUseCase extends Function<String, UserDTO> {
}
