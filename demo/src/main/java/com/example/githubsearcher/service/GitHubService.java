package com.example.githubsearcher.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GitHubService {

    private final RestTemplate restTemplate = new RestTemplate();

    public String searchRepositories(String query, String language, String sort) {
        String url = "https://api.github.com/search/repositories?q=" + query +
                "+language:" + language + "&sort=" + sort;
        return restTemplate.getForObject(url, String.class);
    }
}
