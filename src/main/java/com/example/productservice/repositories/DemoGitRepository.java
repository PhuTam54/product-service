package com.example.productservice.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.productservice.entities.DemoGit;

public interface DemoGitRepository extends JpaRepository<DemoGit, Long>, JpaSpecificationExecutor<DemoGit> {
    List<DemoGit> findCategoriesByDemoGitName(String name);

    @Query(value = "SELECT * FROM DemoGit WHERE c.name = :name", nativeQuery = true)
    List<DemoGit> findByNameUsingQuery(@Param("name") String name);

    Page<DemoGit> findByDeletedAtIsNotNull(Pageable pageable);

    Page<DemoGit> findByDeletedAtIsNull(Pageable pageable);

    boolean existsByDemoGitName(String name);
}
