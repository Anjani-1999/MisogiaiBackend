package com.erp.jwt.repo.codecast;

import com.erp.jwt.entity.codecast.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagRepo extends JpaRepository<TagEntity, Long> {

    @Query(value = "SELECT distinct(tag) FROM tag t", nativeQuery = true)
    List<String> findDistinctTags();
}
