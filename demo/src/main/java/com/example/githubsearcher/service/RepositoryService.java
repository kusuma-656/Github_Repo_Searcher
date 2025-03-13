package com.example.githubsearcher.service;

import com.example.githubsearcher.model.RepositoryEntity;
import com.example.githubsearcher.repository.RepositoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class RepositoryService {

    private final RepositoryRepository repositoryRepository;
    private final RestTemplate restTemplate;

    public RepositoryService(RepositoryRepository repositoryRepository, RestTemplate restTemplate) {
        this.repositoryRepository = repositoryRepository;
        this.restTemplate = restTemplate;
    }

    public List<RepositoryEntity> getRepositories(String username) {
        String url = "https://api.github.com/users/" + username + "/repos";
        try {
            RepositoryEntity[] repos = restTemplate.getForObject(url, RepositoryEntity[].class);

            if (repos != null) {
                for (RepositoryEntity repo : repos) {
                    repo.setLastUpdated(LocalDateTime.now());
                    repositoryRepository.save(repo);
                }
            }

            return repositoryRepository.findAll();

        } catch (Exception e) {
            System.out.println("Error fetching repositories: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }


}
