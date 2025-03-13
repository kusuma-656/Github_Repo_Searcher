package com.example.githubsearcher.repository;

import com.example.githubsearcher.model.RepositoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryRepository extends JpaRepository<RepositoryEntity, Long> {

    List<RepositoryEntity> findByOwner(String owner);

    // ✅ New queries for filtering
    List<RepositoryEntity> findByLanguageAndStarsGreaterThanEqual(String language, int minStars, Sort sort);

    List<RepositoryEntity> findByLanguage(String language, Sort sort);

    List<RepositoryEntity> findByStarsGreaterThanEqual(int minStars, Sort sort);
}
