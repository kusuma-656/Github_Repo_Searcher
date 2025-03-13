package com.example.githubsearcher.controller;

import com.example.githubsearcher.model.RepositoryEntity;
import com.example.githubsearcher.service.RepositoryService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Sort;

import java.util.List;

@RestController
@RequestMapping("/api/github")
public class RepositoryController {

    private final RepositoryService repositoryService;

    public RepositoryController(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    @GetMapping("/repositories")
    public List<RepositoryEntity> getRepositories(@RequestParam String username) {
        return repositoryService.getRepositories(username);
    }

    // ✅ New endpoint for filtering and sorting
    @GetMapping("/repositories/filter")
    public ResponseEntity<List<RepositoryEntity>> getFilteredRepositories(
            @RequestParam(required = false) String language,
            @RequestParam(required = false) Integer minStars,
            @RequestParam(defaultValue = "stars") String sortBy,
            @RequestParam(defaultValue = "desc") String order) {

        Sort.Direction direction = order.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        List<RepositoryEntity> repositories = repositoryService.getFilteredRepositories(language, minStars, sortBy, direction);
        return new ResponseEntity<>(repositories, HttpStatus.OK);
    }
}
