package com.example.githubsearcher.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "repositories")
@JsonIgnoreProperties(ignoreUnknown = true) // Ignore extra fields from GitHub
public class RepositoryEntity {

    @Id
    @JsonProperty("id")
    private Long id;



    @Column(length = 500) // Increase the limit for name if necessary
    private String name;

    @Column(length = 1000) // Increase limit to 1000 characters
    private String description;

    @JsonProperty("language")
    @Column(length = 100)
    private String language;

    @JsonProperty("stargazers_count")
    private int stars;

    @JsonProperty("forks_count")
    private int forks;

    private LocalDateTime lastUpdated;

    @JsonProperty("owner")
    private void unpackOwnerFromNestedObject(Object owner) {
        if (owner instanceof java.util.LinkedHashMap) {
            this.owner = (String) ((java.util.LinkedHashMap) owner).get("login");
        }
    }

    @Column(length = 500)
    private String owner;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getForks() {
        return forks;
    }

    public void setForks(int forks) {
        this.forks = forks;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
