package com.twittercasero.middleend.application.useCases.users;

import com.twittercasero.middleend.domain.entities.UserDTO;

import java.util.function.Function;

public interface CreateUserUseCase extends Function<UserDTO, UserDTO> {
}
