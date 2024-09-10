package com.twittercasero.middleend.infrastructure.controllers;

import com.twittercasero.middleend.application.useCases.users.BlockUserUseCase;
import com.twittercasero.middleend.application.useCases.users.CreateUserUseCase;
import com.twittercasero.middleend.application.useCases.users.FollowUserUseCase;
import com.twittercasero.middleend.application.useCases.users.GetUserUseCase;
import com.twittercasero.middleend.application.useCases.users.UnFollowUserUseCase;
import com.twittercasero.middleend.domain.entities.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CreateUserUseCase createUserUseCase;
    @Mock
    private FollowUserUseCase followUserUseCase;
    @Mock
    private UnFollowUserUseCase unfollowUserUseCase;
    @Mock
    private BlockUserUseCase blockUserUseCase;
    @Mock
    private GetUserUseCase getUserUseCase;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        mockMvc = standaloneSetup(userController).build();
    }

    @Test
    void testCreateUser() throws Exception {
        UserDTO savedUser = UserDTO.builder()
                .nickName("newUser")
                .email("new@user.com")
                .build();

        when(createUserUseCase.apply(any(UserDTO.class))).thenReturn(savedUser);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"newUser\",\"email\":\"new@user.com\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nickName").value("newUser"));

        verify(createUserUseCase).apply(any(UserDTO.class));
    }

    @Test
    void testGetUser() throws Exception {
        String userId = "123";
        UserDTO userDTO = UserDTO.builder()
                .nickName("existingUser")
                .email("exist@user.com")
                .build();

        when(getUserUseCase.apply(userId)).thenReturn(userDTO);

        mockMvc.perform(get("/users/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nickName").value("existingUser"));

        verify(getUserUseCase).apply(userId);
    }

    @Test
    void testFollowUser() throws Exception {
        mockMvc.perform(patch("/users/{userId}/follow/{targetUserId}", "1", "2"))
                .andExpect(status().isOk());

        verify(followUserUseCase).accept("1", "2");
    }

    @Test
    void testUnfollowUser() throws Exception {
        mockMvc.perform(patch("/users/{userId}/unfollow/{targetUserId}", "1", "2"))
                .andExpect(status().isOk());

        verify(unfollowUserUseCase).accept("1", "2");
    }

    @Test
    void testBlockUser() throws Exception {
        mockMvc.perform(patch("/users/{userId}/block/{targetUserId}", "1", "2"))
                .andExpect(status().isOk());

        verify(blockUserUseCase).accept("1", "2");
    }
}
