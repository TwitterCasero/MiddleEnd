package com.twittercasero.middleend.infrastructure.restclient;

import com.twittercasero.middleend.domain.entities.TweetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class TweetRestServiceClient {

    @Autowired
    private RestTemplate restTemplate;


    @Value("${tweet.service.url}")  // Inyecta la URL desde el archivo de propiedades
    String tweetServiceUrl;


    public List<TweetDTO> getTweetsByOwners(List<String> owners, int page, int size) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(tweetServiceUrl.concat("/tweets"))
                .queryParam("owners", String.join(",", owners))  // Asumiendo que el API puede manejar la lista como una cadena separada por comas
                .queryParam("page", page)
                .queryParam("size", size);

        ResponseEntity<List<TweetDTO>> response = restTemplate.exchange(
                uriBuilder.toUriString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<TweetDTO>>() {
                }
        );

        return response.getBody();
    }

    public TweetDTO getTweetById(String tweetId) {
        String url = tweetServiceUrl + "/tweets/" + tweetId;
        ResponseEntity<TweetDTO> response = restTemplate.getForEntity(url, TweetDTO.class);

        return response.getBody();
    }

    public List<TweetDTO> getTweetsByMention(String nickname, int page, int size) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(tweetServiceUrl + "/tweets/" + nickname + "/mentions")
                .queryParam("page", page)
                .queryParam("size", size);

        ResponseEntity<List<TweetDTO>> response = restTemplate.exchange(
                uriBuilder.toUriString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<TweetDTO>>() {
                }
        );

        return response.getBody();
    }

    public List<TweetDTO> getTweetsByHashtag(String hashtag, int page, int size) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(tweetServiceUrl + "/tweets/" + hashtag + "/hashtag")
                .queryParam("page", page)
                .queryParam("size", size);

        ResponseEntity<List<TweetDTO>> response = restTemplate.exchange(
                uriBuilder.toUriString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<TweetDTO>>() {
                }
        );

        return response.getBody();
    }


}
