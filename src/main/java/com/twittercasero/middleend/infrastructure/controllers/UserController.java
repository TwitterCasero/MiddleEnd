package com.twittercasero.middleend.infrastructure.controllers;

import com.twittercasero.middleend.application.useCases.users.BlockUserUseCase;
import com.twittercasero.middleend.application.useCases.users.CreateUserUseCase;
import com.twittercasero.middleend.application.useCases.users.FollowUserUseCase;
import com.twittercasero.middleend.application.useCases.users.GetUserUseCase;
import com.twittercasero.middleend.application.useCases.users.UnFollowUserUseCase;
import com.twittercasero.middleend.domain.entities.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private CreateUserUseCase createUserUseCase;
    @Autowired
    private FollowUserUseCase followUserUseCase;
    @Autowired
    private UnFollowUserUseCase unfollowUserUseCase;
    @Autowired
    private BlockUserUseCase blockUserUseCase;
    @Autowired
    private GetUserUseCase getUserUseCase;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user) {
        UserDTO savedUser = createUserUseCase.apply(user);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String userId) {
        UserDTO userDTO = getUserUseCase.apply(userId);
        return ResponseEntity.ok(userDTO);
    }


    @PatchMapping("/{userId}/follow/{targetUserId}")
    public ResponseEntity<Void> followUser(@PathVariable String userId, @PathVariable String targetUserId) {
        followUserUseCase.accept(userId, targetUserId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{userId}/unfollow/{targetUserId}")
    public ResponseEntity<Void> unfollowUser(@PathVariable String userId, @PathVariable String targetUserId) {
        unfollowUserUseCase.accept(userId, targetUserId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{userId}/block/{targetUserId}")
    public ResponseEntity<Void> blockUser(@PathVariable String userId, @PathVariable String targetUserId) {
        blockUserUseCase.accept(userId, targetUserId);
        return ResponseEntity.ok().build();
    }
}
