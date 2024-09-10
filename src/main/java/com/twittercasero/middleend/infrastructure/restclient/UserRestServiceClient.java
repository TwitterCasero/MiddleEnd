package com.twittercasero.middleend.infrastructure.restclient;

import com.twittercasero.middleend.domain.entities.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserRestServiceClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${user.service.url}")  // Inyecta la URL desde el archivo de propiedades
    private String userServiceUrl;

    public UserDTO createUser(UserDTO user) {
        ResponseEntity<UserDTO> response = restTemplate.postForEntity(userServiceUrl + "/users", user, UserDTO.class);
        return response.getBody();
    }

    public UserDTO getUserByNickname(String nickname) {
        ResponseEntity<UserDTO> response = restTemplate.getForEntity(userServiceUrl + "/users/" + nickname, UserDTO.class);
        return response.getBody();
    }

    public List<String> getFollowersByNickname(String nickname) {
        ResponseEntity<List<String>> response = restTemplate.exchange(
                userServiceUrl + "/users/" + nickname + "/followers",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<String>>() {
                }
        );
        return response.getBody();
    }

}
