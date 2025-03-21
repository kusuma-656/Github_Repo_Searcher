package com.example.githubsearcher.service;

import com.example.githubsearcher.model.RepositoryEntity;
import com.example.githubsearcher.repository.RepositoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
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

    // ✅ Fetch and store GitHub repositories
    public List<RepositoryEntity> getRepositories(String username) {
        String url = "https://api.github.com/users/" + username + "/repos";
        try {
            RepositoryEntity[] repos = restTemplate.getForObject(url, RepositoryEntity[].class);

            if (repos != null) {
                Arrays.stream(repos).forEach(repo -> {
                    repo.setLastUpdated(LocalDateTime.now());
                    repositoryRepository.save(repo);
                });
            }
            return repositoryRepository.findAll();
        } catch (Exception e) {
            System.out.println("Error fetching repositories: " + e.getMessage());
            throw e;
        }
    }

    // ✅ Filtering and sorting logic
    public List<RepositoryEntity> getFilteredRepositories(String language, Integer minStars, String sortBy, Sort.Direction direction) {
        if (language != null && minStars != null) {
            return repositoryRepository.findByLanguageAndStarsGreaterThanEqual(language, minStars, Sort.by(direction, sortBy));
        } else if (language != null) {
            return repositoryRepository.findByLanguage(language, Sort.by(direction, sortBy));
        } else if (minStars != null) {
            return repositoryRepository.findByStarsGreaterThanEqual(minStars, Sort.by(direction, sortBy));
        } else {
            return repositoryRepository.findAll(Sort.by(direction, sortBy));
        }
    }
}
