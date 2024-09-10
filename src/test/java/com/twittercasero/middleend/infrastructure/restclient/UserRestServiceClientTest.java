package com.twittercasero.middleend.infrastructure.restclient;

import com.twittercasero.middleend.domain.entities.UserDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.contains;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.isNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserRestServiceClientTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private UserRestServiceClient userRestServiceClient;

    @Test
    void testCreateUser() {
        UserDTO newUser = UserDTO.builder().nickName("nickname").email("email@example.com").build();
        UserDTO returnedUser = UserDTO.builder().nickName("nickname").email("email@example.com").build();
        when(restTemplate.postForEntity(anyString(), any(), any()))
                .thenReturn(ResponseEntity.ok(returnedUser));

        UserDTO result = userRestServiceClient.createUser(newUser);

        assertEquals(returnedUser, result);
        verify(restTemplate).postForEntity(contains("/users"), eq(newUser), eq(UserDTO.class));
    }

    @Test
    void testGetUserByNickname() {
        UserDTO user = UserDTO.builder().nickName("nickname").email("email@example.com").build();
        when(restTemplate.getForEntity(anyString(), eq(UserDTO.class)))
                .thenReturn(ResponseEntity.ok(user));

        UserDTO result = userRestServiceClient.getUserByNickname("nickname");

        assertEquals(user, result);
        verify(restTemplate).getForEntity(contains("/users/nickname"), eq(UserDTO.class));
    }

    @Test
    void testGetFollowersByNickname() {
        List<String> followers = Arrays.asList("user1", "user2");
        when(restTemplate.exchange(
                anyString(),
                eq(HttpMethod.GET),
                any(),
                any(ParameterizedTypeReference.class)))
                .thenReturn(ResponseEntity.ok(followers));

        List<String> result = userRestServiceClient.getFollowersByNickname("nickname");

        assertEquals(followers, result);
        verify(restTemplate).exchange(contains("/users/nickname/followers"), eq(HttpMethod.GET), isNull(), any(ParameterizedTypeReference.class));
    }
}
