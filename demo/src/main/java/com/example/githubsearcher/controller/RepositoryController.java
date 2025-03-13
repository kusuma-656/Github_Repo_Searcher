package com.example.githubsearcher.controller;

import com.example.githubsearcher.model.RepositoryEntity;
import com.example.githubsearcher.service.RepositoryService;
import org.springframework.web.bind.annotation.*;

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
}
